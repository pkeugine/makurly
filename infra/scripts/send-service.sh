cd $PK_FRONTEND_PATH
yarn build
# build directory
scp -i $PK_INFRA_PATH/pk-key.pem -r $PK_FRONTEND_PATH/build ubuntu@$PK_SERVICE_PUBLIC_IP:~
# ssl directory
scp -i $PK_INFRA_PATH/pk-key.pem -r $PK_INFRA_PATH/ssl ubuntu@$PK_SERVICE_PUBLIC_IP:~
# nginx config (myapp.conf)
scp -i $PK_INFRA_PATH/pk-key.pem $PK_INFRA_PATH/nginx/myapp.conf ubuntu@$PK_SERVICE_PUBLIC_IP:~
# running nginx (nginx-setup.sh)
scp -i $PK_INFRA_PATH/pk-key.pem $PK_INFRA_PATH/scripts/nginx-setup.sh ubuntu@$PK_SERVICE_PUBLIC_IP:~

cd $PK_INFRA_PATH
