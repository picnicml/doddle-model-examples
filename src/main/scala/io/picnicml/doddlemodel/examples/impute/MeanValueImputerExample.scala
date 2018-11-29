package io.picnicml.doddlemodel.examples.impute

import breeze.linalg.DenseMatrix
import io.picnicml.doddlemodel.impute.MeanValueImputer
import io.picnicml.doddlemodel.syntax.TransformerSyntax._

object MeanValueImputerExample extends App {
  val xMissing = DenseMatrix(
    List(Double.NaN, 1.0, 2.0),
    List(3.0, Double.NaN, 5.0),
    List(6.0, 7.0, 8.0)
  )

  val imputer = MeanValueImputer()
  val trainedImputer = imputer.fit(xMissing)
  println(s"imputed data: ${trainedImputer.transform(xMissing)}")
}
