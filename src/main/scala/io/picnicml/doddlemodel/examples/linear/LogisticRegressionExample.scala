package io.picnicml.doddlemodel.examples.linear

import io.picnicml.doddlemodel.data.{loadBreastCancerDataset, splitDataset}
import io.picnicml.doddlemodel.linear.LogisticRegression
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.syntax.ClassifierSyntax._

object LogisticRegressionExample extends App {
  val (x, y) = loadBreastCancerDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val (xTr, yTr, xTe, yTe) = splitDataset(x, y)
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  // lambda is L2 regularization strength
  val model = LogisticRegression(lambda = 1.5)
  val trainedModel = model.fit(xTr, yTr)

  val score = accuracy(yTe, trainedModel.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
