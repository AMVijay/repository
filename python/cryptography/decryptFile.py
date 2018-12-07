from cryptography.fernet import Fernet
import shutil, os,stat

def copyComplete(source, target):
    # copy content, stat-info (mode too), timestamps...
    shutil.copy2(source, target)
    # copy owner and group
    # st = os.stat(source)
    # os.chown(target, st[stat.ST_UID], st[stat.ST_GID])


inputFilePath = "data/(encrypted)test.txt"
outputFilePath = "data/test.txt"
CHUNKSIZE = 64 * 1024

# Decryption Passkey
key = b'CUW-34FKrHuiwgvrh-d7iBCdNxUcexhcCXheTA5Z9yw='

# Create Fernet Object from Cryptography library
fernetObject = Fernet(key)

# copyComplete(inputFilePath, outputFilePath)

# Input File Handler to read file in binary format
infile = open(inputFilePath, "rb")
# Output File Handler to write file in binary format
outfile = open(outputFilePath,'wb')

# Read and Write the file
try:
    fileReadStatus = True
    while fileReadStatus:
        bytes_read = infile.read(CHUNKSIZE)
        if len(bytes_read) <= 0:
            fileReadStatus = False
        else:
            # Write the Decrypted File content
            outfile.write(fernetObject.decrypt(bytes_read))
finally:
    infile.close()
    outfile.close()
    os.remove(inputFilePath)

