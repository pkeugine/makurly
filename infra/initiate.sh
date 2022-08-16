# domain name setup
echo -n "insert service domain name: "
read -r PK_SERVICE_DOMAIN_NAME
echo -n "set $PK_SERVICE_DOMAIN_NAME as service domain name\n"
export PK_SERVICE_DOMAIN_NAME
echo "pk_service_domain_name=${PK_SERVICE_DOMAIN_NAME}" >> information.txt

echo -n "insert api domain name: "
read -r PK_API_DOMAIN_NAME
echo -n "set $PK_API_DOMAIN_NAME as service domain name\n"
export PK_API_DOMAIN_NAME
echo "pk_api_domain_name=${PK_API_DOMAIN_NAME}" >> information.txt

# aws setup
export AWS_ACCESS_KEY_ID=$(sed -n 2p pkeugine_accessKeys.csv | cut -d, -f1)
export AWS_SECRET_ACCESS_KEY=$(sed -n 2p pkeugine_accessKeys.csv | rev | cut -d, -f1 | rev)
echo AWS_ACCESS_KEY_ID : $AWS_ACCESS_KEY_ID
echo AWS_SECRET_ACCESS_KEY : $AWS_SECRET_ACCESS_KEY

# begin terraform
terraform init
terraform apply -auto-approve
terraform output >> information.txt
