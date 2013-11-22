package com.qiuzhuang.df

import java.io.{DataOutput, DataInput}

/**
 * All rights reserved by Qiuzhuang.Lian
 */
class Leaf(var label: Double = 0) extends Node {
  val EPSILON = 1.0e-6

  // def this() = this(0.0)

  def classify(features: Array[Double]): Double = label

  def nbnodes(): Double = 1

  override def getNodeType() = NodeType.LEAF

  def maxDepth(): Double = 1

  def getString(): String = ""

  def readFields(in: DataInput) = {
    label = in.readDouble()
  }

  def writeNode(out: DataOutput) = out.writeDouble(label)

  override def hashCode(): Int = {
    val bits = java.lang.Double.doubleToLongBits(label)
    (bits ^ (bits >>> 32).toInt).toInt
  }

  override def equals(that: Any): Boolean = {
    if (this == that) true
    if (!that.isInstanceOf[Leaf]) false
    val leaf = that.asInstanceOf[Leaf]
    return scala.math.abs(label - leaf.label) < EPSILON
  }
}
