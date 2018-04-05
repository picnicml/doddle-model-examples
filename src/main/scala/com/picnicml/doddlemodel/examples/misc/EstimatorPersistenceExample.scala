package com.picnicml.doddlemodel.examples.misc

import com.picnicml.doddlemodel.data.loadBreastCancerDataset
import com.picnicml.doddlemodel.linear.LogisticRegression
import com.picnicml.doddlemodel.loadEstimator
import com.picnicml.doddlemodel.metrics.accuracy

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
