pipeline {
    agent any

    environment {
        SONARQUBE_SERVER = ${SONARQUBE_SERVER}
        DOCKER_IMAGE = 'butterfly88/java-app:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Tulip55/OddorEvenNumbers.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    docker.image('openjdk:17-jdk').inside {
                        sh 'mvn clean package'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    docker.image('openjdk:11-jdk').inside {
                        sh 'mvn test'
                    }
                }
            }
        }
        
        stage('Static Code Analysis'){
            steps{
                script{
                    docker.image('openjdk:8-jdk').inside{
                        sh 'mvn sonar:sonar -Dsonar.projecyKey=OddorEvenNumbers -Dsonar.host.url=http:127.0.0.1:9000'
                    }
                }
            }
        }
        
        stage('Build Docker Image'){
            steps{
                script{
                    sh 'docker build -t ${DOCKER_IMAGE} .'
                }
            }
        }
        
        stage('Push to Docker Hub'){
            steps{
                script{
                    withCredentials([usernamePassword(
                        credentialsId: 'dockeer-hub-creds',
                        userNameVariable: 'butterfly88',
                        passwordVariable:'Zq031590%'
                        )]){
                        
                    sh ' echo ${DOCKER_PASS} | docker login -u ${butterfly88} --Zq031590%-stdin'
                    sh 'docker push ${DOCKER_IMAGE}'
                }
            }
        }
    }
}
        
        post {
            always{
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true 
            }
        }
    }
