package io.picnicml.doddlemodel.examples.linear

import io.picnicml.doddlemodel.data.{loadHighSchoolTestDataset, splitDataset}
import io.picnicml.doddlemodel.linear.PoissonRegression
import io.picnicml.doddlemodel.metrics.rmse
import io.picnicml.doddlemodel.syntax.RegressorSyntax._

object PoissonRegressionExample extends App {
  val (x, y) = loadHighSchoolTestDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val split = splitDataset(x, y)
  println(s"training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}")

  // lambda is L2 regularization strength
  val model = PoissonRegression(lambda = 1.5)
  val trainedModel = model.fit(split.xTr, split.yTr)

  val score = rmse(split.yTe, trainedModel.predict(split.xTe))
  println(f"test root mean squared error: $score%1.4f")
}
