# sed -n 1p $PK_ABSOLUTE_PATH/production/ip.txt | awk -F'"' '{print $2}'
# sed -n 2p $PK_ABSOLUTE_PATH/production/ip.txt | awk -F'"' '{print $2}'
# app_server_ip=$(echo "$a" | sed -n 3p $PK_ABSOLUTE_PATH/information.txt | rev | cut -d= -f1 | rev)
echo "REACT_APP_API_SERVER=http://$PK_API_DOMAIN_NAME:8080" > $PK_ABSOLUTE_PATH/production/.env.production
echo "cors:" > $PK_ABSOLUTE_PATH/production/application-prod.yml
echo "  allowed-origins: http://$PK_SERVICE_DOMAIN_NAME, https://$PK_SERVICE_DOMAIN_NAME" >> $PK_ABSOLUTE_PATH/production/application-prod.yml
