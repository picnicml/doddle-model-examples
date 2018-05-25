package com.picnicml.doddlemodel.examples.misc

import com.picnicml.doddlemodel.data.loadCsvDataset

object ReadingDataExample extends App {
  val dataDir = "/path/to/local/data/dir"

  // by default a header line is assumed
  val dataTr = loadCsvDataset(s"$dataDir/train.csv")
  val dataTe = loadCsvDataset(s"$dataDir/test.csv")

  // assume that 'label' is the first column
  val (xTr, yTr) = (dataTr(::, 1 to -1), dataTr(::, 0))
  val (xTe, yTe) = (dataTe(::, 1 to -1), dataTe(::, 0))
}
