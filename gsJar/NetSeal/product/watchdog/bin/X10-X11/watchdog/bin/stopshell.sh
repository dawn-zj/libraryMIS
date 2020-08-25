if [ $1 = "" ]
then
	echo "stopshell.sh command"
	exit
fi

str=`ps -ef | grep "$1" | grep -v grep | grep -v " 0 " | grep -v stopshell.sh | head -n 1`
while [ "$str" != "" ]
do
	pid=`echo $str | awk '{ print $2 }'`
	time=`echo $str | awk '{ print $7 }' | awk -F: '{ print $1$2$3 }'`
	if [ "$time" != "000000" ]
	then
		kill -9 $pid
	fi
	sleep 0.5
	str=`ps -ef | grep "$1" | grep -v grep | grep -v stopshell.sh | head -n 1`
done
