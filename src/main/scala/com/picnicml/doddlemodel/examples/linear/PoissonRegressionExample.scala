package com.picnicml.doddlemodel.examples.linear

import com.picnicml.doddlemodel.data.loadHighSchoolTestDataset
import com.picnicml.doddlemodel.linear.PoissonRegression
import com.picnicml.doddlemodel.metrics.rmse

object PoissonRegressionExample extends App {

  println("Poisson regression model trained on the high school test dataset:")
  val (x, y) = loadHighSchoolTestDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val trIndices = 0 until x.rows / 2
  val teIndices = x.rows / 2 until x.rows
  val (xTr, yTr) = (x(trIndices, ::), y(trIndices))
  val (xTe, yTe) = (x(teIndices, ::), y(teIndices))
  println(s"training set size: ${xTr.rows}, validation set size: ${xTe.rows}")

  // lambda is L2 regularization strength
  val model = PoissonRegression(lambda = 1.5)
  val trainedModel = model.fit(xTr, yTr)

  val score = rmse(yTe, trainedModel.predict(xTe))
  println(f"validation root mean squared error: $score%1.4f")
}
