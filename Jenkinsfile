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

                script {
                    echo "Before parsing JSON"
                    def jsonFailureStructure = params.failureStructure
                    echo "JSON Failure Structure: ${jsonFailureStructure}"

                    def jsonSlurper = new JsonSlurper()

                    if (jsonFailureStructure == null) {
                        echo "No failure structure provided"
                        return
                    }

                    def failureStructure = jsonSlurper.parseText(jsonFailureStructure)
                    echo "Failure Structure: ${failureStructure}"
                }
            }
        }

        stage('Run Tests') {
            matrix {
                axes {
                    axis {
                        name 'TEST_CASE'
                        values 'DeflakeFlakyProjectApplicationTests#successfulTest', 'DeflakeFlakyProjectApplicationTests#flakyTest', 'DeflakeFlakyProjectApplicationTests#failingTest'
                    }
                }

                stages {
                    stage('Execute Test') {
                    steps {
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