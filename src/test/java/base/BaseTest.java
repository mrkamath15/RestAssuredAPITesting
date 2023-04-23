package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import utils.Constants;

import java.lang.reflect.Method;

public class BaseTest {
    public static RequestSpecification spec;
    private RequestSpecBuilder builder;
    public ExtentSparkReporter sparkReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public BaseTest() {
        builder = new RequestSpecBuilder();
        builder.setBaseUri("https://jsonplaceholder.typicode.com/");
        builder.setContentType(ContentType.JSON);
        builder.log(LogDetail.URI);
        builder.log(LogDetail.BODY);
        builder.log(LogDetail.METHOD);
        builder.addFilter(new ResponseLoggingFilter(LogDetail.STATUS));
        builder.addFilter(new ResponseLoggingFilter(LogDetail.BODY));
        spec = builder.build();
    }

    @BeforeTest
    public void beforeTest() {
        sparkReporter = new ExtentSparkReporter(Constants.EXTENT_REPORTS_PATH);
        sparkReporter.config().setReportName("API Automation Report");
        sparkReporter.config().setDocumentTitle("REST API Automation Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setEncoding("utf-8");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    @AfterTest
    public void afterTest() {
        extentReports.flush();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        extentTest = extentReports.createTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            Markup m = MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN);
            extentTest.log(Status.PASS, m);
        }
        else if (result.getStatus() == ITestResult.FAILURE) {
            Markup m = MarkupHelper.createLabel("Test Failed", ExtentColor.RED);
            extentTest.log(Status.FAIL, m);
            extentTest.fail(result.getThrowable());
        }
        else if (result.getStatus() == ITestResult.SKIP) {
            Markup m = MarkupHelper.createLabel("Test Skipped", ExtentColor.AMBER);
            extentTest.log(Status.SKIP,m);
        }
    }
}
