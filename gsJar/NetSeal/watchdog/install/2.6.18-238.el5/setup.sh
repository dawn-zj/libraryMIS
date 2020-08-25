path=`pwd`
cp ./w83627ehf.ko /lib/modules/`uname -r`/kernel/drivers/hwmon

rpm -ihv lm_sensors-2.10.7-9.el5.x86_64.rpm

rm -rf /etc/sensors*.conf
cp sensors.conf.supermicro /etc/sensors.conf

cd $path
chmod u+x S27WatchDog
chmod u+x K27WatchDog
cp S27WatchDog /etc/rc3.d/
cp K27WatchDog /etc/rc3.d/

echo "install is ok"
