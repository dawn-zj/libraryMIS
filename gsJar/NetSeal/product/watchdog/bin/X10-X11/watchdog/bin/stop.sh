pid=`ps -ef|grep 'cn.com.infosec.watchdog.Startup start'|grep -v 'grep'|awk '{print $2}'`
kill -9 ${pid}
