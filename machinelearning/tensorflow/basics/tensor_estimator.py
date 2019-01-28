import tensorflow as tf

column_names = ['MPG', 'Cylinders', 'Displacement', 'Horsepower', 'Weight','Acceleration', 'ModelYear', 'Origin', 'Carname']

FIELD_DEFAULTS = [[0.0], [0.0], [0.0], [0.0], [0.0], [0.0], [0.0], [0.0],['test']]

vocab_size = 5000

# Tensor flow Feature Columns
FEATURE_COLUMNS = [
    # tf.feature_column.numeric_column(column_names[0]),
    tf.feature_column.numeric_column(key=column_names[1],dtype=tf.float32),
    tf.feature_column.numeric_column(key=column_names[2],dtype=tf.float32),
    tf.feature_column.numeric_column(key=column_names[3],dtype=tf.float32),
    tf.feature_column.numeric_column(key=column_names[4],dtype=tf.float32),
    tf.feature_column.numeric_column(key=column_names[5],dtype=tf.float32),
    tf.feature_column.numeric_column(key=column_names[6],dtype=tf.float32),
    tf.feature_column.numeric_column(key=column_names[7],dtype=tf.float32),
    tf.feature_column.categorical_column_with_hash_bucket(key=column_names[8], hash_bucket_size=400)
]


# Tensor flow Label Column
LABEL = tf.feature_column.numeric_column(column_names[0])

# Define Model from Tensor Estimator - High Level API
linear_regression_model = tf.estimator.LinearRegressor(feature_columns=FEATURE_COLUMNS)

# Read Data
# https://archive.ics.uci.edu/ml/machine-learning-databases/auto-mpg/auto-mpg.data
dataSet = tf.data.TextLineDataset("data/auto-mpg.csv")


def read_data(line):
    line_data = tf.decode_csv(line, record_defaults=FIELD_DEFAULTS, )
    pairs = zip(column_names,line_data)
    features_dict = dict(pairs)
    label_dict = features_dict.pop('MPG')
    return features_dict, label_dict


dataSet = dataSet.map(read_data)

training_data = dataSet


# Train the Model
# Build the training input_fn.
def input_train():
    return (
        # Shuffling with a buffer larger than the data set ensures
        # that the examples are well mixed.
        training_data.batch(128)
            # Repeat forever
            .repeat().make_one_shot_iterator().get_next()
    )


# Train the model.
# By default, the Estimators log output every 100 steps.
linear_regression_model.train(input_fn=input_train, steps=1000)