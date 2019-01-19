package io.picnicml.doddlemodel.examples.misc

import java.io.File

import io.picnicml.doddlemodel.data.CsvLoader.loadCsvDataset

object ReadingDataExample extends App {
  val dataPath = "/path/to/local/data/dataset.csv"
  val data = loadCsvDataset(new File(dataPath))

  // assume that 'label' is the last column
  val (x, y) = (data(::, 0 to -2), data(::, -1))
  println(s"features shape: ${x.rows}, ${x.cols}")
  println(s"target shape: ${y.length}")
}
