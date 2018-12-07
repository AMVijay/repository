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







