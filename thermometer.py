import os
import glob
import time
import logging

logging.basicConfig(format='%(asctime)s %(message)s',filename='temperature.log',level=logging.DEBUG)
base_dir='/devl/python/'
#base_dir = '/sys/bus/w1/devices/'
device_folder= glob.glob(base_dir+'28*')[0]
device_file = device_folder + '/w1_slave'
print ("Folder..." + device_folder)
print ("File..." + device_file)

def read_temp_raw():
	f = open(device_file, 'r')
	lines = f.readlines()
	f.close
	return lines

def read_temp():
	lines = read_temp_raw()
	while lines[0].strip()[-3:]!='YES':
		time.sleep(0.2)
		lines = read_temp_raw()
	equals_pos = lines[1].find('t=')
	if equals_pos != -1:
		temp_string = lines[1][equals_pos+2:]
		temp_c = float(temp_string)/1000.0
		temp_f = temp_c * 9.0 / 5.0 + 32.0
		return temp_c, temp_f

# print (read_temp_raw())
# print (read_temp())

while True:
	tempC, tempF = read_temp()
	print ("{:.2f}".format(tempC)+'....'+"{:.2f}".format(tempF))
	logging.debug(tempC )	
	time.sleep(5)