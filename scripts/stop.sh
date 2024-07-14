#!/bin/bash

#ROOT_PATH="/home/ubuntu/spring-github-action"
#JAR="$ROOT_PATH/application.jar"
#STOP_LOG="$ROOT_PATH/stop.log"
#SERVICE_PID=$(pgrep -f $JAR) # 실행중인 Spring 서버의 PID
#
#if [ -z "$SERVICE_PID" ]; then
#  echo "서비스 NouFound" >> $STOP_LOG
#else
#  echo "서비스 종료 " >> $STOP_LOG
#  kill "$SERVICE_PID"
#  # kill -9 $SERVICE_PID # 강제 종료를 하고 싶다면 이 명령어 사용
#fi


REPOSITORY=/home/ubuntu/spring-github-action
cd $REPOSITORY

APP_NAME=gasip
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할것 없음."
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> $JAR_PATH 배포"
nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &
