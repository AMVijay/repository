from os import walk, remove, urandom
import base64
from cryptography.fernet import Fernet
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC

# ChuckSize to read
# CHUNK_SIZE = 64 * 1024

ENCRYPTED = "(encrypted)"

# Decryption Passkey
KEY = b'CUW-34FKrHuiwgvrh-d7iBCdNxUcexhcCXheTA5Z9yw='

# Create Fernet Object from Cryptography library
FERNET_OBJECT = Fernet(KEY)

def deleteFile(filePath):
    try:
        remove(filePath)
    except:
        print("Remove Failed")

def decryptFile(inputFilePath, inputFileName):
    print("Decrypting File :: " + inputFilePath + "\\" + inputFileName)
    print("Output :: " + inputFilePath + "\\" + inputFileName[len(ENCRYPTED):None]);
    # Input File Handler to read file in binary format
    infile = open(inputFilePath + "\\" + inputFileName, "rb")
    # Output File Handler to write file in binary format
    outfile = open(inputFilePath + "\\" + inputFileName[len(ENCRYPTED):None], 'wb')

    # Read and Write the file
    try:
        filereadstatus = True
        while filereadstatus:
            bytes_read = infile.read()
            if len(bytes_read) <= 0:
                filereadstatus = False
            else:
                # Write the Decrypted File content
                outfile.write(FERNET_OBJECT.decrypt(bytes_read))
    finally:
        infile.close()
        outfile.close()

def decryptFolder(folderPath):
    for root, dirs,files in walk(folderPath):
        for file in files:
            decryptFile(root, file)
            deleteFile(root + "\\" + file)


folderPath = "C:\\Users\\135670\\Desktop\\test"
decryptFolder(folderPath)
