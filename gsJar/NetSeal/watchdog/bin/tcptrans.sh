in1=`cat /proc/net/dev | grep $1 | sed "s/$1://" | awk '{ printf "%.f\n",$1 }'`
out1=`cat /proc/net/dev | grep $1 | sed "s/$1://" | awk '{ printf "%.f\n",$9 }'`
sleep 1
in2=`cat /proc/net/dev | grep $1 | sed "s/$1://" | awk '{ printf "%.f\n",$1 }'`
out2=`cat /proc/net/dev | grep $1 | sed "s/$1://" | awk '{ printf "%.f\n",$9 }'`
in=`expr $in2 - $in1`
out=`expr $out2 - $out1`
echo "Receive:"$in"bytes  Transmit:"$out"bytes"
