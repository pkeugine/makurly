#!/bin/bash
mkdir build

sudo apt update -y
sudo apt upgrade -y

sudo apt install openjdk-11-jdk -y

java -jar build/core-0.0.1-SNAPSHOT.jar
