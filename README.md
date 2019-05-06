## doddle-model examples
This repository contains code examples for the [doddle-model](https://github.com/picnicml/doddle-model) machine learning library.

### Table of Contents

#### 1. Feature Preprocessing
* [Standard Scaler](src/main/scala/io/picnicml/doddlemodel/examples/preprocessing/StandardScalerExample.scala)
* [One-Hot Encoder](src/main/scala/io/picnicml/doddlemodel/examples/preprocessing/OneHotEncoderExample.scala)
* [Mean Value Imputation](src/main/scala/io/picnicml/doddlemodel/examples/impute/MeanValueImputerExample.scala)
* [Most Frequent Value Imputation](src/main/scala/io/picnicml/doddlemodel/examples/impute/MostFrequentValueImputerExample.scala)

#### 2. Metrics
* [Classification Metrics](https://github.com/picnicml/doddle-model/blob/master/src/main/scala/io/picnicml/doddlemodel/metrics/ClassificationMetrics.scala)
* [Regression Metrics](https://github.com/picnicml/doddle-model/blob/master/src/main/scala/io/picnicml/doddlemodel/metrics/RegressionMetrics.scala)
* [Ranking Metrics](https://github.com/picnicml/doddle-model/blob/master/src/main/scala/io/picnicml/doddlemodel/metrics/RankingMetrics.scala)
* [ROC curve visualization](https://picnicml.github.io/doddle-model-examples/roc-curve-visualization.html)

#### 3. Baseline models
* [Most Frequent Classifier](src/main/scala/io/picnicml/doddlemodel/examples/dummy/MostFrequentClassifierExample.scala)
* [Stratified Classifier](src/main/scala/io/picnicml/doddlemodel/examples/dummy/StratifiedClassifierExample.scala)
* [Uniform Classifier](src/main/scala/io/picnicml/doddlemodel/examples/dummy/UniformClassifierExample.scala)
* [Mean Regressor](src/main/scala/io/picnicml/doddlemodel/examples/dummy/MeanRegressorExample.scala)
* [Median Regressor](src/main/scala/io/picnicml/doddlemodel/examples/dummy/MedianRegressorExample.scala)

#### 4. Linear models
* [Linear Regression](src/main/scala/io/picnicml/doddlemodel/examples/linear/LinearRegressionExample.scala)
* [Logistic Regression](src/main/scala/io/picnicml/doddlemodel/examples/linear/LogisticRegressionExample.scala)
* [Softmax Classifier](src/main/scala/io/picnicml/doddlemodel/examples/linear/SoftmaxClassifierExample.scala)
* [Poisson Regression](src/main/scala/io/picnicml/doddlemodel/examples/linear/PoissonRegressionExample.scala)

#### 5. Model Selection
* [K-Fold Cross-Validation](src/main/scala/io/picnicml/doddlemodel/examples/modelselection/KFoldExample.scala)
* [Group K-Fold Cross-Validation](src/main/scala/io/picnicml/doddlemodel/examples/modelselection/GroupKFoldExample.scala)
* [Grid Search](src/main/scala/io/picnicml/doddlemodel/examples/modelselection/GridSearchExample.scala)
* [Random Search](src/main/scala/io/picnicml/doddlemodel/examples/modelselection/RandomSearchExample.scala)

#### 6. Miscellaneous
* [Reading Data](https://github.com/picnicml/doddle-model-examples/wiki/Reading-CSV-Data)
* [Shuffling Data](src/main/scala/io/picnicml/doddlemodel/examples/misc/ShuffleDatasetExample.scala)
* [Splitting Data](src/main/scala/io/picnicml/doddlemodel/examples/misc/SplitDatasetExample.scala)
* [Feature Preprocessing Pipeline](src/main/scala/io/picnicml/doddlemodel/examples/pipeline/PipelineExample.scala)
* [Estimator Persistence](src/main/scala/io/picnicml/doddlemodel/examples/misc/EstimatorPersistenceExample.scala)

#### 7. Use Cases
* [Kaggle House Prices](src/main/scala/io/picnicml/doddlemodel/examples/usecase/HousePrices.scala)
