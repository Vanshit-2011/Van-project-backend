pipeline {
    agent any 
    stages {
        stage('code-pull'){
            steps {
                git branch: 'dev', url: 'https://github.com/Vanshit-2011/Van-project-backend.git'
            }
        }
        stage('code-Build'){
            steps {
               sh 'mvn clean package'
            }
        }
         stage('Deploy-K8s'){
            steps {
               sh '''
                    docker build . -t vanshit967/project-backend-img:latest
                    docker push vanshit967/project-backend-img:latest
                    docker rmi vanshit967/project-backend-img:latest
                    kubectl apply -f ./deploy/

               '''
            }
        }
    }
}i
