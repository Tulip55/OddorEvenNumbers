pipeline {
    agent any

    tools {
        maven 'Maven 3.8.8'   // Replace with your configured Maven version in Jenkins
        jdk 'jdk17'          // Replace with your configured JDK in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Tulip55/OddorEvenNumbers.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
