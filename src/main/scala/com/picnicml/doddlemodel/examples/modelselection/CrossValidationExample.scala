package com.picnicml.doddlemodel.examples.modelselection

import com.picnicml.doddlemodel.data.loadBreastCancerDataset
import com.picnicml.doddlemodel.linear.LogisticRegression
import com.picnicml.doddlemodel.metrics.accuracy
import com.picnicml.doddlemodel.modelselection.CrossValidation

import scala.util.Random

object CrossValidationExample extends App {
  val (x, y) = loadBreastCancerDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  // lambda is L2 regularization strength
  val model = LogisticRegression(lambda = 1.5)
  val cv = CrossValidation(metric = accuracy, folds = 10)

  implicit val rand: Random = new Random(42)
  val score = cv.score(model, x, y)
  println(f"cross-validation accuracy: $score%1.4f")
}
