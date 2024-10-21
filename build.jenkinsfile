pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://gkaraboychev@bitbucket.org/antonio_busuladzich/hackaton.git'
            }
        }
        stage('Build') {
            steps {
                sh './mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh './mvn test'
            }
        }
        stage('Package') {
            steps {
                sh './mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build and Deploy succeeded!'
        }
        failure {
            echo 'Build or Deploy failed!'
        }
    }
}