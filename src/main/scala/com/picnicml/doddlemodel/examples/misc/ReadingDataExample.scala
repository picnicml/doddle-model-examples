package com.picnicml.doddlemodel.examples.misc

import java.io.File

import breeze.linalg.csvread

object ReadingDataExample extends App {
  val dataDir = "/path/to/local/data/dir"

  // assume a header line
  val dataTr = csvread(new File(s"$dataDir/train.csv"), skipLines = 1)
  val dataTe = csvread(new File(s"$dataDir/test.csv"), skipLines = 1)

  // assume that 'label' is the first column
  val (xTr, yTr) = (dataTr(::, 1 to -1), dataTr(::, 0))
  val (xTe, yTe) = (dataTe(::, 1 to -1), dataTe(::, 0))
}
