package io.picnicml.doddlemodel.examples.preprocessing

import breeze.linalg.DenseMatrix
import io.picnicml.doddlemodel.data.Feature.{CategoricalFeature, FeatureIndex, NumericalFeature}
import io.picnicml.doddlemodel.preprocessing.RangeScaler
import io.picnicml.doddlemodel.syntax.TransformerSyntax._

object RangeScalerExample extends App {
  val x = DenseMatrix(
    List(1.0, 1.0, 1.0),
    List(3.0, 0.0, 1.5),
    List(6.0, 2.0, 0.0)
  )
  val featureIndex = FeatureIndex(List(NumericalFeature, CategoricalFeature, NumericalFeature))
  println(s"features: $featureIndex")

  // scale all numerical features
  val scaler = RangeScaler(range = (0, 1), featureIndex = featureIndex)
  val fittedScaler = scaler.fit(x)
  println(s"scaled data:\n${fittedScaler.transform(x)}")

  // only scale the last feature
  val scalerSubset = RangeScaler(range = (0, 1), featureIndex = featureIndex.subset(IndexedSeq(2)))
  val fittedScalerSubset = scalerSubset.fit(x)
  println(s"scaled data:\n${fittedScalerSubset.transform(x)}")
}
