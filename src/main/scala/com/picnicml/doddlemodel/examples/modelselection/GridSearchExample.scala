package com.picnicml.doddlemodel.examples.modelselection

import com.picnicml.doddlemodel.data.{loadBreastCancerDataset, splitDataset}
import com.picnicml.doddlemodel.linear.LogisticRegression
import com.picnicml.doddlemodel.metrics.accuracy
import com.picnicml.doddlemodel.modelselection.{CrossValidation, HyperparameterSearch}

import scala.util.Random

object GridSearchExample extends App {
  val (x, y) = loadBreastCancerDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val (xTr, yTr, xTe, yTe) = splitDataset(x, y)
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val numSearchIterations = 100
  val crossValidation = CrossValidation(metric = accuracy, folds = 5)
  val search = HyperparameterSearch[LogisticRegression](crossValidation, numSearchIterations)

  implicit val rand: Random = new Random(42)
  val grid = (0 until numSearchIterations).toIterator.map(_.toDouble)
  val bestModel = search.bestOf(xTr, yTr) {
    LogisticRegression(lambda = grid.next)
  }

  val score = accuracy(yTe, bestModel.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
