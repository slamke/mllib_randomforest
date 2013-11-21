package com.qiuzhuang.df

import org.apache.hadoop.io.Writable;
import org.apache.spark.{Logging, SparkException}
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint
import java.lang.Math
import java.io.DataInput

/**
 * All rights reserved by Qiuzhuang.Lian
 */
abstract class Node extends Writable with Logging {

  def classify(features: Array[Double]): Double

  def nbnodes(): Double

  def maxDepth(): Double


}

object Node {
  /*
  def Node read(in: DataInput) {

  }
  */
}

object NodeType extends Enumeration("LEAF", "NUMERICAL", "CATEGORICAL") {
	type NodeType = Value
	val LEAF, NUMERICAL, CATEGORICAL = Value
}
