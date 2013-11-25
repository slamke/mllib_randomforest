package com.qiuzhuang.df.builder

import org.apache.spark.Logging
import scala.util.Random
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression.LabeledPoint
import com.qiuzhuang.df.node.Node
import com.qiuzhuang.df.split.IgSplit

/**
 * All rights reserved by Qiuzhuang.Lian
 *
 * Builds a Decision Tree <br>
 * Based on the algorithm described in the "Decision Trees" tutorials by Andrew W. Moore, available at:<br>
 * <br>
 * http://www.cs.cmu.edu/~awm/tutorials
 * <br><br>
 * This class can be used when the criterion variable is the categorical attribute.
 */
class DefaultTreeBuilder(
    private var selected: Array[Boolean],
    private var m: Int = 1,
    private var igSplit: IgSplit
  ) extends TreeBuilder with Logging {


  /**
   * Builds a Decision tree using the training data
   */
  def build(rng: Random, data: RDD[LabeledPoint]): Node = null
}

object DefaultTreeBuilder {
  val NO_ATTRIBUTES = new Array[Int](0)
}
