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
                        includes: '**/*.png',
                    keepAll: true,
                    reportDir: 'test-output/',
                    reportFiles: 'index.html',
                    reportName: 'REST API Test Report'
                ]                
            }
    }
}
}
}
