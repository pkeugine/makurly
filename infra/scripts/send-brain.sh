cd $PK_BRAIN_PATH
cd ..
scp -i $PK_INFRA_PATH/pk-key.pem brain/requirements.txt ubuntu@$PK_FLASK_PUBLIC_IP:~
scp -i $PK_INFRA_PATH/pk-key.pem brain/app.py ubuntu@$PK_FLASK_PUBLIC_IP:~
scp -i $PK_INFRA_PATH/pk-key.pem brain/recommender.py ubuntu@$PK_FLASK_PUBLIC_IP:~
# scp -i $PK_INFRA_PATH/pk-key.pem brain/explicitMF.pkl ubuntu@$PK_FLASK_PUBLIC_IP:~
# scp -i $PK_INFRA_PATH/pk-key.pem brain/implicitMF.pkl ubuntu@$PK_FLASK_PUBLIC_IP:~
scp -i $PK_INFRA_PATH/pk-key.pem brain/exMF.pkl ubuntu@$PK_FLASK_PUBLIC_IP:~
scp -i $PK_INFRA_PATH/pk-key.pem brain/imMF.pkl ubuntu@$PK_FLASK_PUBLIC_IP:~
scp -i $PK_INFRA_PATH/pk-key.pem $PK_INFRA_PATH/scripts/flask-setup.sh ubuntu@$PK_FLASK_PUBLIC_IP:~
