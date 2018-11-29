package io.picnicml.doddlemodel.examples.misc

import io.picnicml.doddlemodel.data.loadBreastCancerDataset
import io.picnicml.doddlemodel.linear.LogisticRegression
import io.picnicml.doddlemodel.loadEstimator
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.syntax.ClassifierSyntax._

object EstimatorPersistenceExample extends App {
  val (x, y) = loadBreastCancerDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val model = LogisticRegression()
  val trainedModel = model.fit(x, y)
  val score = accuracy(y, trainedModel.predict(x))
  println(f"training accuracy: $score%1.4f")

  // save the model
  val tempFilePath = s"${System.getProperty("java.io.tmpdir")}model.ser"
  trainedModel.save(tempFilePath)

  // load the model
  val loadedModel = loadEstimator[LogisticRegression](tempFilePath)

  val scoreAfterLoading = accuracy(y, loadedModel.predict(x))
  println(f"training accuracy of the loaded model: $scoreAfterLoading%1.4f")
}
