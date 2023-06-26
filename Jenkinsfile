pipeline {
    agent any
    tools {
        maven maven
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Add your build commands here
                sh 'mvn clean install' // Assuming Maven is used for the project
            }
        }

        stage('Test') {
            steps {
                // Add your test commands here
                sh 'mvn test' // Assuming Maven is used for the project
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml' // Publish test results
        }
    }
}