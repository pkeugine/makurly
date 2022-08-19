echo "REACT_APP_API_SERVER=http://$PK_API_DOMAIN_NAME:8080" > $PK_ABSOLUTE_PATH/production/.env.production
echo "cors:" > $PK_ABSOLUTE_PATH/production/application-prod.yml
echo "  allowed-origins: http://$PK_SERVICE_DOMAIN_NAME, https://$PK_SERVICE_DOMAIN_NAME" >> $PK_ABSOLUTE_PATH/production/application-prod.yml
