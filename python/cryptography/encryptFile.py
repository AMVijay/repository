from cryptography.fernet import Fernet
import shutil, os,stat


def copyComplete(source, target):
    # copy content, stat-info (mode too), timestamps...
    shutil.copy2(source, target)
    # copy owner and group
    # st = os.stat(source)
    # os.chown(target, st[stat.ST_UID], st[stat.ST_GID])

CHUNKSIZE = 64 * 1024
inputFilePath = "data/test.txt"
outputFilePath = "data/(encrypted)test.txt"

# Define Passkey
# key = Fernet.generate_key()
key = b'CUW-34FKrHuiwgvrh-d7iBCdNxUcexhcCXheTA5Z9yw='
# Create New Fernet Object from Cryptography library
fernetObject = Fernet(key)

# copyComplete(inputFilePath, outputFilePath)

# Input file handler to Read the file in Binary format
infile = open(inputFilePath, "rb")
# Output File Handler to write in Binary format
outfile = open(outputFilePath,'wb')

#  Read the input file
try:
    fileReadStatus = True
    while fileReadStatus:
        bytes_read = infile.read(CHUNKSIZE)
        if len(bytes_read) <= 0:
            fileReadStatus = False
        else:
            # Encrypt and write in output file
            outfile.write(fernetObject.encrypt(bytes_read))
finally:
    infile.close()
    outfile.close()
    os.remove(inputFilePath)

