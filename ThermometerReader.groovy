def base_dir = '/devl/python/'
def device_folder = base_dir + '28abcdefg'
def device_file = device_folder + '/w1_slave'
println('Folder...'+device_file)
println read_raw_temp(device_file)
read_temp(device_file)

def read_raw_temp(def device_file) {
	def tempFile = new File(device_file)	
	tempFile.text

}

def read_temp(def device_file) {
	lines = read_raw_temp(device_file)
	println lines
	println "Loop started"
	while (!(lines.contains("YES"))) {
		sleep 5
		lines = read_raw_temp(device_file)
		println lines
	}
	println "Loop ended"
	
}

