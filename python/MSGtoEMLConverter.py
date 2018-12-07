# Author: Vijay
# Reference http://stackoverflow.com/questions/26322255/parsing-outlook-msg-files-with-python
# Reference https://msdn.microsoft.com/en-us/library/microsoft.office.interop.outlook.mailitem_properties.aspx
import mimetypes
import os
from email import generator, encoders
from email.mime.audio import MIMEAudio
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart, MIMEBase
from email.mime.text import MIMEText
from email.utils import format_datetime
from os import walk
from re import search

import win32com.client

outlook = win32com.client.Dispatch("Outlook.Application").GetNamespace("MAPI")
PR_SMTP_ADDRESS = "http://schemas.microsoft.com/mapi/proptag/0x39FE001E"
rootDirectory = "C:/Vijay/emailBackup/2017/09/"
READ_CHAR_OUTLOOK = "r"
ANY_CHARACTER = "."
ATTACHMENT_TEMP_DIR = "C:/Vijay/temp/"

for root, dirs, files in walk(os.path.abspath(rootDirectory)):
    for file in files:
        outlookFile = os.path.join(root, file)

        if outlookFile.endswith(".msg"):
            try:
                print("Converting Outlook Message " + outlookFile)
                msg = outlook.OpenSharedItem(outlookFile)

                newMsg = MIMEMultipart()
                newMsg['From'] = msg.SenderName + '<' + msg.SenderEmailAddress + '>'
                newMsg['Date'] = format_datetime(msg.SentOn, usegmt=False)
                newMsg['Subject'] = msg.Subject
                for recipient in msg.Recipients:
                    if recipient.Type == 1:
                        try:
                            if recipient.Name != recipient.PropertyAccessor.GetProperty(PR_SMTP_ADDRESS):
                                newMsg['To'] = recipient.Name + '<' + recipient.PropertyAccessor.GetProperty(PR_SMTP_ADDRESS) + '>'
                            else:
                                newMsg['To'] = recipient.PropertyAccessor.GetProperty(PR_SMTP_ADDRESS)
                        except:
                            print("Not able to extract TO Address")
                    elif recipient.Type == 2:
                        if recipient.Name != recipient.PropertyAccessor.GetProperty(PR_SMTP_ADDRESS):
                            newMsg['Cc'] = recipient.Name + '<' + recipient.PropertyAccessor.GetProperty(PR_SMTP_ADDRESS) + '>'
                        else:
                            newMsg['Cc'] = recipient.PropertyAccessor.GetProperty(PR_SMTP_ADDRESS)
                    elif recipient.Type == 3:
                        if recipient.Name != recipient.PropertyAccessor.GetProperty(PR_SMTP_ADDRESS):
                            newMsg['Bcc'] = recipient.Name + '<' + recipient.PropertyAccessor.GetProperty(PR_SMTP_ADDRESS) + '>'
                        else:
                            newMsg['Bcc'] = recipient.PropertyAccessor.GetProperty(PR_SMTP_ADDRESS)
                searchResults = search(ANY_CHARACTER, msg.Body)
                if searchResults is not None:
                    if hasattr(msg, "HTMLBody"):
                        mailContent = MIMEText(msg.HTMLBody, 'html')
                        newMsg.attach(mailContent)
                    else:
                        print("no content")

                count_attachments = msg.Attachments.Count
                if count_attachments > 0:
                    for attachment in msg.Attachments:
                        print("Attachment Type :: " + str(attachment.Type))
                        if attachment.Type != 6:
                            print("Attachment FileName :: " + attachment.FileName)
                            attachment.SaveASFile(os.path.join(ATTACHMENT_TEMP_DIR, attachment.FileName))
                            attachmentFile = os.path.join(ATTACHMENT_TEMP_DIR, attachment.FileName)

                            # Guess the content type based on the file's extension.  Encoding
                            # will be ignored, although we should check for simple things like
                            # gzip'd or compressed files.
                            ctype, encoding = mimetypes.guess_type(attachmentFile)
                            if ctype is None or encoding is not None:
                                # No guess could be made, or the file is encoded (compressed), so
                                # use a generic bag-of-bits type.
                                ctype = 'application/octet-stream'
                            maintype, subtype = ctype.split('/', 1)
                            if maintype == 'text':
                                with open(attachmentFile) as fp:
                                    # Note: we should handle calculating the charset
                                    part = MIMEText(fp.read(), _subtype=subtype)
                            elif maintype == 'image':
                                with open(attachmentFile, 'rb') as fp:
                                    part = MIMEImage(fp.read(), _subtype=subtype)
                            elif maintype == 'audio':
                                with open(attachmentFile, 'rb') as fp:
                                    part = MIMEAudio(fp.read(), _subtype=subtype)
                            else:
                                with open(attachmentFile, 'rb') as fp:
                                    part = MIMEBase(maintype, subtype)
                                    part.set_payload(fp.read())
                                # Encode the payload using Base64
                                encoders.encode_base64(part)
                            # Set the filename parameter
                            part.add_header('Content-Disposition', 'attachment', filename=attachment.FileName)
                            newMsg.attach(part)
                            # Remove the attachment from Temp Location
                            if (os.path.exists(attachmentFile)):
                                os.remove(attachmentFile)

                outfile_name = os.path.join(root, file.replace(".msg", ".eml"))
                with open(outfile_name, 'w') as outfile:
                    gen = generator.Generator(outfile)
                    gen.flatten(newMsg)
                print(outfile_name + " Message Created")
            except RuntimeError:
                print("Processing Error while parsing " + outlookFile)
