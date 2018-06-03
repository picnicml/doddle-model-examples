package com.picnicml.doddlemodel.examples.preprocessing

import breeze.linalg.*
import breeze.stats.{mean, stddev}
import com.picnicml.doddlemodel.data.loadIrisDataset
import com.picnicml.doddlemodel.preprocessing.StandardScaler

object StandardScalerExample extends App {
  val (x, y) = loadIrisDataset
  println(s"Mean values before preprocessing: ${mean(x(::, *)).t}")
  println(s"Standard deviations before preprocessing: ${stddev(x(::, *)).t}")

  val scaler = StandardScaler()
  val trainedScaler = scaler.fit(x, y)
  val (xTransformed, _) = trainedScaler.transform(x, y)
  println(s"Mean values after preprocessing: ${mean(xTransformed(::, *)).t}")
  println(s"Standard deviations after preprocessing: ${stddev(xTransformed(::, *)).t}")
}
