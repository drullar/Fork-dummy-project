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
                        script {
                            def jsonFailureStructure = params.failureStructure
                            def jsonSlurper = new JsonSlurper()
                            def failureStructure = jsonSlurper.parseText(jsonString)
                            echo "Failure Structure: ${failureStructure}"

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