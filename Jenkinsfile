pipeline {
    agent any

    environment {
        GRADLE_OPTS = "-Xmx2G"
        REPO_USERNAME = credentials('maven-username')
        REPO_PASSWORD = credentials('maven-password')
        GH_TOKEN = credentials('gh-javadoc-token')
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

        stage('Release Tasks') {
            when {
                expression {
                    return env.GITHUB_EVENT_NAME == 'release'
                }
            }
            steps {
                script {
                    def payload = readJSON text: env.GITHUB_EVENT_PAYLOAD
                    def releaseTag = payload.release.tag_name
                    echo "Release detected: ${releaseTag}"

                    sh './gradlew javadoc --no-daemon'

                    sh """
                    mkdir -p deploy/petarlib
                    cp -a javadoc/. deploy/petarlib/

                    git config --global user.name "jenkins"
                    git config --global user.email "jenkins@ci"

                    git clone https://${GH_TOKEN}@github.com/PetarMc1/javadocs.git deploy-repo
                    cd deploy-repo
                    mkdir -p petarlib/${releaseTag}
                    cp -a ../deploy/petarlib/* petarlib/${releaseTag}/
                    git add petarlib/${releaseTag}
                    git commit -m "Update petarlib Javadocs (release ${releaseTag})" || echo "No changes"
                    git push
                    """

                    sh """
                    ./gradlew publishMavenJavaPublicationToPetarReleasesRepository \
                        -PrepoUsername="${REPO_USERNAME}" \
                        -PrepoPassword="${REPO_PASSWORD}"
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
