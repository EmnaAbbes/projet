pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')
        DOCKERHUB_USER = 'tonUserDockerHub'
        BACKEND_IMAGE = "${DOCKERHUB_USER}/backend-app"
        FRONTEND_IMAGE = "${DOCKERHUB_USER}/frontend-app"
    }

    stages {

        stage('Build Backend JAR') {
            steps {
                dir('miniProjet_Spring') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                bat "docker build -t %BACKEND_IMAGE%:latest ./miniProjet_Spring"
                bat "docker build -t %FRONTEND_IMAGE%:latest ./front-nginx"
            }
        }

        stage('Push to Docker Hub') {
            steps {
                bat "docker login -u %DOCKERHUB_CREDENTIALS_USR% -p %DOCKERHUB_CREDENTIALS_PSW%"
                bat "docker push %BACKEND_IMAGE%:latest"
                bat "docker push %FRONTEND_IMAGE%:latest"
            }
        }

        stage('Deploy') {
            steps {
                bat 'docker-compose down'
                bat 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminé.'
        }
        success {
            echo 'Build et déploiement réussis ✅'
        }
        failure {
            echo 'Le pipeline a échoué ❌'
        }
    }
}