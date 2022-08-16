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
export AWS_ACCESS_KEY_ID=$(sed -n 2p pkeugine_accessKeys.csv | cut -d, -f1)
export AWS_SECRET_ACCESS_KEY=$(sed -n 2p pkeugine_accessKeys.csv | rev | cut -d, -f1 | rev)
echo AWS_ACCESS_KEY_ID : $AWS_ACCESS_KEY_ID
echo AWS_SECRET_ACCESS_KEY : $AWS_SECRET_ACCESS_KEY

# terraform setup
terraform init
terraform apply -auto-approve

echo -n "set $PK_SERVICE_PUBLIC_IP as service public ip"
export PK_SERVICE_PUBLIC_IP=$(terraform output | awk -F'"' '{print $2}' | tail -n 1)
echo "PK_SERVICE_PUBLIC_IP=${PK_SERVICE_PUBLIC_IP}" >> information.txt

echo -n "set $PK_API_PUBLIC_IP as api public ip"
export PK_API_PUBLIC_IP=$(terraform output | awk -F'"' '{print $2}' | head -n 1)
echo "PK_API_PUBLIC_IP=${PK_API_PUBLIC_IP}" >> information.txt

echo "alias gonginx with ssh to nginx server"
alias gonginx='ssh -i $PK_INFRA_PATH/pk-key.pem ubuntu@$PK_SERVICE_PUBLIC_IP'
echo "alias gonginx with ssh to spring server"
alias gospring='ssh -i $PK_INFRA_PATH/pk-key.pem ubuntu@$PK_API_PUBLIC_IP'
