# TODO

- [ ] alias and environment variables only last for current session!!!!!!!!!!!!!!!!

- [x] 백엔드(api) 서버 도메인 확보
- [ ] apt update 즉시 실행
- [ ] 필요한 패키지 다운로드 즉시 실행
- [ ] mkdir 즉시 실행
- [ ] 인스턴스 shell ui 변경 (인스턴스 용도 및 public ip 보이면 좋을듯)

- [ ] database 생성
    - [x] use mariadb
    - [x] fill in data
    - [ ] switch to RDS if I have the time
    - [ ] automate the process
- [ ] brain 서버 생성
    - [x] initiate flask project
    - [x] use models and run the server
    - [ ] flask server should install python3 and start venv
    - [ ] check how to build flask production server
    - [ ] add flask instance
        - [ ] automate the process

- [ ] setup private network

* remember: to use environment variables, use `source script.sh` to execute command
### 준비물
- [x] install aws cli
- [x] install terraform
- [x] "username_accessKeys.csv"

- [x] frontend domain name (makurkly.kro.kr)
    - [x] SSL certificates for domain name(ex : certificate.crt, private.key)
- [x] backend domain name (makurly-api.kro.kr)

### setup.sh : initial setup before doing anything
- [ ] install aws cli
- [ ] install terraform

- [x] pwd and save the directory path >> information.txt
    - [x] export it

### initiate.sh : initiate infrastructure process
모든 리소스를 처음 만들고 환경 설정을 해주는 과정.
- [x] interactive ui for service domain name > information.txt
    - [x] export it
- [x] interactive ui for api domain name > information.txt
    - [x] export it
- [x] modify ALL scripts to use the directory path from setup process
    - solved by using environment variable
- [x] (connect-ip.sh) modify domain names in production related files
    - [x] application-prod.yml
    - [x] .env.production
- [x] aws-setup
    - [ ] export it

- [x] terraform apply -auto-approve
- [x] add created public ips to information.txt
    - [x] export it (HARD CODED!!!!!! might crash if terraform code structure changes)

- [x] alias ssh commands
    - [x] nginx
    - [x] spring

### update process
이미 배포가 된 과정에서 일부분만 수정해야 할 때.
#### backend 배포
- [x] gradle build
- [ ] backend instance 로 jar 파일 전송
#### frontend 배포
- [x] yarn build
- [ ] frontend instance 로 build 디렉토리 전송

### destory.sh : destroy process
리소스를 삭제하는 과정. Cloud 서비스를 사용하지 않아 비용을 내기 싫거나 프로젝트를 그만 할 때 사용.
- [x] terraform destory
- [x] delete pem key

### what this does
- [ ] install minimum required packages in instances
- [x] automatic (default) CORS setup
- [x] HSTS
- [x] alias ssh commands so user doesn't have to keep track of public ip
    - [x] nginx
    - [x] spring
    - [ ] database
    - [ ] flask
- [x] create pem key to access instances, delete pem key when destroying resources
- [x] default network security setting
    - [x] port 22 for SSH (all instances)
    - [x] port 8080 for Spring application, only for web application server instance
    - [x] port 80 & 443 for Nginx instance
- [x] build jar file and react pages in local machine and send it to instances at startup
