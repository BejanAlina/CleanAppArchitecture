pipeline {
  agent {
    node {
      label 'android'
    }

  }
  stages {
    stage('test') {
      parallel {
        stage('checkStyle') {
          steps {
            sh './gradlew checkStyle'
          }
        }
        stage('Unit Test') {
          steps {
            sh './gradlew testStagingDebug'
          }
        }
      }
    }
  }
}