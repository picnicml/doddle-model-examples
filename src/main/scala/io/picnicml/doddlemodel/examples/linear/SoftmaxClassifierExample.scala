package io.picnicml.doddlemodel.examples.linear

import io.picnicml.doddlemodel.data.DatasetUtils.{shuffleDataset, splitDataset}
import io.picnicml.doddlemodel.data.loadIrisDataset
import io.picnicml.doddlemodel.linear.SoftmaxClassifier
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.syntax.ClassifierSyntax._

import scala.util.Random

object SoftmaxClassifierExample extends App {
  implicit val rand: Random = new Random(42)
  val (x, y) = (shuffleDataset _).tupled(loadIrisDataset)
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val split = splitDataset(x, y)
  println(s"training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}")

  // lambda is L2 regularization strength
  val model = SoftmaxClassifier(lambda = 1.5)
  val trainedModel = model.fit(split.xTr, split.yTr)

  val score = accuracy(split.yTe, trainedModel.predict(split.xTe))
  println(f"test accuracy: $score%1.4f")
}
