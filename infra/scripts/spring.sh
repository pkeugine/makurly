#!/bin/bash
sudo apt update -y

sudo apt install openjdk-11-jdk -y

chmod 400 ~/build/core-0.0.1-SNAPSHOT.jar

nohup java -jar ~/build/core-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod &
