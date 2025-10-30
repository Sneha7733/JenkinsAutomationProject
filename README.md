# JenkinsAutomationProject

### Overview
This project demonstrates Continuous Integration using Jenkins with Selenium (Web UI tests) and Postman (API tests).
Each time code is committed to GitHub, Jenkins automatically builds, runs tests, and sends notifications via Slack and Email.

### Setup Instructions
1. Install Jenkins and required plugins (Git, Maven, TestNG, Slack, Email).
2. Add credentials and connect Jenkins to your GitHub repo.
3. Configure Maven and JDK tools in Jenkins.
4. Create a new Pipeline job and point to the Jenkinsfile in this repository.
5. Add Slack and Email credentials under Jenkins Manage Plugins.
6. Run the pipeline. Reports will be visible under 'TestNG Results'.

### Tools & Technologies
- Jenkins
- Maven
- Selenium WebDriver
- TestNG
- Postman / Newman
- GitHub
- Slack & Email notifications

### Notifications
- Slack Channel: #jenkins-alerts
- Email Recipient: notify.alert@email.com
