package com.qiuzhuang.df.split

import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint
import java.lang.Math

/**
 * All rights reserved by Qiuzhuang.Lian
 */
trait IgSplit {
  /**
   * Computes the best split for the given attribute
   */
  def computeSplit(data: RDD[LabeledPoint], attr: Int): Split
}

object IgSplit {
  final val LOG2: Double = Math.log(2.0)
}

