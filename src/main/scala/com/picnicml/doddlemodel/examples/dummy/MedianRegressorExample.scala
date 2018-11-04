package com.picnicml.doddlemodel.examples.dummy

import com.picnicml.doddlemodel.data.{loadBostonDataset, splitDataset}
import com.picnicml.doddlemodel.dummy.regression.MedianRegressor
import com.picnicml.doddlemodel.metrics.rmse
import com.picnicml.doddlemodel.syntax.RegressorSyntax._

object MedianRegressorExample extends App {
  val (x, y) = loadBostonDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val (xTr, yTr, xTe, yTe) = splitDataset(x, y)
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val model = MedianRegressor()
  val trainedModel = model.fit(xTr, yTr)

  val score = rmse(yTe, trainedModel.predict(xTe))
  println(f"test root mean squared error: $score%1.4f")
}
