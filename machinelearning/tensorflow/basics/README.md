# Tensorflow Basics
Tensor flow basics understanding

## Technical Stack
* Python 3.6
* Tensorflow 1.12
* Pycharm Community Edition 

## Environment Setup Instructions
* Create directory "basics" using `mkdir basics` 
* Navigate to "basics" directory and execute `envSetup.cmd` in https://github.com/AMVijay/repository/blob/master/machinelearning/tensorflow/basics/envSetup.cmd 
* Execute `python -m venv venv` to create virtual environment
* Activate the virtual environment using `basics\venv\Scripts>activate.bat`
* Upgrade PIP to latest version using command `python -m pip install -U pip --trusted-host pypi.org`
* Upgrade SetupTools to latest version using command `python -m pip install -U setuptools --trusted-host pypi.org`
* Install Tensorflow using command `pip install tensorflow --trusted-host pypi.org`


## Understanding Notes
* `tensor_constants.py` - describes how to define tensor constant node and perform a tensor add operation.
    *  `a = tf.constant(2, tf.int32)` - Create a Constant called `a` of type `int` in tensor. It denotes a node in tensor graph.
    *  `c = tf.add(a, b)` - Create a tensor node in graph (DAG) from the outcome of tensor add operation.    

* `tensor_variable.py` - describes how to define a tensor variable and use it in graph. Tensor Variable is also a node in DAG.
    * `tf.get_variable()` - is best way to define a tensor variable.
    * `tf.variable_scope("model", reuse=tf.AUTO_REUSE)` - helps to reuse the same variable a graph in session execution.  

   
## Acronyms and Definition
* DAG - Directed Acyclic Graph.
* `tf.variable` is to define a value, initialize it and change the value through out graph execution. It is used while tensor training to arrive 
* `tf.placeholder` is to feed the input values (could be from collection, file, or any external source) at the time of graph execution. We don't need to initialize any value while defining a placeholder as it values can be fed at the time of tensor session execution. 