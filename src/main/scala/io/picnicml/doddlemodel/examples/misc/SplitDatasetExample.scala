package io.picnicml.doddlemodel.examples.misc

import breeze.linalg.DenseVector
import io.picnicml.doddlemodel.data.{loadIrisDataset, splitDataset, splitDatasetWithGroups}

object SplitDatasetExample extends App {
  val (x, y) = loadIrisDataset

  // if not specified, the default value of 0.5 is used for proportionTrain
  val split = splitDataset(x, y, proportionTrain = 0.9)
  println(s"training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}")

  // split with groups
  val groups = DenseVector((0 until x.rows).map(x => x % 4):_*)
  val groupsSplit = splitDatasetWithGroups(x, y, groups, proportionTrain = 0.9)
  println(s"training set size: ${groupsSplit.xTr.rows}, test set size: ${groupsSplit.xTe.rows}")
}
