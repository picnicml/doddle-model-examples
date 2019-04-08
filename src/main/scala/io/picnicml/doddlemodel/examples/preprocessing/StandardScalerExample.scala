package io.picnicml.doddlemodel.examples.preprocessing

import breeze.linalg.*
import breeze.stats.{mean, stddev}
import io.picnicml.doddlemodel.data.loadIrisDataset
import io.picnicml.doddlemodel.preprocessing.StandardScaler
import io.picnicml.doddlemodel.syntax.TransformerSyntax._

object StandardScalerExample extends App {
  val (x, _, featureIndex) = loadIrisDataset
  println(s"features: $featureIndex")
  println(s"mean values before preprocessing:\n${mean(x(::, *)).t}")
  println(s"standard deviations before preprocessing:\n${stddev(x(::, *)).t}\n")

  val scaler = StandardScaler(featureIndex)
  val trainedScaler = scaler.fit(x)
  val xTransformed = trainedScaler.transform(x)
  println(s"mean values after preprocessing:\n${mean(xTransformed(::, *)).t}")
  println(s"standard deviations after preprocessing:\n${stddev(xTransformed(::, *)).t}\n")

  // only transform a subset of columns
  val scalerSubset = StandardScaler(featureIndex.subset("sepal_length", "petal_width"))
  val trainedScalerSubset = scalerSubset.fit(x)
  val xTransformedSubset = trainedScalerSubset.transform(x)
  println(s"mean values after preprocessing a subset of features:\n${mean(xTransformedSubset(::, *)).t}")
  println(s"standard deviations after preprocessing a subset of features:\n${stddev(xTransformedSubset(::, *)).t}")
}
