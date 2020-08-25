export LANG=en_US.ISO8859-1
PORTS=""
PROCESSORS="java"
echo ""
echo "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"
echo "           Infosec NetSign Server Status"
echo "gentime `date +"%F %T"`"
echo "====================NET INTERFACES==================="
ifconfig
echo "====================================================="
echo "====================SYSTEM STATUS===================="
top -b -n 2 -d 1 | awk 'BEGIN { a = 0 } { if( $0 == "" ) a+=1 ; if ( a == 3 && $0 != "" ) print $0 }'
echo "====================================================="
echo "=====================DISK STATUS====================="
df
echo "====================================================="
echo "==================CONNECTION STATUS=================="
PORTS=`cat ../config/service.xml | grep port | sed 's/<\/port>//g' | sed 's/<port>//g' | awk 'BEGIN{ a="" } { a=a"|"$1} END { print a }'`
PORTS=`echo ${PORTS:1}`
echo "Ports of NetSignServer: $PORTS"
echo "Proto Recv-Q Send-Q Local Address               Foreign Address             State       PID/Program name"
if [ $PORTS ]
then
	netstat -anp | egrep -e "($PORTS)"
else
	echo "NetSign service did not set up."
fi
echo "====================================================="
echo "==================PLATFORM STATUS===================="
sensors 2>/dev/null
echo "====================================================="
echo "=================PROCESSORS STATUS==================="
pids=`ps -ef | egrep -e "($PROCESSORS)" | egrep -v egrep | awk 'BEGIN { a="" } { a=a","$2 } END { print a }'`
if [ $pids ]
then
	pids=`echo ${pids:1}`
	echo "Pids of $PROCESSORS: $pids"
	top -b -n 2 -d 1 -p "$pids" | awk 'BEGIN { a=0 } { if( $0 == "" ) a+=1 ; if ( a == 4 && $0 != "" ) print $0 }'
	echo "UID        PID  PPID  C STIME TTY          TIME CMD"
	ps -ef | egrep -e "($PROCESSORS)" | egrep -v egrep
else
	echo "WARNING NO PROCESSOR $PROCESSORS RUNNING!"
fi
echo "====================================================="
echo "==================SERVICES STATUS===================="
/sbin/service --status-all | grep pid
echo "====================================================="
echo "==================SERVICES RESOURCES================="
threadcount=`ps -em | wc -l`
echo "Total thread count:"$threadcount 
openfilescount=`/usr/sbin/lsof | wc -l`
echo "Total open file count:"$openfilescount
echo "====================================================="
echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
