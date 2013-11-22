package com.qiuzhuang.df

import org.apache.hadoop.io.Writable;
import org.apache.spark.{SparkException, Logging}
import java.io.{DataOutput, DataInput}

/**
 * All rights reserved by Qiuzhuang.Lian
 */
abstract class Node extends Writable with Logging {

  def classify(features: Array[Double]): Double

  def nbnodes(): Double

  def maxDepth(): Double

  def getNodeType() = NodeType.NONE

  def getString(): String

  def writeNode(out: DataOutput)

  override def toString(): String = {
    getNodeType + ":" + getString() + ';'
  }

  override def write(out: DataOutput) {
    out.write(getNodeType.id)
    writeNode(out)
  }
}

object Node {
  def read(in: DataInput): Node = {
    val nodeType = NodeType(in.readInt())
    nodeType match {
      case NodeType.LEAF =>
        new Leaf()
      case NodeType.NUMERICAL =>
        new CategoricalNode
      case NodeType.CATEGORICAL =>
        new CategoricalNode
      case _ =>
        throw new IllegalArgumentException("Could not parse node type: '" + nodeType + "'")
    }
  }
}

object NodeType extends Enumeration("LEAF", "NUMERICAL", "CATEGORICAL", "NONE") {
	type NodeType = Value
	val LEAF, NUMERICAL, CATEGORICAL, NONE = Value
}
