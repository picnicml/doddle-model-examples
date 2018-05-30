## doddle-model examples
This repository contains code examples for the [doddle-model](https://github.com/picnicml/doddle-model) machine learning library.

### Table of Contents

#### 1. Baseline models
* [Most Frequent Classifier](src/main/scala/com/picnicml/doddlemodel/examples/dummy/MostFrequentClassifierExample.scala)
* [Stratified Classifier](src/main/scala/com/picnicml/doddlemodel/examples/dummy/StratifiedClassifierExample.scala)
* [Uniform Classifier](src/main/scala/com/picnicml/doddlemodel/examples/dummy/UniformClassifierExample.scala)
* [Mean Regressor](src/main/scala/com/picnicml/doddlemodel/examples/dummy/MeanRegressorExample.scala)
* [Median Regressor](src/main/scala/com/picnicml/doddlemodel/examples/dummy/MedianRegressorExample.scala)

#### 2. Linear models
* [Linear Regression](src/main/scala/com/picnicml/doddlemodel/examples/linear/LinearRegressionExample.scala)
* [Logistic Regression](src/main/scala/com/picnicml/doddlemodel/examples/linear/LogisticRegressionExample.scala)
* [Softmax Classifier](src/main/scala/com/picnicml/doddlemodel/examples/linear/SoftmaxClassifierExample.scala)
* [Poisson Regression](src/main/scala/com/picnicml/doddlemodel/examples/linear/PoissonRegressionExample.scala)

#### 3. Model Selection
* [Cross-Validation](src/main/scala/com/picnicml/doddlemodel/examples/modelselection/CrossValidationExample.scala)
* [Grid Search](src/main/scala/com/picnicml/doddlemodel/examples/modelselection/GridSearchExample.scala)
* [Random Search](src/main/scala/com/picnicml/doddlemodel/examples/modelselection/RandomSearchExample.scala)

#### 4. Miscellaneous
* [Reading Data](src/main/scala/com/picnicml/doddlemodel/examples/misc/ReadingDataExample.scala)
* [Estimator Persistence](src/main/scala/com/picnicml/doddlemodel/examples/misc/EstimatorPersistenceExample.scala)

### Setup
To run the examples locally you will need to publish a local snapshot version of the repository:
```
git clone https://github.com/picnicml/doddle-model.git
cd doddle-model
sbt publishLocal
```
Ensure the published version matches the version contained within the `project/Dependencies.scala` file.
