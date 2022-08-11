cd ~/Projects/kurly/backend
./gradlew clean build
scp -i ~/Projects/kurly/infra/pk-key.pem ~/Projects/kurly/backend/build/libs/backend-0.0.1-SNAPSHOT.jar ubuntu@54.180.87.62:~/build
scp -i ~/Projects/kurly/infra/pk-key.pem ~/Projects/kurly/infra/scripts/spring.sh ubuntu@54.180.87.62:~/scripts
scp -i ~/Projects/kurly/infra/pk-key.pem ~/Projects/kurly/infra/scripts/spring-setup.sh ubuntu@54.180.87.62:~/scripts


cd ~/Projects/kurly/frontend
yarn build
scp -i ~/Projects/kurly/infra/pk-key.pem -r ~/Projects/kurly/frontend/build ubuntu@52.79.243.79:~
scp -i ~/Projects/kurly/infra/pk-key.pem -r ~/Projects/kurly/infra/scripts/nginx.sh ubuntu@52.79.243.79:~/scripts
scp -i ~/Projects/kurly/infra/pk-key.pem -r ~/Projects/kurly/infra/scripts/nginx-setup.sh ubuntu@52.79.243.79:~/scripts
scp -i ~/Projects/kurly/infra/pk-key.pem ~/Projects/kurly/infra/nginx/myapp.conf ubuntu@52.79.243.79:~/nginx
