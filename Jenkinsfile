pipeline {
    agent any

    tools {
        maven 'Maven_3_9'
        jdk 'JDK_17'
    }

    environment {
        SLACK_CHANNEL = '#jenkins-alerts'
        EMAIL_RECIPIENT = 'notify.alert@email.com'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/username/JenkinsAutomationProject.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Parallel Tests') {
            parallel {
                stage('Run Selenium Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng.xml'
                    }
                }
                stage('Run Postman Tests') {
                    steps {
                        bat 'newman run UserAuth_API_Test.postman_collection.json'
                    }
                }
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML([reportDir: 'test-output', reportFiles: 'index.html', reportName: 'TestNG Results'])
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo 'Build Successful!'
            slackSend(channel: "${SLACK_CHANNEL}", message: "✅ Jenkins Build Passed!")
            emailext(to: "${EMAIL_RECIPIENT}", subject: "Jenkins Build Passed", body: "All tests passed successfully.")
        }
        failure {
            echo 'Build Failed!'
            slackSend(channel: "${SLACK_CHANNEL}", message: "❌ Jenkins Build Failed! Check reports.")
            emailext(to: "${EMAIL_RECIPIENT}", subject: "Jenkins Build Failed", body: "Some tests failed. Please check reports.")
        }
    }
}
