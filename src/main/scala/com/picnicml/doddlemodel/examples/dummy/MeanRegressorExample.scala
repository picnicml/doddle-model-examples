package com.picnicml.doddlemodel.examples.dummy

import com.picnicml.doddlemodel.data.loadBostonDataset
import com.picnicml.doddlemodel.dummy.regression.MeanRegressor
import com.picnicml.doddlemodel.metrics.rmse

object MeanRegressorExample extends App {
  val (x, y) = loadBostonDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  // split the data
  val trIndices = 0 until x.rows / 2
  val teIndices = x.rows / 2 until x.rows
  val (xTr, yTr) = (x(trIndices, ::), y(trIndices))
  val (xTe, yTe) = (x(teIndices, ::), y(teIndices))
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val model = MeanRegressor()
  val trainedModel = model.fit(xTr, yTr)

  val score = rmse(yTe, trainedModel.predict(xTe))
  println(f"test root mean squared error: $score%1.4f")
}
