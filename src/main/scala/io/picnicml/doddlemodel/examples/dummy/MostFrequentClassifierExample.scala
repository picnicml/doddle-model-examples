package io.picnicml.doddlemodel.examples.dummy

import io.picnicml.doddlemodel.data.loadIrisDataset
import io.picnicml.doddlemodel.data.DatasetUtils.{shuffleDataset, splitDataset}
import io.picnicml.doddlemodel.dummy.classification.MostFrequentClassifier
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.syntax.ClassifierSyntax._

import scala.util.Random

object MostFrequentClassifierExample extends App {
  implicit val rand: Random = new Random(42)
  val (features, target, featureIndex) = loadIrisDataset
  println(s"features: $featureIndex")

  val (x, y) = shuffleDataset(features, target)
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val split = splitDataset(x, y)
  println(s"training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}")

  val model = MostFrequentClassifier()
  val trainedModel = model.fit(split.xTr, split.yTr)

  val score = accuracy(split.yTe, trainedModel.predict(split.xTe))
  println(f"test accuracy: $score%1.4f")
}
