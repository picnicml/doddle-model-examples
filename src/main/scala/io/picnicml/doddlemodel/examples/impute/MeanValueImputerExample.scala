package io.picnicml.doddlemodel.examples.impute

import breeze.linalg.DenseMatrix
import io.picnicml.doddlemodel.data.Feature.FeatureIndex
import io.picnicml.doddlemodel.impute.MeanValueImputer
import io.picnicml.doddlemodel.syntax.TransformerSyntax._

object MeanValueImputerExample extends App {
  val xMissing = DenseMatrix(
    List(Double.NaN, 1.0, 2.0),
    List(3.0, Double.NaN, 5.0),
    List(6.0, 7.0, 8.0)
  )
  val featureIndex = FeatureIndex.numerical(xMissing.cols)

  // only impute the first and the last column
  val imputer = MeanValueImputer(featureIndex.subset(IndexedSeq(0, 2)))
  val trainedImputer = imputer.fit(xMissing)
  println(s"imputed data:\n${trainedImputer.transform(xMissing)}")
}
