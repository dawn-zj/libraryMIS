path=`pwd`
cp ./w83793.ko /lib/modules/`uname -r`/kernel/drivers/hwmon

#rpm -ihv lm_sensors-3.0.2-1.fc10.i386.rpm
rpm -ihv lm_sensors-2.10.7-4.el5.i386.rpm

#cd /lib/modules/`uname -r`/kernel/drivers/hwmon

#insmod w83793.ko

rm -rf /etc/sensors*.conf
cp sensors.conf.supermicro /etc/sensors.conf

cd $path
chmod u+x S27WatchDog
chmod u+x K27WatchDog
cp S27WatchDog /etc/rc3.d/
cp K27WatchDog /etc/rc3.d/

echo "install is ok"
