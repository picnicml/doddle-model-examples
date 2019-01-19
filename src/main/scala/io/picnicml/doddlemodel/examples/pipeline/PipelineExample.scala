package io.picnicml.doddlemodel.examples.pipeline

import io.picnicml.doddlemodel.data.DatasetUtils.{shuffleDataset, splitDataset}
import io.picnicml.doddlemodel.data.loadIrisDataset
import io.picnicml.doddlemodel.impute.MeanValueImputer
import io.picnicml.doddlemodel.linear.SoftmaxClassifier
import io.picnicml.doddlemodel.metrics.accuracy
import io.picnicml.doddlemodel.pipeline.Pipeline.pipe
import io.picnicml.doddlemodel.pipeline.{Pipeline, PipelineTransformers}
import io.picnicml.doddlemodel.preprocessing.StandardScaler
import io.picnicml.doddlemodel.syntax.PredictorSyntax._

import scala.util.Random

object PipelineExample extends App {
  // load and shuffle the data
  implicit val rand: Random = new Random(42)
  val (x, y) = (shuffleDataset _).tupled(loadIrisDataset)

  val split = splitDataset(x, y)
  println(s"training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}")

  val transformers: PipelineTransformers = List(
    pipe(MeanValueImputer()),
    pipe(StandardScaler())
  )
  val pipeline = Pipeline(transformers)(pipe(SoftmaxClassifier()))
  val trainedPipeline = pipeline.fit(split.xTr, split.yTr)

  val score = accuracy(split.yTe, trainedPipeline.predict(split.xTe))
  println(f"test accuracy: $score%1.4f")
}
