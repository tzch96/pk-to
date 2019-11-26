FILE=./main/target/main-0.2.0-jar-with-dependencies.jar
if [ ! -f "$FILE" ]; then
  mvn package
fi
java -jar main/target/main-0.2.0-jar-with-dependencies.jar