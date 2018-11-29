package io.picnicml.doddlemodel.examples.dummy

import io.picnicml.doddlemodel.data.{loadIrisDataset, shuffleDataset, splitDataset}
import io.picnicml.doddlemodel.dummy.classification.StratifiedClassifier
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.syntax.ClassifierSyntax._

import scala.util.Random

object StratifiedClassifierExample extends App {
  implicit val rand: Random = new Random(42)
  val (x, y) = (shuffleDataset _).tupled(loadIrisDataset)
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val (xTr, yTr, xTe, yTe) = splitDataset(x, y)
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val model = StratifiedClassifier()
  val trainedModel = model.fit(xTr, yTr)

  val score = accuracy(yTe, trainedModel.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
