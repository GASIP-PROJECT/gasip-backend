# 자주 사용하는 값 변수에 저장
REPOSITORY=/home/ec2-user/app

# git clone 받은 위치로 이동
cd $REPOSITORY

echo "> 현재 구동중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

echo "> 현재 구동중인 애플리케이션 pid: $CURRENT_PID"
if [ -z "$CURRENT_PID" ]; then
	echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
	echo "> kill -15 $CURRENT_PID"
	kill -15 $CURRENT_PID
	sleep 5
fi

echo "> Jar Name: $JAR_NAME"
nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &
