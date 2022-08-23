cd $PK_BRAIN_PATH

scp -i $PK_INFRA_PATH/pk-key.pem kurly_hackerton_tutorial_0818.ipynb ubuntu@$PK_JUPYTER_PUBLIC_IP:~
scp -i $PK_INFRA_PATH/pk-key.pem $PK_INFRA_PATH/requirements.txt ubuntu@$PK_JUPYTER_PUBLIC_IP:~
