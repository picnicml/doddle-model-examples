package io.picnicml.doddlemodel.examples.modelselection

import breeze.stats.distributions.Gamma
import io.picnicml.doddlemodel.data.{loadBreastCancerDataset, splitDataset}
import io.picnicml.doddlemodel.linear.LogisticRegression
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.modelselection.{CrossValidation, HyperparameterSearch, KFoldSplitter}
import io.picnicml.doddlemodel.syntax.ClassifierSyntax._

import scala.util.Random

object RandomSearchExample extends App {
  val (x, y) = loadBreastCancerDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val (xTr, yTr, xTe, yTe) = splitDataset(x, y)
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val numSearchIterations = 100
  val cv: CrossValidation = CrossValidation(accuracy, KFoldSplitter(numFolds = 5))
  val search = HyperparameterSearch(numSearchIterations, cv)

  implicit val rand: Random = new Random(42)
  val gamma = Gamma(shape = 2, scale = 2)
  val bestModel = search.bestOf(xTr, yTr) {
    LogisticRegression(lambda = gamma.draw())
  }

  val score = accuracy(yTe, bestModel.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
