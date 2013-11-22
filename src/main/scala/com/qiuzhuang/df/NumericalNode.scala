package com.qiuzhuang.df

import java.io.{DataOutput, DataInput}

/**
 * All rights reserved by Qiuzhuang.Lian
 */
class NumericalNode extends Node {
  def classify(features: Array[Double]): Double = 0.0

  def nbnodes(): Double = 0.0

  def maxDepth(): Double = 0.0

  def getString(): String = null

  def writeNode(out: DataOutput) {}

  def readFields(p1: DataInput) {}
}
