# Cryptography POC
POC using Python Cryptography library

## Technical Stack
* Python 3.7
* Cryptography library (https://cryptography.io/en/latest/)

## Setup Instructions
* Execute `envSetup.cmd`, make sure Python Installation path is correct.
* Create venv using `python -m venv venv`
* Activate venv `.\venv\Scripts>activate.bat`
* Upgrade PIP to latest version using `python -m pip install -U pip`
* Upgrade Setuptool package using `python -m pip install -U setuptools`
* Install Cryptography library using `pip install cryptography`


## Notes
* In Cryptography library, `cyptography.fernet.Fernet` class is the one provides encryption/decryption functionality based on passkey.
