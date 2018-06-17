package com.picnicml.doddlemodel.examples.dummy

import com.picnicml.doddlemodel.data.{loadIrisDataset, shuffleDataset, splitDataset}
import com.picnicml.doddlemodel.dummy.classification.UniformClassifier
import com.picnicml.doddlemodel.metrics.accuracy

import scala.util.Random

object UniformClassifierExample extends App {
  implicit val rand: Random = new Random(42)
  val (x, y) = (shuffleDataset _).tupled(loadIrisDataset)
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  val (xTr, yTr, xTe, yTe) = splitDataset(x, y)
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val model = UniformClassifier()
  val trainedModel = model.fit(xTr, yTr)

  val score = accuracy(yTe, trainedModel.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
