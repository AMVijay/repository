from cryptography.fernet import Fernet

inputFilePath = "data/samplePDF.pdf"
encryptedFilePath = "data/(encrypted)samplePDF.pdf"
decryptedFilePath = "data/(decrypted)samplePDF.pdf"

CHUNK_SIZE = 64 * 1024

# Define Passkey
key = Fernet.generate_key()
# Create New Fernet Object from Cryptography library
fernetObject = Fernet(key)



# Input file handler to Read the file in Binary format
infile = open(inputFilePath, "rb")
# Output File Handler to write in Binary format
outFile = open(encryptedFilePath,'wb')

#  Read the input file
try:
    fileReadStatus = True
    while fileReadStatus:
        bytes_read = infile.read()
        if len(bytes_read) <= 0:
            fileReadStatus = False
        else:
            # Encrypt and write in output file
            outFile.write(fernetObject.encrypt(bytes_read))
finally:
    infile.close()
    outFile.close()

# Input file handler to Read the file in Binary format
infile = open(encryptedFilePath, "rb")
# Output File Handler to write in Binary format
outFile = open(decryptedFilePath,'wb')


#  Read the input file
try:
    fileReadStatus = True
    while fileReadStatus:
        bytes_read = infile.read()
        if len(bytes_read) <= 0:
            fileReadStatus = False
        else:
            # Encrypt and write in output file
            outFile.write(fernetObject.decrypt(bytes_read))
finally:
    infile.close()
    outFile.close()
