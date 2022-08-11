#!/bin/bash
sudo apt update -y

git clone https://github.com/DangsanFamily/Gurumi.git

sudo apt install openjdk-11-jdk -y

# 디폴트 브랜치는 dev
cd Gurumi/backend

# jar 파일 빌드
./gradlew clean build

# 애플리케이션 서버 실행 (8080 포트)
# java -jar build/libs/core-0.0.1-SNAPSHOT.jar
