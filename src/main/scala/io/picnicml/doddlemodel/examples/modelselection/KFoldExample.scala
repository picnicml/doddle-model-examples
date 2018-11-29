package io.picnicml.doddlemodel.examples.modelselection

import io.picnicml.doddlemodel.data.loadBreastCancerDataset
import io.picnicml.doddlemodel.linear.LogisticRegression
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.modelselection.{CrossValidation, KFoldSplitter}

import scala.util.Random

object KFoldExample extends App {
  val (x, y) = loadBreastCancerDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  // lambda is L2 regularization strength
  val model = LogisticRegression(lambda = 1.5)
  val cv = CrossValidation(accuracy, KFoldSplitter(numFolds = 10))

  implicit val rand: Random = new Random(42)
  val score = cv.score(model, x, y)
  println(f"cross-validation accuracy: $score%1.4f")
}
