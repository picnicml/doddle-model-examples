package io.picnicml.doddlemodel.examples.misc

import java.io.File

import io.picnicml.doddlemodel.data.CsvLoader.loadCsvDataset

object ReadingDataExample extends App {
  val dataPath = "/path/to/local/dataset.csv"
  val (data, featureIndex) = loadCsvDataset(new File(dataPath))

  // 'label' is the last column, drop it from features and feature index
  val (x, y) = (data(::, 0 to -2), data(::, -1))
  val fixedFeatureIndex = featureIndex.drop(x.cols)

  println(s"features shape: ${x.rows}, ${x.cols}")
  println(s"target shape: ${y.length}")
  println(s"features: $fixedFeatureIndex")
}
