pipeline {
    agent any
    tools {
        maven 'mymaven'
    }
    stages {
        stage('CODE') {
            steps {
               git branch: 'main', url: 'https://github.com/VaraLakshmi5701/Java-app.git'
            }
        }
         stage('BUILD') {
            steps {
               sh 'mvn clean package'
            }
        }
        stage('CQA')
        {
            steps {
                
              withSonarQubeEnv('sonarqube') {
                     sh '''
              mvn clean verify \
              org.sonarsource.scanner.maven:sonar-maven-plugin:3.10.0.2594:sonar \
              -Dsonar.projectKey=java
            '''
                }
            }
        }
        stage('webhook')
        {
            steps{
                waitForQualityGate abortPipeline: false, credentialsId: 'sonar'
            }
        }
        stage('Build Image')
        {
            steps{
                 sh 'docker build -t java-app .'
            }
        }
        stage('Image Scan')
        {
            steps {
                sh 'trivy image java-app'
            }
        }
        stage('Push Image')
        {
            steps {
               withDockerRegistry(credentialsId: 'docker', url: 'https://index.docker.io/v1/') {
                           sh ''' docker login 
                           docker tag java-app varalakshmi5701/java-app:v1
                           docker push varalakshmi5701/java-app:v1 '''
                               }
                
            }
        }
        stage('Container')
        {
            steps {
                sh 'docker run -itd --name java-app -p 1234:8080 varalakshmi5701/java-app:v1'
            }
        }
    }
}
