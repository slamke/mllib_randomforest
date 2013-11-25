package com.qiuzhuang.df.node

import java.io.{DataOutput, DataInput}

/**
 * All rights reserved by Qiuzhuang.Lian
 */
class Leaf(private var label: Double = 0) extends Node {

  val EPSILON = 1.0e-6

  // def this() = this(0.0)

  override def classify(features: Array[Double]): Double = label

  override def nbnodes(): Long = 1

  override protected def getNodeType() = NodeType.LEAF

  override def maxDepth(): Long = 1

  override def getString(): String = ""

  override def readFields(in: DataInput) = {
    label = in.readDouble()
  }

  override def writeNode(out: DataOutput) = out.writeDouble(label)

  override def hashCode(): Int = {
    val bits = java.lang.Double.doubleToLongBits(label)
    (bits ^ (bits >>> 32).toInt).toInt
  }

  override def equals(that: Any): Boolean = {
    if (this == that) true
    if (!that.isInstanceOf[Leaf]) false
    val leaf = that.asInstanceOf[Leaf]
    scala.math.abs(label - leaf.label) < EPSILON
  }
}
