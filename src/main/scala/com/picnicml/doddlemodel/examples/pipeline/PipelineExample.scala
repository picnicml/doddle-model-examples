package com.picnicml.doddlemodel.examples.pipeline

import com.picnicml.doddlemodel.data.{loadIrisDataset, shuffleDataset, splitDataset}
import com.picnicml.doddlemodel.linear.SoftmaxClassifier
import com.picnicml.doddlemodel.metrics.accuracy
import com.picnicml.doddlemodel.pipeline.Pipeline
import com.picnicml.doddlemodel.pipeline.Pipeline.pipe
import com.picnicml.doddlemodel.preprocessing.StandardScaler
import com.picnicml.doddlemodel.syntax.PredictorSyntax._

import scala.util.Random

object PipelineExample extends App {
  // load and shuffle the data
  implicit val rand: Random = new Random(42)
  val (x, y) = (shuffleDataset _).tupled(loadIrisDataset)

  val (xTr, yTr, xTe, yTe) = splitDataset(x, y)
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val pipeline = Pipeline(List(pipe(StandardScaler())))(pipe(SoftmaxClassifier()))
  val trainedPipeline = pipeline.fit(xTr, yTr)

  val score = accuracy(yTe, trainedPipeline.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
