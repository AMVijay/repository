import tensorflow as tf
import numpy as np

column = tf.feature_column.numeric_column('month', shape=1)
model = tf.estimator.LinearRegressor(feature_columns=[column])

# Train the Model
train_input = tf.estimator.inputs.numpy_input_fn(
    x={"month": np.array([1.0, 2.0, 3.0, 4.0, 5.0])},
    y=np.array([1000, 500, 500, 800, 1000]),
    shuffle=False,
    num_epochs=None
)

model.train(train_input, steps=100000)

# Predict the Model for an input
predict_input = tf.estimator.inputs.numpy_input_fn(
    x={"month": np.array([6, 7])},
    num_epochs=1,
    shuffle=False
)

# eval_result = model.evaluate(input_fn=predict_input)
# average_loss = eval_result["average_loss"]
# print("Average Loss :: " + average_loss)
# loss_score = eval_result["loss"]
# print("Loss :: " + loss_score)

results = model.predict(input_fn=predict_input)

for value in results:
    print(value['predictions'])
