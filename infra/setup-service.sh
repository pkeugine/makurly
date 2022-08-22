#!/bin/bash
sudo apt update -y
sudo apt upgrade -y

sudo apt install nginx -y

# sudo mkdir ~/nginx
# sudo mkdir ~/scripts

sudo rm /etc/nginx/sites-available/default
sudo rm /etc/nginx/sites-enabled/default
