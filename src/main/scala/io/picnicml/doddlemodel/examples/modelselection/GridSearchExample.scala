package io.picnicml.doddlemodel.examples.modelselection

import io.picnicml.doddlemodel.data.DatasetUtils.splitDataset
import io.picnicml.doddlemodel.data.loadBreastCancerDataset
import io.picnicml.doddlemodel.linear.LogisticRegression
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.modelselection.{CrossValidation, HyperparameterSearch, KFoldSplitter}
import io.picnicml.doddlemodel.syntax.ClassifierSyntax._

import scala.util.Random

object GridSearchExample extends App {
  val (x, y, featureIndex) = loadBreastCancerDataset
  println(s"features: $featureIndex")
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val split = splitDataset(x, y)
  println(s"training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}")

  val numSearchIterations = 100
  val cv: CrossValidation = CrossValidation(accuracy, KFoldSplitter(numFolds = 5))
  val search = HyperparameterSearch(numSearchIterations, cv)

  implicit val rand: Random = new Random(42)
  val grid = (0 until numSearchIterations).toIterator.map(_.toDouble)
  val bestModel = search.bestOf(split.xTr, split.yTr) {
    LogisticRegression(lambda = grid.next)
  }

  val score = accuracy(split.yTe, bestModel.predict(split.xTe))
  println(f"test accuracy: $score%1.4f")
}
