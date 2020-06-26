#bin/sh
mvn clean
mvn install
scp /Users/hanyijun/java/thirdpart/thirdpart-web/target/thirdpart-web-0.0.1-SNAPSHOT.jar root@31.1.135.5:/usr/local/backend/thirdpart
