import re

pattern = re.compile("Exception|\sat\s.*\.java:.*",re.IGNORECASE)

with open("C:\\Users\\135670\\Downloads\\aror_ms1.log", "r") as file:
	for line in file:
		if(pattern.search(line)):
			print(line)




