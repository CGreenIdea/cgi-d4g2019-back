git pull

#npm i
#npm run build
# find src/main/resources/META-INF/resources \( -name '*.css' -o -name '*.css' -o -name '*.html' \) -exec gzip --verbose --keep {} \;

docker start -i graalvm
scp target/green-l4nterne-1.0-SNAPSHOT-runner d4g:/usr/share/green-l4nterne/runner
