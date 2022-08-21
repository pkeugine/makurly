echo "REACT_APP_API_SERVER=http://$PK_API_DOMAIN_NAME" > $PK_INFRA_PATH/production/.env.production
echo "spring:" > $PK_INFRA_PATH/production/application-prod.yml
echo "  datasource:" >> $PK_INFRA_PATH/production/application-prod.yml
echo "    driver-class-name: org.mariadb.jdbc.Driver" >> $PK_INFRA_PATH/production/application-prod.yml
echo "    url: jdbc:mariadb://$PK_DATABASE_PUBLIC_IP:3306/makurly?userSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8" >> $PK_INFRA_PATH/production/application-prod.yml
echo "    username: makurly" >> $PK_INFRA_PATH/production/application-prod.yml
echo "    password: makurlypassword" >> $PK_INFRA_PATH/production/application-prod.yml
echo "  jpa:" >> $PK_INFRA_PATH/production/application-prod.yml
echo "    hibernate:" >> $PK_INFRA_PATH/production/application-prod.yml
echo "      ddl-auto: validate" >> $PK_INFRA_PATH/production/application-prod.yml
echo "  h2:" >> $PK_INFRA_PATH/production/application-prod.yml
echo "    console:" >> $PK_INFRA_PATH/production/application-prod.yml
echo "      enabled: false" >> $PK_INFRA_PATH/production/application-prod.yml
echo "cors:" >> $PK_INFRA_PATH/production/application-prod.yml
echo "  allowed-origins: http://$PK_SERVICE_DOMAIN_NAME, https://$PK_SERVICE_DOMAIN_NAME" >> $PK_INFRA_PATH/production/application-prod.yml
echo "recommend-api: http://$PK_FLASK_PUBLIC_IP:5000" >> $PK_INFRA_PATH/production/application-prod.yml
