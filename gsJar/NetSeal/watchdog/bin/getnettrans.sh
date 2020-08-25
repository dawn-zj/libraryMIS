eths=`ifconfig | grep eth | awk '{ print $1 }'`
cd bin 2>/dev/null
if [ "$eths" != "" ]
then
	for eth in $eths
	do
		l=`./tcptrans.sh $eth`
		echo "[("$eth")":$l"]"
	done
fi
