pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Sonar') {
                    steps {
                        withSonarQubeEnv(installationName: 'My SonarQube Server') {
                        sh "mvn clean verify sonar:sonar \
                              -Dsonar.projectKey=ClimbCaferacer_javaAutomation_AYeUuq4QPe3ERjiv4kqe \
                              -Dsonar.projectName='javaAutomation' \
                              -Dsonar.host.url=http://localhost:9000 \
                              -Dsonar.token=sqp_2d19866d771d69ace82acdf49f457b32e35cdecb"
                        }

                    }
                }

        stage('Build') {
            steps {
                // Add your build commands here
                sh 'mvn clean verify -Dmaven.plugin.validation=VERBOSE' // Assuming Maven is used for the project
            }
        }

        stage('Test') {
            steps {
                // Add your test commands here
                sh 'mvn test' // Assuming Maven is used for the project
            }
        }
    }

/*     post {
        always {
            junit 'target/surefire-reports *//*.xml' // Publish test results
        }
    } */
}