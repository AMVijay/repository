# Auto Suggest/Complete using Solr Search Engine

This POC is demonstrate how auto suggest/complete can be implemented using Solr Search Engine

## Technical Stack
* Solr 7.4.0
* Python - Utility to import sample data
* JQuery latest version
* HTML 5

## Steps 

### Step 1
Create Solr Collection from the predefined schema.
`solr.cmd create_core -c carmodels -d ./solrconfig/conf -p 8983`

### Step 2
Run the Python Code Utility to import data into solr

`python utility\JsonCreationFromCSV.py` to create JSON file from CSV

`python utility\SolrImporter.py` to import data into solr collection/core carmodels

### Step 3

Test Search Suggestion by validateAutoSuggest.html in browser.


## Reference
* https://stackoverflow.com/questions/17400639/sorting-solr-autosuggestion-on-custom-score-field


# Notes on Stream
http://localhost:8983/solr/twokeywords/suggest?suggest=true&suggest.build=true&suggest.dictionary=mySuggester&suggest.q=Toyota


select(rollup(
				hashJoin(
						search(vehicle, q=*:*, fl=”v_id,model_name”,qt=”/export”, sort=”v_id asc”), 
						hashed=search(defects, q=*:*, fl=”v_id,defect_id”,qt=”/export”, sort=”v_id asc”), on=”v_id”),
						over=”model_name”,count(*)
				),count(*) as defect_count_s,model_name as model_name_
			s)
			
			
http://localhost:8983/solr/carmodels/stream?rollup(search(carmodels, fl="modelName:Highlander") over="brandNm")&indent=on&q=*:*&wt=json



curl --data-urlencode 'expr=search(carmodels,q="*:*",fl="brandNm,modelName",sort="_version_ asc",rows="10")' http://localhost:8983/solr/carmodels/stream







