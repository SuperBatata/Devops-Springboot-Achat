pipeline {
    agent any



    stages {
        stage ('Checkout GIT'){
            steps {
                echo 'Pulling... ';
                    git branch : 'master',
                    url : 'https://github.com/SuperBatata/Devops-Springboot-Achat.git';
            }
        }

        stage ('clean'){
            steps {
                echo 'cleaning... ';
                sh 'mvn clean';
            }
        }

        stage ('compile'){
            steps {
                echo 'Compiling... ';
                sh 'mvn compile';
            }
        }
        stage ('Unit Test and Mockito'){
            steps {
                echo 'Testing... ';
                sh 'mvn test';
            }
        }

        stage('SonarQube analysis ') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.projectKey=achat -Dsonar.host.url=http://192.168.1.110:9000 -Dsonar.login=fa1da900575da4c5754a77b1dba94b097ba47c22'
            }
        }
        stage("Build") {
            steps {
                sh 'mvn clean install -DskipTests=true'
            }
        }

        stage('Nexus Deploy ') {
            steps {
                nexusArtifactUploader artifacts: [
                    [
                        artifactId: 'achat',
                        classifier: '',
                        file: 'target/achat.jar',
                        type: 'jar'
                    ]
                ],
                 credentialsId: 'nexus',
                 groupId: 'tn.esprit.rh',
                 nexusUrl: 'localhost:8081',
                 nexusVersion: 'nexus3',
                 protocol: 'http',
                 repository: 'Achat-release',
                 version: '1.0.0'
            }
        }

        
        stage('Build backend docker image') {
            steps {
                sh 'docker build -t superbatata/Achat-release .'
            }
        }
        
      
      stage('Push images to Dockerhub') {
            steps {
                script{

                
              sh 'docker login -u superbatata -p 181JMT1578'

             
              sh 'docker push superbatata/Achat-release'

            }

        }
    }
    
    
     stage("Email"){
           steps{
               emailext attachLog: true, body: "${env.BUILD_URL} has result ${currentBuild.result}", compressLog: true, subject: "Status of pipeline: ${currentBuild.fullDisplayName}", to: 'khaledbattiche@gmail.com'
           }
    }

    }
    post {
        always {
            junit(testResults: 'target/surefire-reports/*.xml', allowEmptyResults : true)
        }
    }
   
    
}
