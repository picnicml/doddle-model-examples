package io.picnicml.doddlemodel.examples.misc

import io.picnicml.doddlemodel.data.{loadIrisDataset, shuffleDataset}

import scala.util.Random

object ShuffleDatasetExample extends App {
  // set random seed
  implicit val rand: Random = new Random(42)

  // load the data and shuffle afterwards
  val (x, y) = loadIrisDataset
  val (xShuffled0, yShuffled0) = shuffleDataset(x, y)

  // load and shuffle in a single line
  val (xShuffled1, yShuffled1) = (shuffleDataset _).tupled(loadIrisDataset)
}
