# pwd and save path
echo "absolute_path=$(pwd)" > information.txt

# domain name setup
echo -n "insert service domain name: "
read -r service_domain_name
echo "service_domain_name=${service_domain_name}" >> information.txt

echo -n "insert api domain name: "
read -r api_domain_name
echo "api_domain_name=${api_domain_name}" >> information.txt

# aws setup
export AWS_ACCESS_KEY_ID=$(sed -n 2p pkeugine_accessKeys.csv | cut -d, -f1)
export AWS_SECRET_ACCESS_KEY=$(sed -n 2p pkeugine_accessKeys.csv | rev | cut -d, -f1 | rev)
echo AWS_ACCESS_KEY_ID : $AWS_ACCESS_KEY_ID
echo AWS_SECRET_ACCESS_KEY : $AWS_SECRET_ACCESS_KEY
