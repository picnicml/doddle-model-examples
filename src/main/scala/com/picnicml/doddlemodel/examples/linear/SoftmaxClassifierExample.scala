package com.picnicml.doddlemodel.examples.linear

import breeze.linalg.shuffle
import com.picnicml.doddlemodel.data.loadIrisDataset
import com.picnicml.doddlemodel.linear.SoftmaxClassifier
import com.picnicml.doddlemodel.metrics.accuracy

object SoftmaxClassifierExample extends App {
  val (x, y) = loadIrisDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  // shuffle the data
  val shuffleIndices = shuffle(0 until y.length)
  val xShuffled = x(shuffleIndices, ::).toDenseMatrix
  val yShuffled = y(shuffleIndices).toDenseVector

  // split the data
  val trIndices = 0 until x.rows / 2
  val teIndices = x.rows / 2 until x.rows
  val (xTr, yTr) = (xShuffled(trIndices, ::), yShuffled(trIndices))
  val (xTe, yTe) = (xShuffled(teIndices, ::), yShuffled(teIndices))
  println(s"training set size: ${xTr.rows}, validation set size: ${xTe.rows}")

  // lambda is L2 regularization strength
  val model = SoftmaxClassifier(lambda = 1.5)
  val trainedModel = model.fit(xTr, yTr)

  val score = accuracy(yTe, trainedModel.predict(xTe))
  println(f"validation accuracy: $score%1.4f")
}
