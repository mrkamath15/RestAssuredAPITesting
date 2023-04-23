pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                bat "mvn clean test"
            }
            post {
            always {
                    publishHTML target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/surefire-reports/API Automation Framework',
                    reportFiles: 'Automation Test.html',
                    reportName: 'REST API Test Report'
                ]                
            }
    }
}
}
}
