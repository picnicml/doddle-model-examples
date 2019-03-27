package io.picnicml.doddlemodel.examples.misc

import io.picnicml.doddlemodel.data.DatasetUtils.shuffleDataset
import io.picnicml.doddlemodel.data.loadIrisDataset

import scala.util.Random

object ShuffleDatasetExample extends App {
  // set random seed
  implicit val rand: Random = new Random(42)

  // load the data and shuffle afterwards
  val (x, y, featureIndex) = loadIrisDataset
  val (xShuffled, yShuffled) = shuffleDataset(x, y)
}
