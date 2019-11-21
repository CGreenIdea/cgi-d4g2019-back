docker rm -f graalvm
#docker build --no-cache -t graalvm:debian-9.11 -f src/main/docker/Dockerfile.graalvm src/main/docker/
#docker create -v $(pwd):/project --name graalvm graalvm:debian-9.11 mvn -Pnative -Dquarkus.http.host=0.0.0.0 -DskipTests clean package
docker create -v $(pwd):/project --name graalvm quay.io/quarkus/centos-quarkus-maven:19.2.1 mvn -Pnative -Dquarkus.http.host=0.0.0.0 clean package
