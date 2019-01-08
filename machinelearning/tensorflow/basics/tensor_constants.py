import tensorflow as tf

a = tf.constant(2, tf.int32)
b = tf.constant(3, tf.int32)
c = tf.add(a, b)

print("Before Execution")
print("a constant value is ", a)
print("b constant value is ", b)
print("c value is ", c)

with tf.Session() as session:
    print("In Tensor Session execution")
    print("a constant value in Session Run :: ", session.run(a))
    print("b constant value in Session Run :: ", session.run(b))
    print("c Value in Session Run :: ", session.run(c))
