@echo off

gradle clean build -x test --no-daemon
java -jar build\libs\geektrust.jar sample_input\input2.txt

@REM ./gradlew run -q --args="sample_input\input1.txt"