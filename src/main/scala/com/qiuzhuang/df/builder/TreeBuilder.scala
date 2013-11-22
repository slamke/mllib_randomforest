package com.qiuzhuang.df.builder

import scala.util.Random
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint

import com.qiuzhuang.df.node.Node

/**
 * All rights reserved by Qiuzhuang.Lian
 */
trait TreeBuilder {

  /**
   * Builds a Decision tree using the training data
   */
  def build(rng: Random, data: RDD[LabeledPoint]): Node
}
