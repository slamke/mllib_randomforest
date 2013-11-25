package com.qiuzhuang.df.node

import java.io.{DataOutput, DataInput}
import scala.Double

/**
 * All rights reserved by Qiuzhuang.Lian
 */
class NumericalNode(
    private var attr: Int,
    private var split: Double,
    private var loChild: Node,
    private var hiChild: Node) extends Node {

  def classify(features: Array[Double]): Double = {
    if (features(attr) < split) {
      loChild.classify(features)
    } else {
      hiChild.classify(features)
    }
  }

  override protected def getNodeType() = NodeType.NUMERICAL

  def nbnodes(): Long = 1 + loChild.nbnodes + hiChild.nbnodes

  def maxDepth(): Long = 1 + scala.math.max(loChild.maxDepth, hiChild.maxDepth)

  override def equals(that: Any): Boolean = {
    if (this == that) true
    if (!that.isInstanceOf[NumericalNode]) false
    val thatNode = that.asInstanceOf[NumericalNode]
    thatNode.attr == attr &&
    thatNode.split == split &&
    thatNode.loChild.equals(loChild) &&
    thatNode.hiChild.equals(hiChild)
  }

  override def hashCode(): Int = {
    attr + java.lang.Double.doubleToLongBits(split).asInstanceOf[Int] + loChild.hashCode + hiChild.hashCode
  }

  override def getString(): String = {
    loChild.toString() + ", " + hiChild.toString()
  }

  override def writeNode(out: DataOutput) {
    out.writeInt(attr)
    out.writeDouble(split)
    loChild.write(out)
    hiChild.write(out)
  }

  override def readFields(in: DataInput) {
    attr = in.readInt
    split = in.readDouble
    loChild = Node.read(in)
    hiChild = Node.read(in)
  }
}
