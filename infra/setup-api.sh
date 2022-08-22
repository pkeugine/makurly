#!/bin/bash
sudo apt update -y
sudo apt upgrade -y

sudo apt install openjdk-11-jdk -y
sudo apt install nginx -y

# sudo mkdir ~/build
# sudo mkdir ~/scripts

sudo rm /etc/nginx/sites-available/default
sudo rm /etc/nginx/sites-enabled/default
