# This program will rename the image file to Data and time taken of the image in format <YYYY_MM_DD_HH24_MI_SS>.

import exifread
from datetime import datetime, date, time
import shutil
import os 
srcDirectory = "F:\\Vijay\\Pictures_Videos\\2014"
for dirName, subDirList, fileList in os.walk(srcDirectory):
    for fName in fileList:
        isPictureCopied = False
        print(fName)
        file = open(dirName + "\\" + fName,'rb')
        #print(file.name)
        tags = exifread.process_file(file, details=False)
        #print(len(tags))
        for tag in tags.keys():
            #if tag not in ('JPEGThumbnail', 'TIFFThumbnail', 'Filename', 'EXIF MakerNote'):
                #print("Key: %s, value %s" % (tag, tags[tag]))
            if tag.find("DateTimeOriginal") != -1:
                dateTaken = datetime.strptime(str(tags[tag]),'%Y:%m:%d %H:%M:%S')
                dateTakenStr = dateTaken.strftime('%Y_%m_%d_%H_%M_%S')
                print(dateTakenStr)
                #print(os.path.splitext(fName)[1])
                shutil.copy2(dirName + "\\" + fName,"F:\\Vijay\\Pictures\\Organized\\2014\\"+dateTakenStr+os.path.splitext(fName)[1],follow_symlinks=False)
                isPictureCopied = True
                #shutil.copy(file,file)
        file.close()
        if isPictureCopied:
            print(file.name)
            os.remove(dirName + "\\" + fName)
            print("File Removed")
        else :
            print("File Not Removed")