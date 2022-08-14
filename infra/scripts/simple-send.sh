# scp -i ~/Projects/kurly/infra/pk-key.pem ~/Projects/kurly/backend/build/libs/backend-0.0.1-SNAPSHOT.jar ubuntu@13.209.48.102:~/build
scp -i ~/Projects/kurly/infra/pk-key.pem ~/Projects/kurly/infra/scripts/spring.sh ubuntu@13.209.48.102:~/scripts
scp -i ~/Projects/kurly/infra/pk-key.pem ~/Projects/kurly/infra/scripts/spring-setup.sh ubuntu@13.209.48.102:~/scripts


# scp -i ~/Projects/kurly/infra/pk-key.pem -r ~/Projects/kurly/frontend/build ubuntu@13.124.232.162:~
# scp -i ~/Projects/kurly/infra/pk-key.pem -r ~/Projects/kurly/infra/scripts/nginx.sh ubuntu@13.124.232.162:~/scripts
# scp -i ~/Projects/kurly/infra/pk-key.pem -r ~/Projects/kurly/infra/scripts/nginx-setup.sh ubuntu@13.124.232.162:~/scripts
# scp -i ~/Projects/kurly/infra/pk-key.pem ~/Projects/kurly/infra/nginx/myapp.conf ubuntu@13.124.232.162:~/nginx
