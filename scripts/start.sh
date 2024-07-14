#!/bin/bash

ROOT_PATH="/home/ubuntu/spring-github-action"
JAR="$ROOT_PATH/application.jar"
STOP_LOG="$ROOT_PATH/stop.log"
SERVICE_PID=$(pgrep -f $JAR) # 실행중인 Spring 서버의 PID


#ROOT_PATH="/home/ubuntu/spring-github-action"
#JAR="$ROOT_PATH/application.jar"
#
#APP_LOG="$ROOT_PATH/application.log"
#ERROR_LOG="$ROOT_PATH/error.log"
#START_LOG="$ROOT_PATH/start.log"
#
#NOW=$(date +%c)

#echo "[$NOW] $JAR 복사" >> $START_LOG
#cp $ROOT_PATH/build/libs/spring-github-action-1.0.0.jar $JAR

#nohup java -jar $JAR > $APP_LOG 2> $ERROR_LOG &
#echo "[$NOW] > $JAR 실행" >> $START_LOG
#JAR_NAME=$(ls -tr $ROOT_PATH/ | grep SNAPSHOT.jar | tail -n 1)
#
#echo "> Jar Name: $JAR_NAME"
#nohup ./run.sh &

#SERVICE_PID=$(pgrep -f $JAR)
#echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG