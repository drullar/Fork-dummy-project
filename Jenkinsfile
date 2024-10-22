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
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            matrix {
                axes {
                    axis {
                        name 'TEST_CASE'
                        values 'successfulTest', 'flakyTest', 'failingTest'
                    }
                }
                stages {
                    stage('Execute Test') {
                        steps {
                            echo "Running test: ${TEST_CASE}"
                            echo "Deflake info: ${params.TEST_CASE}"
                            script {
                                // Run the specified test
                                sh "mvn -Dtest=${TEST_CASE} test"
                            }
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            // Archive test results
            junit '**/target/surefire-reports/*.xml'
        }
    }
}