pipeline {
    agent any

    environment {
        REPO_CREDENTIALS = credentials('maven-credential')
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
                sh "./gradlew buildAllVers --no-daemon -PbuildNumber=${env.BUILD_NUMBER}"
            }
        }

        stage('List Build Artifacts') {
            steps {
                echo "Artifacts in build/libs-versioned:"
                sh 'ls -R build/libs-versioned'
            }
        }

        stage('Archive Build Artifacts') {
            steps {
                archiveArtifacts artifacts: 'build/libs-versioned/**/*.jar', fingerprint: true
            }
        }

        stage('Release Tasks') {
            when {
                expression {
                    return env.GIT_BRANCH?.startsWith('refs/tags/')
                }
            }
            steps {
                script {
                    def releaseTag = env.GIT_BRANCH.replace('refs/tags/', '')
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
                        -PrepoUsername="${REPO_CREDENTIALS_USR}" \
                        -PrepoPassword="${REPO_CREDENTIALS_PSW}" \
                        --no-daemon
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
