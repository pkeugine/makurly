# pwd and save path
export PK_INFRA_PATH=$(pwd)
echo "set $PK_INFRA_PATH as absolute path"
echo "PK_INFRA_PATH=$PK_INFRA_PATH" > information.txt

cd ../backend
export PK_BACKEND_PATH=$(pwd)
echo "set $PK_BACKEND_PATH as backend path"
echo "PK_BACKEND_PATH=$PK_BACKEND_PATH" >> $PK_INFRA_PATH/information.txt

cd ../frontend
export PK_FRONTEND_PATH=$(pwd)
echo "set $PK_FRONTEND_PATH as frontend path"
echo "PK_FRONTEND_PATH=$PK_FRONTEND_PATH" >> $PK_INFRA_PATH/information.txt

cd $PK_INFRA_PATH
