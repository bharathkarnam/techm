### techm
## First need to run configserver app along with 

# mongodb 
docker pull mongo
docker run --name mongo -p 127.0.0.1:27017:27017 -d mongo:latest 

# then mongoservice and then techm with javaopts -Dspring.profiles.active=dev infact set this for all the services
do a 
`mvn clean install` once done add /tech.techm/target/generated-sources/swagger to build path
