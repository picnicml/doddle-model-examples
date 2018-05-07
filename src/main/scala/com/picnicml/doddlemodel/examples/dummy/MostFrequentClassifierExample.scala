package com.picnicml.doddlemodel.examples.dummy

import com.picnicml.doddlemodel.data.Utils.shuffleDataset
import com.picnicml.doddlemodel.data.loadIrisDataset
import com.picnicml.doddlemodel.dummy.classification.MostFrequentClassifier
import com.picnicml.doddlemodel.metrics.accuracy

object MostFrequentClassifierExample extends App {
  val (x, y) = loadIrisDataset
  println(s"number of examples: ${x.rows}, number of features: ${x.cols}")

  // shuffle the data
  val (xShuffled, yShuffled) = shuffleDataset(x, y)

  // split the data
  val trIndices = 0 until x.rows / 2
  val teIndices = x.rows / 2 until x.rows
  val (xTr, yTr) = (xShuffled(trIndices, ::), yShuffled(trIndices))
  val (xTe, yTe) = (xShuffled(teIndices, ::), yShuffled(teIndices))
  println(s"training set size: ${xTr.rows}, test set size: ${xTe.rows}")

  val model = MostFrequentClassifier()
  val trainedModel = model.fit(xTr, yTr)

  val score = accuracy(yTe, trainedModel.predict(xTe))
  println(f"test accuracy: $score%1.4f")
}
