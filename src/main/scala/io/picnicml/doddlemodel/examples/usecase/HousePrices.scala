package io.picnicml.doddlemodel.examples.usecase

import java.io.File

import breeze.numerics.log
import io.picnicml.doddlemodel.data.CsvLoader.loadCsvDataset
import io.picnicml.doddlemodel.data.DatasetUtils.{shuffleDataset, splitDataset}
import io.picnicml.doddlemodel.data.Feature.FeatureIndex
import io.picnicml.doddlemodel.data.{Features, Target, TrainTestSplit}
import io.picnicml.doddlemodel.impute.{MeanValueImputer, MostFrequentValueImputer}
import io.picnicml.doddlemodel.linear.LinearRegression
import io.picnicml.doddlemodel.metrics.rmse
import io.picnicml.doddlemodel.modelselection.{CrossValidation, HyperparameterSearch, KFoldSplitter}
import io.picnicml.doddlemodel.pipeline.Pipeline.pipe
import io.picnicml.doddlemodel.pipeline.{Pipeline, PipelineTransformers}
import io.picnicml.doddlemodel.preprocessing.{OneHotEncoder, StandardScaler}
import io.picnicml.doddlemodel.syntax.PredictorSyntax._

import scala.util.Random

object HousePrices extends App {
  implicit val seed: Random = new Random(0)

  // data downloaded from https://www.kaggle.com/c/house-prices-advanced-regression-techniques/data
  // note that an additional header line that encodes feature types as described on
  // https://github.com/picnicml/doddle-model-examples/wiki/Reading-CSV-Data is required
  val (x, y, featureIndex) = loadData("/path/to/house-prices.csv")
  val split = shuffleSplitData(x, y)
  val selectedModel = gridSearch(split, featureIndex)

  val score = rmse(split.yTe, selectedModel.predict(split.xTe))
  println(f"Test RMSE of the selected model: $score%1.4f")

  def loadData(path: String): (Features, Target, FeatureIndex) = {
    println("Loading data")
    val (data, allFeatures) = loadCsvDataset(new File(path))
    val (x, y) = (data(::, 0 to -2), data(::, -1))
    val featureIndex = allFeatures.drop(x.cols)

    println(s"Features: $featureIndex")
    println(s"Number of rows: ${x.rows}")
    println(s"Number of columns: ${x.cols}\n")

    // we'll be predicting logarithm of the price
    (x, log(y), featureIndex)
  }

  def shuffleSplitData(x: Features, y: Target): TrainTestSplit = {
    println("Shuffling and splitting data")
    val (xShuffled, yShuffled) = shuffleDataset(x, y)
    val split = splitDataset(xShuffled, yShuffled, proportionTrain = 0.85)

    println(s"Training set size: ${split.xTr.rows}, test set size: ${split.xTe.rows}\n")
    split
  }

  def gridSearch(split: TrainTestSplit, featureIndex: FeatureIndex): Pipeline = {
    val numGridSearchIterations = 50

    val cv: CrossValidation = CrossValidation(rmse, KFoldSplitter(numFolds = 10))
    val search = HyperparameterSearch(numGridSearchIterations, cv)

    val (start, end, step) = (1e-5, 5.0, (5.0 - 1e-5) / numGridSearchIterations)
    val grid = Range.BigDecimal(start, end, step).map(_.toDouble).toIterator

    println("Searching the hyperparameter space")
    search.bestOf(split.xTr, split.yTr) { generateModel(lambda = grid.next) }
  }

  def generateModel(lambda: Double): Pipeline = {
    val transformers: PipelineTransformers = List(
      // numerical features
      pipe(MeanValueImputer(featureIndex)),
      pipe(StandardScaler(featureIndex)),
      // categorical features
      pipe(MostFrequentValueImputer(featureIndex)),
      pipe(OneHotEncoder(featureIndex))
    )
    // lambda is L2 regularization strength
    Pipeline(transformers)(pipe(LinearRegression(lambda)))
  }
}
