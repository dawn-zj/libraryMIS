rpm -e lm_sensors
rm -rf /etc/rc3.d/S27WatchDog
rm -rf /etc/rc3.d/K27WatchDog
rm -rf /etc/sensors.conf
rm -rf /lib/modules/`uname -r`/kernel/drivers/hwmon/w83627ehf.ko
