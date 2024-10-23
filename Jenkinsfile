import groovy.json.JsonSlurper

pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }

        stage('Build') {
            steps {
                // Build the project using Maven
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            matrix {
                axes {
                    axis {
                        name 'TEST_CASE'
                        values 'FlakyTest', 'SuccessTest'
                    }
                }

                stages {
                    stage('Execute Test') {
                        steps {
                            script {
                                // Run the specified test
                                sh "mvn clean -Dtest=${TEST_CASE} test"
                            }
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}