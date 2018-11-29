package io.picnicml.doddlemodel.examples.misc

import io.picnicml.doddlemodel.data.{loadIrisDataset, splitDataset}

object SplitDatasetExample extends App {
  val (x, y) = loadIrisDataset

  // if not specified, the default value of 0.5 is used for proportionTrain
  val (xTr, yTr, xTe, yTe) = splitDataset(x, y, proportionTrain = 0.75)
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")
}
