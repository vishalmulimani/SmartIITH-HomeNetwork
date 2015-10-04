set firewall	192.168.1.144
set username    pi
set password    iithiith
set prompt	#

#set filename	[ lrange $argv 4 4 ]

match_max 50000

spawn ssh -l $username $firewall
expect -timeout 1 "yes/no" { send "yes\r" }
expect -timeout 1 "assword:"
send "$password\r"
expect -timeout 1 "$prompt>"
send "sudo python LED_rpi.py\r"
expect -timeout 15 "$"
send "exit\r"
exit
