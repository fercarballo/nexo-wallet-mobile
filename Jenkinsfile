// Pipeline — Jenkins (declarative). Equivalente al .gitlab-ci.yml para entornos Jenkins.
//
// La ejecución real corre en GitHub Actions (el repo está en GitHub); este pipeline no se ejecuta
// automáticamente sobre el mismo commit para no duplicar la batería.
//
// La etapa de dispositivo real requiere un agente etiquetado con emulador Android + Appium.
pipeline {
  agent none

  options {
    timestamps()
    timeout(time: 30, unit: 'MINUTES')
  }

  stages {
    stage('Suite BDD (modo simulado)') {
      agent {
        docker { image 'maven:3.9-eclipse-temurin-21' }
      }
      steps {
        sh 'mvn -B test'
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
          archiveArtifacts artifacts: 'target/cucumber/**', allowEmptyArchive: true
        }
      }
    }

    stage('Dispositivo real (Appium)') {
      // Sólo bajo demanda y en un agente con emulador: no forma parte del gate por commit.
      when { expression { return params.RUN_REAL_DEVICE == true } }
      agent { label 'android-emulator' }
      steps {
        sh 'mvn -B test -DwalletMode=appium'
      }
    }
  }
}
