cd $PK_BACKEND_PATH
./gradlew clean build
# jar
scp -i $PK_INFRA_PATH/pk-key.pem $PK_BACKEND_PATH/build/libs/backend-0.0.1-SNAPSHOT.jar ubuntu@$PK_API_PUBLIC_IP:~
# running spring (spring-setup.sh)
scp -i $PK_INFRA_PATH/pk-key.pem $PK_INFRA_PATH/scripts/spring-setup.sh ubuntu@$PK_API_PUBLIC_IP:~
# spring's nginx (spring-nginx directory)
scp -i $PK_INFRA_PATH/pk-key.pem -r $PK_INFRA_PATH/spring-nginx ubuntu@$PK_API_PUBLIC_IP:~

cd $PK_INFRA_PATH
