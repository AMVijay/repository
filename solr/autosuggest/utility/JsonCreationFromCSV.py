# Author: Vijay (AM.Vijay@gmail.com)

import csv
import json

csvfile = open('data\export.csv', 'r')
jsonfile = open('data\outfile.json', 'w')

fieldnames = ("id","brandNm","modelName","modelYear","modelCode")
reader = csv.DictReader(csvfile, fieldnames)

# Create Complete JSON Structure from CSV
out = json.dumps( [ row for row in reader ] )
jsonfile.write(out)
jsonfile.close()




