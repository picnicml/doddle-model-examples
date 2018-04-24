package com.picnicml.doddlemodel.examples.modelselection

import breeze.stats.distributions.Gamma
import com.picnicml.doddlemodel.data.Utils.shuffleDataset
import com.picnicml.doddlemodel.data.loadBreastCancerDataset
import com.picnicml.doddlemodel.linear.LogisticRegression
import com.picnicml.doddlemodel.metrics.accuracy
import com.picnicml.doddlemodel.modelselection.{CrossValidation, HyperparameterSearch}

object RandomSearchExample extends App {
  val (x, y) = loadBreastCancerDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  // shuffle the data
  val (xShuffled, yShuffled) = shuffleDataset(x, y)

  // split the data
  val trIndices = 0 until 400
  val teIndices = 400 until x.rows
  val (xTr, yTr) = (xShuffled(trIndices, ::), yShuffled(trIndices))
  val (xTe, yTe) = (xShuffled(teIndices, ::), yShuffled(teIndices))
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val numSearchIterations = 100
  val crossValidation = CrossValidation[LogisticRegression](metric = accuracy, folds = 5)
  val search = HyperparameterSearch[LogisticRegression](crossVal = crossValidation, numIterations = numSearchIterations)

  val gamma = Gamma(shape = 2, scale = 2)
  val bestModel = search.bestOf(xTr, yTr) {
    LogisticRegression(lambda = gamma.draw())
  }

  val score = accuracy(yTe, bestModel.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
