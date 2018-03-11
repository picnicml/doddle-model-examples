package com.picnicml.doddlemodel.examples.linear

import com.picnicml.doddlemodel.data.loadBreastCancerDataset
import com.picnicml.doddlemodel.linear.LogisticRegression
import com.picnicml.doddlemodel.metrics.accuracy

object LogisticRegressionExample extends App {
  println("Logistic regression model trained on the breast cancer dataset:")
  val (x, y) = loadBreastCancerDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val trIndices = 0 until x.rows / 2
  val teIndices = x.rows / 2 until x.rows
  val (xTr, yTr) = (x(trIndices, ::), y(trIndices))
  val (xTe, yTe) = (x(teIndices, ::), y(teIndices))
  println(s"training set size: ${xTr.rows}, validation set size: ${xTe.rows}")

  // lambda is L2 regularization strength
  val model = LogisticRegression(lambda = 1.5)
  val trainedModel = model.fit(xTr, yTr)

  val score = accuracy(yTe, trainedModel.predict(xTe))
  println(f"validation accuracy: $score%1.4f")
}
