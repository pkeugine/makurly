cd $PK_BACKEND_PATH
./gradlew clean build
# jar
scp -i $PK_INFRA_PATH/pk-key.pem $PK_BACKEND_PATH/build/libs/backend-0.0.1-SNAPSHOT.jar ubuntu@$PK_API_PUBLIC_IP:~

cd $PK_FRONTEND_PATH
yarn build
# build directory
scp -i $PK_INFRA_PATH/pk-key.pem -r $PK_FRONTEND_PATH/build ubuntu@$PK_SERVICE_PUBLIC_IP:~

cd $PK_INFRA_PATH
