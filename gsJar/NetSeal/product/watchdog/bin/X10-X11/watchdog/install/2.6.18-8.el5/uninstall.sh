rpm -e lm_sensors
rm -rf /etc/rc3.d/S27WatchDog
rm -rf /etc/rc3.d/K27WatchDog
rm -rf /etc/sensors3.conf
rm -rf /lib/modules/`uname -r`/kernel/drivers/hwmon/w83793.ko
