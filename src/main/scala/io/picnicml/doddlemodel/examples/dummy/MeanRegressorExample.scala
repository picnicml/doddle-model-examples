package io.picnicml.doddlemodel.examples.dummy

import io.picnicml.doddlemodel.data.{loadBostonDataset, splitDataset}
import io.picnicml.doddlemodel.dummy.regression.MeanRegressor
import io.picnicml.doddlemodel.metrics.rmse
import io.picnicml.doddlemodel.syntax.RegressorSyntax._

object MeanRegressorExample extends App {
  val (x, y) = loadBostonDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val (xTr, yTr, xTe, yTe) = splitDataset(x, y)
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val model = MeanRegressor()
  val trainedModel = model.fit(xTr, yTr)

  val score = rmse(yTe, trainedModel.predict(xTe))
  println(f"test root mean squared error: $score%1.4f")
}
