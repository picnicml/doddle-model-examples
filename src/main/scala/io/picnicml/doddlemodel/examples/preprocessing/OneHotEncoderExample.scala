package io.picnicml.doddlemodel.examples.preprocessing

import breeze.linalg.DenseMatrix
import io.picnicml.doddlemodel.data.Feature.{CategoricalFeature, FeatureIndex, NumericalFeature}
import io.picnicml.doddlemodel.preprocessing.OneHotEncoder
import io.picnicml.doddlemodel.syntax.TransformerSyntax._

object OneHotEncoderExample extends App {
  val x = DenseMatrix(
    List(1.0, 1.0, 1.0),
    List(3.0, 0.0, 1.0),
    List(6.0, 2.0, 0.0)
  )
  val featureIndex = FeatureIndex(List(CategoricalFeature, NumericalFeature, CategoricalFeature))
  println(s"features: $featureIndex")

  // encode all categorical features
  val oneHotEncoder = OneHotEncoder(featureIndex)
  val fittedOneHotEncoder = oneHotEncoder.fit(x)
  println(s"encoded data:\n${fittedOneHotEncoder.transform(x)}")

  // only encode the last feature
  val oneHotEncoderSubset = OneHotEncoder(featureIndex.subset("f2"))
  val fittedOneHotEncoderSubset = oneHotEncoderSubset.fit(x)
  println(s"encoded data:\n${fittedOneHotEncoderSubset.transform(x)}")
}
