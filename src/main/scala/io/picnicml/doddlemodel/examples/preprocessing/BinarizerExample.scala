package io.picnicml.doddlemodel.examples.preprocessing

import breeze.linalg.DenseMatrix
import io.picnicml.doddlemodel.data.Feature.{CategoricalFeature, FeatureIndex, NumericalFeature}
import io.picnicml.doddlemodel.preprocessing.Binarizer
import io.picnicml.doddlemodel.syntax.TransformerSyntax._

object BinarizerExample extends App {
  val x = DenseMatrix(
    List(1.0, 1.0, 1.0),
    List(3.0, 0.0, 1.5),
    List(6.0, 2.0, 0.0)
  )
  val featureIndex = FeatureIndex(List(NumericalFeature, CategoricalFeature, NumericalFeature))
  println(s"features: $featureIndex")

  // binarize all numerical features
  val binarizer = Binarizer(threshold = 1.0, featureIndex = featureIndex)
  val fittedBinarizer = binarizer.fit(x)
  println(s"binarized data:\n${fittedBinarizer.transform(x)}")

  // only binarize the last feature
  val binarizerSubset = Binarizer(threshold = 1.0, featureIndex = featureIndex.subset("f2"))
  val fittedBinarizerSubset = binarizerSubset.fit(x)
  println(s"binarized data:\n${fittedBinarizerSubset.transform(x)}")
}
