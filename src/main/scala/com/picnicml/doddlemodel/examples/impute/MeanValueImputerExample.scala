package com.picnicml.doddlemodel.examples.impute

import breeze.linalg.DenseMatrix
import com.picnicml.doddlemodel.impute.MeanValueImputer
import com.picnicml.doddlemodel.syntax.TransformerSyntax._

object MeanValueImputerExample extends App {
  val xMissing = DenseMatrix(
    List(Double.NaN, 1.0, 2.0),
    List(3.0, Double.NaN, 5.0),
    List(6.0, 7.0, 8.0)
  )

  val imputer = MeanValueImputer()
  val fittedImputer = imputer.fit(xMissing)
  println(s"imputed data: ${fittedImputer.transform(xMissing)}")
}
