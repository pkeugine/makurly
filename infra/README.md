# TODO

- [x] 백엔드(api) 서버 도메인 확보
- [ ] apt update 즉시 실행
- [ ] 필요한 패키지 다운로드 즉시 실행
- [ ] mkdir 즉시 실행
- [ ] 인스턴스 shell ui 변경 (인스턴스 용도 및 public ip 보이면 좋을듯)

- [ ] database 생성
- [ ] brain 서버 생성
    - [x] initiate flask project
    - [ ] use models and run the server
    - [ ] flask server should install python3 and start venv

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

- [x] pwd and save the directory path > information.txt
- [ ] interactive ui for service domain name > information.txt
- [ ] interactive ui for api domain name > information.txt
- [ ] (modify.sh) modify ALL scripts to use the directory path from above process
- [ ] (connect-ip.sh) modify domain names in production related files
- [ ] (not fully solved) aws-setup (child process 에서 진행되기 때문에 environment variable setting 이 안된다)

### initiate.sh : initiate infrastructure process
모든 리소스를 처음 만들고 환경 설정을 해주는 과정.
- [x] terraform apply -auto-approve
- [x] create production/ip.txt containing created public ips

### update process
이미 배포가 된 과정에서 일부분만 수정해야 할 때.
#### backend 배포
#### frontend 배포

### destory.sh : destroy process
리소스를 삭제하는 과정. Cloud 서비스를 사용하지 않아 비용을 내기 싫거나 프로젝트를 그만 할 때 사용.
- [x] terraform destory
- [x] delete pem key

### what this does
- [x] automatic (default) CORS setup
- [x] HSTS
- [x] create pem key to access instances
- [x] default network security setting (open 22 port for SSH, 8080 port for Spring application, 80 & 433
    - [x] port 22 for SSH (all instances)
    - [x] port 8080 for Spring application, only for web application server instance
    - [x] port 80 & 443 for Nginx instance
