pipeline {
    agent {
            docker {
                image 'maven:3.9.9-eclipse-temurin-21'
                args '-v /root/.m2:/root/.m2 -v /var/run/docker.sock:/var/run/docker.sock'
            }
        }

    environment {
        IMAGE_NAME = "anishmn/backend"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/AnishKashyap05/Devops-backend.git'
            }
        }
        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh "docker build -t $IMAGE_NAME:latest ."
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                    sh "docker push $IMAGE_NAME:latest"
                }
            }
        }
    }
}
