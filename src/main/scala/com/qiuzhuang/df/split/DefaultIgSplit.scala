package com.qiuzhuang.df.split

import org.apache.spark.Logging
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint

/**
 * All rights reserved by Qiuzhuang.Lian
 */
class DefaultIgSplit(
    var counts: Array[Int]
  ) extends IgSplit with Logging {

  /**
   * Computes the best split for the given attribute
   */
  override def computeSplit(data: RDD[LabeledPoint], attr: Int): Split = {

       null
  }

}
