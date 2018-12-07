# Author: Vijay (AM.Vijay@gmail.com)

import csv
import json
from urllib import request, parse

csvfile = open('data\export.csv', 'r')
jsonfile = open('data\\twokeywords.json', 'w')

fieldnames = ("id","brandNm","modelName","modelYear","modelCode")
reader = csv.DictReader(csvfile, fieldnames)

# Create Model + Model Year Structure
content = []
for row in reader:
    content.append(row['brandNm'] + " " + row['modelName'])
    content.append(row['modelName'] + " " + row['modelYear'])
    content.append(row['modelYear'] + " " + row['modelName'])
    content.append(row['brandNm'] + " " + row["modelYear"])

finalContent = list(set(content))
data = {}
data['keyword'] = finalContent
out = json.dumps(data)
jsonfile.write(out)
jsonfile.close()


# Variable declaration
headers = {'Content-type': 'application/json'}
url="http://localhost:8983/solr/twokeywords/update/json/docs?commit=true&wt=json"
solrInputFile = open("data/twokeywords.json",'r')

requestObject = request.Request(url, solrInputFile, headers)

with request.urlopen(requestObject) as response:
    print(response.read().decode())