pipeline {
    agent any

    environment {
        GRADLE_OPTS = "-Xmx2G"
    }

    options {
        disableConcurrentBuilds()
        timestamps()
    }


    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build All Versions') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew buildAllVers --no-daemon'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
