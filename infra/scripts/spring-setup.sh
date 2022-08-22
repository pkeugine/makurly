sudo rm /etc/nginx/sites-enabled/myapp.conf
sudo rm /etc/nginx/sites-available/myapp.conf
# kill spring application
sudo ln -s ~/spring-nginx/myapp.conf /etc/nginx/sites-available/myapp.conf
sudo ln -s ~/spring-nginx/myapp.conf /etc/nginx/sites-enabled/myapp.conf
sudo systemctl restart nginx
nohup java -jar backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod > nohup.out 2>&1 &
