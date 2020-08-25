pids=`ps -ef | grep $1 | grep -v grep | awk '{ kill -9 $2 }' > pids`

if [ "$pids" != "" ]
then
	killallpid $pids
fi

killallpid(){
	until [ $# -eq 0 ]
	do
		kill -9 $1
		shift
	done
}
