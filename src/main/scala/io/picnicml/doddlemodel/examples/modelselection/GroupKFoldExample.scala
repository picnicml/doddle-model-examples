package io.picnicml.doddlemodel.examples.modelselection

import breeze.linalg.DenseVector
import io.picnicml.doddlemodel.data.loadBreastCancerDataset
import io.picnicml.doddlemodel.linear.LogisticRegression
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.modelselection.{CrossValidation, GroupKFoldSplitter}

import scala.util.Random

object GroupKFoldExample extends App {
  val (x, y, featureIndex) = loadBreastCancerDataset
  println(s"features: $featureIndex")

  // e.g. we have 10 patients
  val groups = Some(DenseVector((0 until x.rows).map(x => x % 10):_*))
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  // lambda is L2 regularization strength
  val model = LogisticRegression(lambda = 1.5)
  val cv = CrossValidation(accuracy, GroupKFoldSplitter(numFolds = 10))

  implicit val rand: Random = new Random(42)
  val score = cv.score(model, x, y, groups)
  println(f"cross-validation accuracy: $score%1.4f")
}
