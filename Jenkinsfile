pipeline {
    agent any
    
    tools {
        maven 'maven3'
        jdk 'jdk17'
    }

    environment {
	JAVA_HOME = "/usr/lib/jvm/java-17-openjdk"
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
        SCANNER_HOME = tool 'sonar-scanner'
        DOCKERHUB_USERNAME = 'butterfly88'
        DOCKER_IMAGE = "${DOCKERHUB_USERNAME}/spotify-app:latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'docker-hub-creds', url: 'https://github.com/Tulip55/OddorEvenNumbers.git'
            }
        }

        stage('Compile') {
            steps {
	            dir('OddorEvennumbers'){
                sh "mvn compile"
            }
        }
    }

        stage('Test') {
            steps {
	            dir('OddorEvenNumbers'){
                script {
                    docker.image("openjdk:11-jdk").inside {
                        sh "mvn test"
                    }
                }
            }
        }
     }

        stage('Sonar Analysis') {
            steps {
	            dir('OddorEvenNumbers'){
                script {
                    docker.image("openjdk:8-jdk").inside {
                        withSonarQubeEnv('sonar-server') {
                            sh "$SCANNER_HOME/bin/sonar-scanner -Dsonar.projectKey=OddOrEvenNumbers -Dsonar.host.url=http://3.135.189.78:9000"
                        }
                    }
                }
            }
        }
   }

        stage('Build') {
            steps {
               dir('OddorEvenNumbers'){
                script {
                    docker.image("openjdk:17-jdk").inside {
                        sh "mvn package"
                    }
                }
            }
        }
    }

        stage('Push to DockerHub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASS')]) {
                        echo 'Pushing to DockerHub...'
                    }
                }
            }
        }

      post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
	}
       
        failure {
            echo 'Build Failed. Check logs for errors.'
        }
    }
}
