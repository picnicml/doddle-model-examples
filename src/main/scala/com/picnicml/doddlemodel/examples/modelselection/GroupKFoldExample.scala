package com.picnicml.doddlemodel.examples.modelselection

import breeze.linalg.DenseVector
import com.picnicml.doddlemodel.data.loadBreastCancerDataset
import com.picnicml.doddlemodel.linear.LogisticRegression
import com.picnicml.doddlemodel.metrics.accuracy
import com.picnicml.doddlemodel.modelselection.{CrossValidation, GroupKFoldSplitter}

import scala.util.Random

object GroupKFoldExample extends App {
  val (x, y) = loadBreastCancerDataset
  // e.g. we have 10 patients
  val groups = Some(DenseVector((0 until x.rows).map(x => x % 10):_*))
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  // lambda is L2 regularization strength
  val model = LogisticRegression(lambda = 1.5)
  val splitter = GroupKFoldSplitter(numFolds = 10)
  val cv = CrossValidation(metric = accuracy, splitter)

  implicit val rand: Random = new Random(42)
  val score = cv.score(model, x, y, groups)
  println(f"cross-validation accuracy: $score%1.4f")
}
