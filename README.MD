## RabbitMQ

README.MD는 Mac OS 기반으로 작성함
#### RabbitMQ download and install 
 - 설치 참고 : http://www.rabbitmq.com/download.html
 - Mac OS 설치 
   - brew 설치 \
    https://brew.sh/index_ko.html
   - 터미널에서 실행 \
   `/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`
   - Rabbit mq 설치 \
   `brew install rabbitmq` \
   - export
   `export PATH=$PATH:/usr/local/sbin`
   
####RabbitMQ 서버 실행

- `/usr/local/sbin` 폴더로 이동
- `./rabbitmq-server` 로 실행

####RabbitMQ 플러그인 접속
   
 - http://localhost:15672 로 접속
 - 기본 ID/PW는 guest/guest
 
#### Application 실행

 - RabbitmqApplication.main 실행
 