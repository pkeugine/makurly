cd $PK_BACKEND_PATH
./gradlew clean build
scp -i $PK_INFRA_PATH/pk-key.pem $PK_BACKEND_PATH/build/libs/backend-0.0.1-SNAPSHOT.jar ubuntu@$PK_API_IP:~/build
scp -i $PK_INFRA_PATH/pk-key.pem $PK_INFRA_PATH/scripts/spring.sh ubuntu@$PK_API_IP:~/scripts
scp -i $PK_INFRA_PATH/pk-key.pem $PK_INFRA_PATH/scripts/spring-setup.sh ubuntu@$PK_API_IP:~/scripts


cd $PK_FRONTEND_PATH
yarn build
scp -i $PK_INFRA_PATH/pk-key.pem -r $PK_FRONTEND_PATH/build ubuntu@$PK_SERVICE_IP:~
scp -i $PK_INFRA_PATH/pk-key.pem -r $PK_INFRA_PATH/scripts/nginx.sh ubuntu@$PK_SERVICE_IP:~/scripts
scp -i $PK_INFRA_PATH/pk-key.pem -r $PK_INFRA_PATH/scripts/nginx-setup.sh ubuntu@$PK_SERVICE_IP:~/scripts
scp -i $PK_INFRA_PATH/pk-key.pem $PK_INFRA_PATH/nginx/myapp.conf ubuntu@$PK_SERVICE_IP:~/nginx
