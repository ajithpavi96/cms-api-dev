pipeline {
    agent any

    parameters {
        string(name: 'BRANCH_TO_BUILD', defaultValue: 'master', description: 'Git branch to build')
    }
    
    environment {
        DOCKER_IMAGE = 'ak096511/cms-api:latest'
    }
    
    tools {
        maven 'Maven 3.9.16'
        jdk 'JDK 21'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo "Checking out source code from branch: ${params.BRANCH_TO_BUILD}..."
                checkout([$class: 'GitSCM',
                    branches: [[name: "*/${params.BRANCH_TO_BUILD}"]],
                    userRemoteConfigs: [[
                        url: 'https://github.com/ajithpavi96/cms-api-dev.git',
                        credentialsId: 'b290128b-fdd1-40b4-8fcf-8c10e6cf7699'
                    ]]
                ])
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building Spring Boot application...'
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo 'Packaging application...'
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh 'docker --version'
                sh 'docker build -t ${DOCKER_IMAGE} .'
            }
        }
        
        stage('Push to Docker Hub') {
            steps {
                echo 'Pushing image to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh 'docker push ${DOCKER_IMAGE}'
                    sh 'docker logout'
                }
            }
        }
        
        stage('Deploy with Docker') {
            steps {
                echo 'Deploying with Docker...'
                sh 'docker stop cms-api || true'
                sh 'docker rm cms-api || true'
                sh 'docker run -d -p 9090:8080 --name cms-api ${DOCKER_IMAGE}'
                sh 'docker ps'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed.'
            cleanWs()
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
