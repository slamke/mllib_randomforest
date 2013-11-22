package com.qiuzhuang.df

import java.io.{DataOutput, DataInput}

/**
 * All rights reserved by Qiuzhuang.Lian
 */
class CategoricalNode(
    var attr: Int = 0,
    var values: Array[Double] = null,
    var children: Array[Node] = null
  ) extends Node {

  override def classify(features: Array[Double]): Double = {
    val attVal = features(attr)
    var attValIndex = -1
    for (i <- 0 until features.length if attVal == features(i)) yield attValIndex = i
    if (attValIndex == -1) {
      Double.NaN
    } else {
      children(attValIndex).classify(features)
    }
  }

  override def nbnodes(): Long = {
    var nbNodes: Long = 1
    for (child <- children) {
      nbNodes += child.nbnodes
    }
    nbNodes
  }

  override def maxDepth(): Long = {
    var max: Long = 0
    for (child <- children) {
      val depth = child.maxDepth
      if (depth > max) {
        max = depth
      }
    }
    1 + max
  }

  override protected def getNodeType() = NodeType.CATEGORICAL

  override def equals(that: Any): Boolean = {
    if (this == that) true
    if (!that.isInstanceOf[CategoricalNode]) false
    val thatNode = that.asInstanceOf[CategoricalNode]
    thatNode.attr == attr &&
    thatNode.children.sameElements(children) &&
    thatNode.values.sameElements(values)
  }

  override def hashCode(): Int = {
    var hashCode = attr
    for (value <- values) {
      hashCode = 31 * hashCode + java.lang.Double.doubleToLongBits(hashCode).asInstanceOf[Int]
    }
    for (node <- children) {
      hashCode = 31 * hashCode + node.hashCode
    }
    hashCode
  }


  override def getString(): String = {
    val buffer = new StringBuilder
    for (child <- children) {
      buffer.append(child).append(',')
    }
    buffer.toString
  }

  override def writeNode(out: DataOutput) {
    out.writeInt(attr)
    for (value <- values) out.writeDouble(value)
    for (value <- children) value.write(out)
  }

  override def readFields(in: DataInput) {
    attr = in.readInt
    val length = in.readInt
    values = new Array[Double](length)
    for (i <- 0 until length) values(i) = in.readDouble()

    val length2 = in.readInt
    children = new Array[Node](length2)
    for (i <- 0 until length2) children(i) = Node.read(in)
  }
}
