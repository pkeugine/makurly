cd $PK_BRAIN_PATH
cd ..
scp -i $PK_INFRA_PATH/pk-key.pem -r brain ubuntu@$PK_FLASK_PUBLIC_IP:~
