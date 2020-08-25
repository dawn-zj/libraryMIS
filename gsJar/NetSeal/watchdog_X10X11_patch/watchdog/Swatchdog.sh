
agetty /dev/ttyS1 9600 vt100 &
sleep 3
pid=`ps -ef | grep agetty | grep ttyS1 | awk '{ print $2}'`
kill -9 $pid

modprobe ipmi_msghandler 
modprobe ipmi_si
modprobe acpi_ipmi
modprobe ipmi_devintf
modprobe power_meter

export PATH=/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin
cd  /opt/infosec/NetSeal/watchdog/bin/
./start.sh &
