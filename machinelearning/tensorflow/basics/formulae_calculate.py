import tensorflow as tf


def calculate_formulae(param):
    # The area of triangle whose three sides are (a, b, c) is square root of s(s-a)(s-b)(s-c) where s=(a+b+c)/2
    a = param[:,0]
    b = param[:,1]
    c = param[:,2]
    s = (a + b + c)/2
    temp = s * (s - a) * (s - b) * (s - c)
    result = tf.sqrt(temp)
    return result


with tf.Session() as session:
    variable1 = tf.constant([
      [5.0, 3.0, 7.1],
      [2.3, 4.1, 4.8]
    ])
    result = session.run(calculate_formulae(variable1))
    print(result)