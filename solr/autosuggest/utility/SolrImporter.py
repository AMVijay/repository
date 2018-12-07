from urllib import request, parse

# Variable declaration
headers = {'Content-type': 'application/json'}
url="http://localhost:8983/solr/carmodels/update/json/docs?commit=true&wt=json"
jsonFile = open("data/outfile.json",'r')

requestObject = request.Request(url, jsonFile, headers)

with request.urlopen(requestObject) as response:
    print(response.read().decode())
