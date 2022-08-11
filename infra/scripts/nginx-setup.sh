sudo rm /etc/nginx/sites-available/default
sudo ln -s /home/ubuntu/nginx/myapp.conf /etc/nginx/sites-available/myapp.conf
sudo ln -s /home/ubuntu/nginx/myapp.conf /etc/nginx/sites-enabled/myapp.conf
