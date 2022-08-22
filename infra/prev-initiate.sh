# domain name setup
echo -n "insert service domain name: "
read -r PK_SERVICE_DOMAIN_NAME
echo -n "set $PK_SERVICE_DOMAIN_NAME as service domain name\n"
export PK_SERVICE_DOMAIN_NAME
echo "PK_SERVICE_DOMAIN_NAME=${PK_SERVICE_DOMAIN_NAME}" >> information.txt

echo -n "insert api domain name: "
read -r PK_API_DOMAIN_NAME
echo -n "set $PK_API_DOMAIN_NAME as api domain name\n"
export PK_API_DOMAIN_NAME
echo "PK_API_DOMAIN_NAME=${PK_API_DOMAIN_NAME}" >> information.txt

# aws setup
# DO NOT SAVE AWS ACCESS KEYS ANYWHERE ELSE OTHER THAN THE CSV FILE THAT YOU'VE BEEN GIVEN
export AWS_ACCESS_KEY_ID=$(sed -n 2p jiho_pkeugine_accessKeys.csv | cut -d, -f1)
export AWS_SECRET_ACCESS_KEY=$(sed -n 2p jiho_pkeugine_accessKeys.csv | rev | cut -d, -f1 | rev)
echo AWS_ACCESS_KEY_ID : $AWS_ACCESS_KEY_ID
echo AWS_SECRET_ACCESS_KEY : $AWS_SECRET_ACCESS_KEY

# terraform setup
terraform init
terraform apply -auto-approve

export PK_API_PUBLIC_IP=$(terraform output | awk -F'"' 'NR==1{print $2}')
echo -n "set $PK_API_PUBLIC_IP as api public ip"
echo "PK_API_PUBLIC_IP=${PK_API_PUBLIC_IP}" >> information.txt

export PK_SERVICE_PUBLIC_IP=$(terraform output | awk -F'"' 'NR==2{print $2}')
echo -n "set $PK_SERVICE_PUBLIC_IP as service public ip"
echo "PK_SERVICE_PUBLIC_IP=${PK_SERVICE_PUBLIC_IP}" >> information.txt

export PK_DATABASE_PUBLIC_IP=$(terraform output | awk -F'"' 'NR==3{print $2}')
echo -n "set $PK_DATABASE_PUBLIC_IP as database public ip"
echo "PK_DATABASE_PUBLIC_IP=${PK_DATABASE_PUBLIC_IP}" >> information.txt

export PK_FLASK_PUBLIC_IP=$(terraform output | awk -F'"' 'NR==4{print $2}')
echo -n "set $PK_FLASK_PUBLIC_IP as flask public ip"
echo "PK_FLASK_PUBLIC_IP=${PK_FLASK_PUBLIC_IP}" >> information.txt

# create production related files
source $PK_INFRA_PATH/scripts/connect-ip.sh
