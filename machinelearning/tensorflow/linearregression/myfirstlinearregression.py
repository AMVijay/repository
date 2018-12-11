import numpy as np
import tensorflow as tf


STEPS = 1000
PRICE_NORM_FACTOR = 1000

# Load CSV Data into Model
basedata = tf.data.TextLineDataset("data/sales.csv")

train = basedata
test = basedata

print("Test Data Created")

feature_columns = [
        # "curb-weight" and "highway-mpg" are numeric columns.
        tf.feature_column.numeric_column(key="month"),
        tf.feature_column.numeric_column(key="salesunit"),
    ]

# Build the Estimator.
model = tf.estimator.LinearRegressor(feature_columns=feature_columns)

# Train the model.
# By default, the Estimators log output every 100 steps.
model.train(input_fn=train, steps=STEPS)

# Evaluate how the model performs on data it has not yet seen.
eval_result = model.evaluate(input_fn=test)

# The evaluation returns a Python dictionary. The "average_loss" key holds the
# Mean Squared Error (MSE).
average_loss = eval_result["average_loss"]

# Convert MSE to Root Mean Square Error (RMSE).
print("\n" + 80 * "*")
print("\nRMS error for the test set: ${:.0f}"
      .format(PRICE_NORM_FACTOR * average_loss ** 0.5))

# Run the model in prediction mode.
input_dict = {
    "month": np.array([5, 6]),
    "salesUnit": np.array([30, 40])
}
predict_input_fn = tf.estimator.inputs.numpy_input_fn(
    input_dict, shuffle=False)
predict_results = model.predict(input_fn=predict_input_fn)

# Print the prediction results.
print("\nPrediction results:")
for i, prediction in enumerate(predict_results):
    msg = ("month: {: 4d}lbs, "
           "salesUnit: {: 0d}mpg, "
           "Prediction: ${: 9.2f}")
    msg = msg.format(input_dict["month"][i], input_dict["salesUnit"][i],
                     PRICE_NORM_FACTOR * prediction["predictions"][0])

    print("    " + msg)
print()
