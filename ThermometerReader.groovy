//Get the directory name of the device
def base_dir = '/devl/python/'
def device_folder = base_dir + '28abcdefg'
def deviceFilePath = device_folder + '/w1_slave'
println('Folder...'+deviceFilePath)

float temp = readTemp(deviceFilePath)
println ((convertToCelsius(temp)).round(2) + " degrees Celsius")
println ((convertToFarenheit(temp)).round(2) + " degrees Farenhheit")

def readTemp(def deviceFilePath) {
	def deviceFile = new File(deviceFilePath)
	def rawTemp

	deviceFile.eachLine {
		if (it.contains("YES")) {return}
		rawTemp = parseTemp(it).toFloat()
	}	
	return rawTemp
}

def parseTemp(deviceLine) {
	def tempLine = new ConfigSlurper().parse(deviceLine)
	tempLine.t    
}

def convertToCelsius(temperature) {
	(temperature / 1000.0) 
}

def convertToFarenheit(temperature) {
	((temperature/1000.0) * 9.0/5.0 + 32.0) 
}


