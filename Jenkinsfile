#!groovy​
properties([[$class: 'jenkins.model.BuildDiscarderProperty', strategy: [$class: 'LogRotator',
                                                                        numToKeepStr: '10',
                                                                        artifactNumToKeepStr: '10']]])
node {
    step([$class: 'WsCleanup'])
    
    stage('Check Out') {
        checkout scm
    }
        
        def mvnHome = tool 'MAVEN3'
        build_version = env.BUILD_NUMBER
      
        stage('Build & Test') {
            sh "${mvnHome}/bin/mvn clean verify -DBUILD_NUMBER=$build_version"
            
            junit(testResults: "subscriber-service/target/surefire-reports/*.xml")
            
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'subscriber-service-it/target/jacoco-report', reportFiles: 'index.html', reportName: 'Jacoco Report'])
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'subscriber-service-it/target/cucumber-html-report', reportFiles: 'index.html', reportName: 'Cucumber Report'])
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'subscriber-service-it/target/site/serenity/', reportFiles: 'index.html', reportName: 'Serenity Report'])
        }
        
        
        def sonarqubeScannerHome = tool name: 'sonarqubescanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
        stage('Code Analysis') {
            //sh "${sonarqubeScannerHome}/bin/sonar-scanner -e -Dsonar.host.url=http://sonar.ms-accelerator.com -Dsonar.projectName=subscriber-service -Dsonar.projectKey=com.dell.tsp.subscriber.tsp-subscriber-service:tsp-subscriber-service -Dsonar.sources=. -Dsonar.java.binaries=."
            sh "${mvnHome}/bin/mvn sonar:sonar"
        }

        stage('Deploy to Nexus') {
            sh "${mvnHome}/bin/mvn deploy -DBUILD_NUMBER=$build_version -Dmaven.test.skip=true"
         }
       stage ('Copy Artifact to Deployment Server') { 
          withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '1cbeac72-4505-4a87-9bbe-de92a95b9217', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
              sh 'sshpass -p $PASSWORD scp /var/lib/jenkins/workspace/Subscriber-Test-GSAP/subscriber-service/target/subscriber-service-1.0.0-SNAPSHOT.jar root@10.118.169.49:/root/Subscriber-service/'
              sh 'sshpass -p $PASSWORD scp /var/lib/jenkins/workspace/Subscriber-Test-GSAP/subscriber-service/target/subscriber-service-1.0.0-SNAPSHOT-exec.jar root@10.118.169.49:/root/Subscriber-service/'
              sh 'sshpass -p $PASSWORD scp /var/lib/jenkins/workspace/Subscriber-Test-GSAP/Dockerfile root@10.118.169.49:/root/Subscriber-service/'
              sh 'sshpass -p $PASSWORD scp /var/lib/jenkins/workspace/Subscriber-Test-GSAP/subscriber-service/docker.sh root@10.118.169.49:/root/Subscriber-service/'
              
          }
        stage ('Subscriber BE deployment to Docker') { 
           withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: '1cbeac72-4505-4a87-9bbe-de92a95b9217', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
              sh "sshpass -p $PASSWORD ssh root@10.118.169.49 'chmod a+x /root/Subscriber-service/docker.sh; /root/Subscriber-service/docker.sh'"
          }
    }

