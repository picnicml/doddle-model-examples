package io.picnicml.doddlemodel.examples.dummy

import io.picnicml.doddlemodel.data.DatasetUtils.splitDataset
import io.picnicml.doddlemodel.data.loadBostonDataset
import io.picnicml.doddlemodel.dummy.regression.MeanRegressor
import io.picnicml.doddlemodel.metrics.rmse
import io.picnicml.doddlemodel.syntax.RegressorSyntax._

object MeanRegressorExample extends App {
  val (x, y, featureIndex) = loadBostonDataset
  println(s"features: $featureIndex")
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val split = splitDataset(x, y)
  println(s"training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}")

  val model = MeanRegressor()
  val trainedModel = model.fit(split.xTr, split.yTr)

  val score = rmse(split.yTe, trainedModel.predict(split.xTe))
  println(f"test root mean squared error: $score%1.4f")
}
