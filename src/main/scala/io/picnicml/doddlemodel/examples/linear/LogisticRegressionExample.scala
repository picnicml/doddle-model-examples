package io.picnicml.doddlemodel.examples.linear

import io.picnicml.doddlemodel.data.DatasetUtils.splitDataset
import io.picnicml.doddlemodel.data.loadBreastCancerDataset
import io.picnicml.doddlemodel.linear.LogisticRegression
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.syntax.ClassifierSyntax._

object LogisticRegressionExample extends App {
  val (x, y) = loadBreastCancerDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val split = splitDataset(x, y)
  println(s"training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}")

  // lambda is L2 regularization strength
  val model = LogisticRegression(lambda = 1.5)
  val trainedModel = model.fit(split.xTr, split.yTr)

  val score = accuracy(split.yTe, trainedModel.predict(split.xTe))
  println(f"test accuracy: $score%1.4f")
}
