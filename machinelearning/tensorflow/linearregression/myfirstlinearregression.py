import collections
import tensorflow as tf
import numpy as np

# Read CSV Data

# Define Default Values with Key Value Pair
defaults = collections.OrderedDict([
    ("month", [0]),
    ("incentive", [0]),
    ("salesunit", [0])
])  # pyformat: disable


def mapdataset(line):
    items = tf.decode_csv(line,list(defaults.values()))
    # Convert the keys and items to a dict.
    pairs = zip(defaults.keys(), items)
    features_dict = dict(pairs)

    label = features_dict.pop('salesunit')
    return features_dict, label


# Read CSV/TXT File and Create Model
dataSet = tf.data.TextLineDataset("data/sales.csv")

dataSet = dataSet.map(mapdataset)

train = dataSet
test = dataSet

feature_columns = [
        # "curb-weight" and "highway-mpg" are numeric columns.
        tf.feature_column.numeric_column(key="month"),
        tf.feature_column.numeric_column(key="incentive"),
    ]

# Build the Estimator.
model = tf.estimator.LinearRegressor(feature_columns=feature_columns)

STEPS = 1000

# Build the training input_fn.
def input_train():
    return (
        # Shuffling with a buffer larger than the data set ensures
        # that the examples are well mixed.
        train.shuffle(1000).batch(128)
            # Repeat forever
            .repeat().make_one_shot_iterator().get_next()
    )


# Train the model.
# By default, the Estimators log output every 100 steps.
model.train(input_fn=input_train, steps=STEPS)

# Build the validation input_fn.
def input_test():
    return (test.shuffle(1000).batch(128)
            .make_one_shot_iterator().get_next())


# Evaluate how the model performs on data it has not yet seen.
eval_result = model.evaluate(input_fn=input_test)


# The evaluation returns a Python dictionary. The "average_loss" key holds the
# Mean Squared Error (MSE).
average_loss = eval_result["average_loss"]

# PRICE_NORM_FACTOR = 1

# Convert MSE to Root Mean Square Error (RMSE).
print("\n" + 80 * "*")
print("\nRMS error for the test set: {0:f}"
      .format(average_loss))

loss_score = eval_result["loss"]
print("Loss: {0:f}".format(loss_score))


# Run the model in prediction mode.
input_dict = {
    "month": np.array([1]),
    "incentive": np.array([10])
}
predict_input_fn = tf.estimator.inputs.numpy_input_fn(
    input_dict, shuffle=False)
predict_results = model.predict(input_fn=predict_input_fn)


# Print the prediction results.
print("\nPrediction results:")
for i, prediction in enumerate(predict_results):
    msg = ("month: {: 4d}, "
           "incentive: {: 0d}, "
           "Sales Prediction: {: 9.2f}")
    msg = msg.format(input_dict["month"][i], input_dict["incentive"][i],
                     prediction["predictions"][0])

    print("    " + msg)
print()