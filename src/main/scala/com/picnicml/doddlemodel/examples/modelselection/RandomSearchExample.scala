package com.picnicml.doddlemodel.examples.modelselection

import breeze.stats.distributions.Gamma
import com.picnicml.doddlemodel.data.loadBreastCancerDataset
import com.picnicml.doddlemodel.linear.LogisticRegression
import com.picnicml.doddlemodel.metrics.accuracy
import com.picnicml.doddlemodel.modelselection.{CrossValidation, HyperparameterSearch}

import scala.util.Random

object RandomSearchExample extends App {
  val (x, y) = loadBreastCancerDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  // split the data
  val trIndices = 0 until 400
  val teIndices = 400 until x.rows
  val (xTr, yTr) = (x(trIndices, ::), y(trIndices))
  val (xTe, yTe) = (x(teIndices, ::), y(teIndices))
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val numSearchIterations = 100
  val crossValidation = CrossValidation(metric = accuracy, folds = 5)
  val search = HyperparameterSearch[LogisticRegression](crossVal = crossValidation, numIterations = numSearchIterations)

  implicit val rand: Random = new Random(42)
  val gamma = Gamma(shape = 2, scale = 2)
  val bestModel = search.bestOf(xTr, yTr) {
    LogisticRegression(lambda = gamma.draw())
  }

  val score = accuracy(yTe, bestModel.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
