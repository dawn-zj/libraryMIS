#! /bin/bash

mtypes="1000 2000 10000"
mt="" 

echo "select device model"
select atype in $mtypes; 
do
	mt=$atype
	if [ $mt ]
	then
		echo "NetSeal device model is $mt"
		break
	else
		echo "select device model invalid"
		continue
	fi
	break       
done

mmt=""
mmtl=0
while [ "$mmtl" != "7" ]
do
	echo "input host model code(7 bit):"
	read mmt
	mmtl=`echo ${#mmt}`
done

mc=""
mcl=0
while [ "$mcl" != "8" ]
do
	echo "input config code(8 bit):"
	read mc
	mcl=`echo ${#mc}`
done

msn=""
while [ "$msn" = "" ]
do
	echo "input device sn"
	read msn
done
	
echo $mt"<>"$mmt"<>"$mc"<>"$msn > config.txt

