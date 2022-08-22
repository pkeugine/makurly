sudo ln -s /home/ubuntu/myapp.conf /etc/nginx/sites-available/myapp.conf
sudo ln -s /home/ubuntu/myapp.conf /etc/nginx/sites-enabled/myapp.conf
sudo systemctl restart nginx
