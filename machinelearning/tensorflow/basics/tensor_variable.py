import tensorflow as tf

LOOP_COUNTER = 5


def tensor_add(a, b):
    return tf.add(a, b)


def tensor_train(b):
    with tf.variable_scope("model", reuse=tf.AUTO_REUSE):
        a = tf.get_variable('variable1',
                            shape=1,
                            initializer=tf.truncated_normal_initializer(),
                            trainable=True)
    c = None
    for counter in range(LOOP_COUNTER):
        c = tensor_add(a, b)
        a = a + 0.1
    return c


with tf.Session() as session:
    c = tensor_train(tf.constant(2, tf.float32))
    tf.global_variables_initializer().run()
    print(c.eval())
