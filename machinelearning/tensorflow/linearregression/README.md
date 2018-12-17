# POC for Linear Regression using TensorFlow
Example of Linear Regression Implementation using Tensorflow 

## Technical Stack
* Python 3.6
* Tensorflow 1.12
* Pycharm Community Edition

## Environment Setup Instructions
* Execute `envSetup.cmd` in https://github.com/AMVijay/repository/blob/master/machinelearning/tensorflow/envSetup.cmd 
* create directory "linearregression" using `mkdir linearregression`
* Navigate to "linearregression" directory and execute `python -m venv venv` to create virtual environment
* Activate the virtual environment using `linearregression\venv\Scripts>activate.bat`
* Upgrade PIP to latest version using command `python -m pip install -U pip --trusted-host pypi.org`
* Upgrade SetupTools to latest version using command `python -m pip install -U setuptools --trusted-host pypi.org`
* Install Tensorflow using command `pip install tensorflow --trusted-host pypi.org`

## Notes
* Refer [Link](https://www.tensorflow.org/guide/datasets#csv-files) for importing CSV files.
* Yet to explore [implementation](https://www.guru99.com/linear-regression-tensorflow.html)


### What is Linear Regression: 

Linear Regression is mathematical approach for prediction based on sequential model data of same type and structure. 

Example dataset: 
* Sales Data 
    * Jan – 1000 units, Feb – 600 units, Mar – 500 units, Apr – 700 units like that. 
* Weather Data 
    * 00:00 – 48 F, 01:00 – 47 F, 02:00 – 46 F, 03:00 – 48 F 

Linear Regression provides us the approach and methodologies to arrive next data from the previous data set available.
