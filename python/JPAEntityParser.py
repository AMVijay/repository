#@author Vijay

from os import walk
from os.path import splitext, join
import re

print("Started Parsing Entity")

#ENTITY_IDENTIFIER = "(.|\n)*@Entity(.|\n)*"   This is for match scenario only, not search
ENTITYNAME_IDENTIFIER = "@Entity"
TABLENAME_IDENTIFIER = "@Table\(name = \"(.*)\"\)"
SEQUENCENAME_IDENTIFIER = "sequence_name.*\"([A-Z|_]*)\""
INCREMENTSIZE_IDENTIIER  = "increment_size.*\"(.*)\""
OPTIMIZERNAME_IDENTIFIER = "optimizer.*\"(.*)\""

def fetchOptimizerName(entityFileContent):
    optimizerName = None
    searchResults = re.search(OPTIMIZERNAME_IDENTIFIER,entityFileContent)
    if searchResults is not None:
        optimizerName = searchResults.group(1)
    return optimizerName

def fetchIncrementSizeValue(entityFileContent):
    incrementSize = None
    searchResults = re.search(INCREMENTSIZE_IDENTIIER,entityFileContent)
    if searchResults is not None:
        incrementSize = searchResults.group(1)
    return incrementSize
def fetchSequenceName(entityFileContent):
    sequenceName = None
    searchResults = re.search(SEQUENCENAME_IDENTIFIER,entityFileContent)
    if searchResults is not None:
        sequenceName = searchResults.group(1)
    return sequenceName

def fetchTableName(entityFileContent):
    tableName = None
    searchResults = re.search(TABLENAME_IDENTIFIER, entityFileContent)
    if searchResults is not None:
        tableName = searchResults.group(1)
    return tableName

def fetchEntityFiles(path):
    entityFiles = []
    for root,dirs,files in walk(path):
        for file in files:
            fileContent = open(root + "\\" + file,'r').read()
            matchedString = re.search(ENTITYNAME_IDENTIFIER, fileContent)
            if matchedString is not None:
                tableName = fetchTableName(fileContent)
                sequenceName = fetchSequenceName(fileContent)
                optimizerName = fetchOptimizerName(fileContent)
                incrementSize = fetchIncrementSizeValue(fileContent)
                print(root + "\\" + file, file, tableName, sequenceName, optimizerName, incrementSize)

    return entityFiles

#filePath = "C:\\Vijay\\iPlus\\R1B4_AMS\\sims-model\\src\\main\\java\\com\\tms\\incentives\\model\\program\\"
filePath = "C:\\Vijay\\iPlus\\R1B4_AMS\\sims-model\\src\\main\\java\\com\\tms\\incentives"
fetchEntityFiles(filePath)



