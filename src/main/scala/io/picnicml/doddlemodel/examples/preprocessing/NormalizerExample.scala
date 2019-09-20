package io.picnicml.doddlemodel.examples.preprocessing

import breeze.linalg.DenseMatrix
import io.picnicml.doddlemodel.preprocessing.Normalizer
import io.picnicml.doddlemodel.preprocessing.Norms.L2Norm
import io.picnicml.doddlemodel.syntax.TransformerSyntax._

object NormalizerExample extends App {
  val x = DenseMatrix(
    List(1.0, 1.0, 1.0),
    List(3.0, 0.0, 1.5),
    List(6.0, 2.0, 0.0)
  )

  val normalizer = Normalizer(L2Norm)
  val fittedNormalizer = normalizer.fit(x)
  println(s"normalized rows:\n${fittedNormalizer.transform(x)}")
}
