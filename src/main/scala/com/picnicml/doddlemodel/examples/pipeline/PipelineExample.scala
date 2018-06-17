package com.picnicml.doddlemodel.examples.pipeline

import com.picnicml.doddlemodel.data.Utils.shuffleDataset
import com.picnicml.doddlemodel.data.loadIrisDataset
import com.picnicml.doddlemodel.linear.SoftmaxClassifier
import com.picnicml.doddlemodel.metrics.accuracy
import com.picnicml.doddlemodel.pipeline.Pipeline
import com.picnicml.doddlemodel.preprocessing.StandardScaler

import scala.util.Random

object PipelineExample extends App {
  // load and shuffle the data
  implicit val rand: Random = new Random(0)
  val (x, y) = (shuffleDataset _).tupled(loadIrisDataset)

  // split the data
  val trIndices = 0 until x.rows / 2
  val teIndices = x.rows / 2 until x.rows
  val (xTr, yTr) = (x(trIndices, ::), y(trIndices))
  val (xTe, yTe) = (x(teIndices, ::), y(teIndices))
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val pipeline = Pipeline(StandardScaler())(SoftmaxClassifier())
  val trainedPipeline = pipeline.fit(xTr, yTr)

  val score = accuracy(yTe, trainedPipeline.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
