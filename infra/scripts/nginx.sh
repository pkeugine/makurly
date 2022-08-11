#!/bin/bash
sudo apt update -y
sudo apt upgrade -y
git clone https://github.com/DangsanFamily/Gurumi.git

sudo apt install nginx -y

sudo apt install nodejs -y
sudo apt install npm -y
sudo npm install --global yarn -y

# 여러 node 버전을 다운로드하고 관리하기 위해 nvm 설치
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion

# 최신 node LTS
nvm install v16.16 -y
nvm use v16.16

# 디폴트 브랜치는 dev
cd Gurumi/frontend

# React 빌드
yarn
# 웹 서버 실행 (3000 포트)
yarn start
