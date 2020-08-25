
chmod +x *.sh
cp Sserver.sh /etc/rc.d/rc3.d/.
cp Kserver.sh /etc/rc.d/rc3.d/.

cp fishman/install/libfmapiv100.so /lib64/.

NETSEALHOME="/opt/infosec/NetSeal"

#check NetSeal home
if [ -e "$NETSEALHOME" ];
then
   echo "Clean directory file"	
   rm -rf $NETSEALHOME
else
   echo "Create directory ..."	
fi

#Create NetSeal home
mkdir -p $NETSEALHOME

echo " copy jdk1.8.0_121 ..."
cp -r jdk1.8.0_121 "$NETSEALHOME"/

echo " copy appserver ... "
cp -r appserver "$NETSEALHOME"/

echo " copy tomcat ..."
cp -r tomcat "$NETSEALHOME"/

echo " copy conf ... "
cp -r conf "$NETSEALHOME"/

echo " copy log ... "
cp -r log "$NETSEALHOME"/

echo " copy photo ... "
cp -r photo "$NETSEALHOME"/

echo " copy crl ... "
cp -r crl "$NETSEALHOME"/

echo " copy key ... "
cp -r key "$NETSEALHOME"/

echo " copy cert ..."
cp -r cert "$NETSEALHOME"/

echo " copy license ..."
cp -r license "$NETSEALHOME"/

echo " copy ssl ..."
cp -r ssl "$NETSEALHOME"/

echo " copy report ..."
cp -r report "$NETSEALHOME"/

echo " copy ha ..."
cp -r ha "$NETSEALHOME"/

echo " copy backup ..."
cp -r backup "$NETSEALHOME"/

echo " copy seal ..."
cp -r seal "$NETSEALHOME"/

echo " copy pdf ..."
cp -r pdf "$NETSEALHOME"/

echo " copy pdf_stamp ..."
cp -r pdf_stamp "$NETSEALHOME"/

echo " copy pdf_template ..."
cp -r pdf_template "$NETSEALHOME"/

echo " copy ofd ..."
cp -r ofd "$NETSEALHOME"/

echo " copy ofd_stamp ..."
cp -r ofd_stamp "$NETSEALHOME"/

echo " copy rads ..."
cp -r rads "$NETSEALHOME"/

echo " copy tmp ..."
cp -r tmp "$NETSEALHOME"/

echo " copy rpm ..."
cp -r rpm "$NETSEALHOME"/

echo " copy fishman ..."
cp -r fishman "$NETSEALHOME"/

echo " copy rpm_check ..."
cp -r rpm_check "$NETSEALHOME"/

echo " copy stamp"
cp -r stamp "$NETSEALHOME"/

echo " copy watchdog ..."
cp -r watchdog "$NETSEALHOME"/

cd watchdog
./setup.sh

