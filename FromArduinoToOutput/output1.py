import sys, os, serial, threading

def monitor():

   #ser = serial.Serial(COMPORT, BAUDRATE, timeout=0)
   ser = serial.Serial("/dev/ttyACM0", 9600, timeout=100)
   count=1;
   # write to file
   text_file = open("Pdata.log", "w")
   text_file.close();
   line1="";
   while (1):
      try:
       line = ser.readline()
       if (line != "" and line !='\r' and line !='\n' and line !='' and line !=' '):
            print "LIne= ", line[:-1]         # strip \n
            fields = line[:-1].split(':');
            print "field length = ", len(fields);
            print "fields = ", fields
            if (len(fields) == 2):
              ID = fields[0]
              print "Device ID = ",ID
              print "Time = ", fields
              #TIME = int(fields[1])
              TIME = fields[1]
              # print fields
              print "TIME ID: ", TIME
              # write to file
              #text_file = open("Pdata.log", "a+")
              #line = str(TIME) + ": " + str(ID) + "\n"
              #line =  str(ID) + str(TIME.rstrip()) + ","
              #line =  str(TIME.rstrip()) + ","
              if (count%5 != 0):
                line1= line1 + str(TIME.rstrip()) + ","
                count = count + 1;
              elif (count%5 == 0):
                #line = str(ID) + str(TIME.rstrip()) + "  count  " + str(count) + "\n";
                #line1 = line1 + str(TIME.rstrip()) + "\n";
                line1 = line1 + str(TIME.rstrip()) + "\n";
                print "\n\n\n\ \t\t whole line = ", line1; 
                # write to file
                text_file = open("Pdata.log", "a+")
                text_file.write(line1)
                text_file.close()
                line1="";
                count = count + 1;
       if (line == ","):
            # write to file
            text_file = open("Pdata.log", "a+")
            line =  "\n"
            text_file.write(line)
            text_file.close()
      except:      
       # do some other things here

       print "Stop Monitoring"

""" -------------------------------------------
MAIN APPLICATION
"""  

print "Start Serial Monitor"
print

COMPORT = "/dev/ttyACM0";
#COMPORT = 4;
#BAUDRATE = 115200
BAUDRATE = 9600

monitor()
