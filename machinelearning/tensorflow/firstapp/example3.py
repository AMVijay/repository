# Import tensorflow Package
import tensorflow as tf

# Initialize the placeholder
placeholder1 = tf.placeholder(tf.int32)
placeholder2 = tf.placeholder(tf.int32)

# Initialize tensorgraph for placeholder addition
add = tf.add(placeholder1,placeholder2)
multiply = tf.multiply(placeholder1, placeholder2)

# Get Tensor Session to execute the graph
session = tf.Session()

# Pass the Model at runtime
feed_dict ={placeholder1: [[2,4],[5,6]], placeholder2: [[4,8],[5,6]]}

# Execute the graph
print(session.run(add , feed_dict))
print(session.run(multiply, feed_dict))

# Close the session
session.close()