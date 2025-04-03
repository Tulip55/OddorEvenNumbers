pipeline {
    agent any
    
    tools {
        maven 'maven3'
        jdk 'jdk'
    }

    environment {
        SCANNER_HOME = tool 'sonar-scanner'
        DOCKERHUB_USERNAME = 'butterfly88'
        DOCKER_IMAGE = "${DOCKERHUB_USERNAME}/spotify-app:latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Tulip55/OddorEvenNumbers.git'
            }
        }

        stage('Compile') {
            steps {
                sh "mvn compile"
            }
        }

        stage('Test') {
            steps {
                script {
                    docker.image("openjdk:11-jdk").inside {
                        sh "mvn test"
                    }
                }
            }
        }

        stage('Sonar Analysis') {
            steps {
                script {
                    docker.image("openjdk:8-jdk").inside {
                        withSonarQubeEnv('sonar-server') {
                            sh "$SCANNER_HOME/bin/sonar-scanner -Dsonar.projectKey=OddOrEvenNumbers -Dsonar.host.url=http://3.135.189.78:9000"
                        }
                    }
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    docker.image("openjdk:17-jdk").inside {
                        sh "mvn package"
                    }
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASS')]) {
                        sh 'echo "Docker Username: $DOCKERHUB_USERNAME"'
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/*.class', fingerprint: true
        }
    }
}
