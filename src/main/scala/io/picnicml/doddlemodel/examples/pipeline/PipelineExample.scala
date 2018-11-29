package io.picnicml.doddlemodel.examples.pipeline

import io.picnicml.doddlemodel.data.{loadIrisDataset, shuffleDataset, splitDataset}
import io.picnicml.doddlemodel.impute.MeanValueImputer
import io.picnicml.doddlemodel.linear.SoftmaxClassifier
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.pipeline.Pipeline
import io.picnicml.doddlemodel.pipeline.Pipeline.pipe
import io.picnicml.doddlemodel.preprocessing.StandardScaler
import io.picnicml.doddlemodel.syntax.PredictorSyntax._

import scala.util.Random

object PipelineExample extends App {
  // load and shuffle the data
  implicit val rand: Random = new Random(42)
  val (x, y) = (shuffleDataset _).tupled(loadIrisDataset)

  val (xTr, yTr, xTe, yTe) = splitDataset(x, y)
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val pipeline = Pipeline(
    transformers = List(pipe(MeanValueImputer()), pipe(StandardScaler())))(
    predictor = pipe(SoftmaxClassifier())
  )
  val trainedPipeline = pipeline.fit(xTr, yTr)

  val score = accuracy(yTe, trainedPipeline.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
