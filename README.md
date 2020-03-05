# techm

### First need to run configserver app along with mongodb
https://github.com/bharathkarnam/configserver.git

### mongodb 
docker pull mongo
docker run --name mongo -p 127.0.0.1:27017:27017 -d mongo:latest 

### start mongoservice 
https://github.com/bharathkarnam/mongoservice.git

### start techm with javaopts -Dspring.profiles.active=dev infact set this for all the services
do a 
`mvn clean install` once done add /tech.techm/target/generated-sources/swagger to build path

### add localhost.p12 cert under resources to your trust store
keytool -v -importkeystore -srckeystore localhost.p12 -srcstoretype PKCS12 -destkeystore abc.jks -deststoretype JKS
for more https://jackstromberg.com/2013/05/importing-a-ssl-certificate-into-a-java-keystore-via-a-pkcs12-file/

## flow of things
<img src="techm/IMG_3865.jpg" >
