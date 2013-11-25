package com.qiuzhuang.df.data

import org.apache.spark.util.Vector

/**
 * All rights reserved by Qiuzhuang.Lian
 */
class Instance(val attrs: Vector) {

  def get(index: Int): Double = {
    attrs.elements(index)
  }

  def set(index: Int, value: Double): Unit = {
    attrs.elements(index) = value
  }

  override def hashCode(): Int = {
    attrs.hashCode()
  }

  override def equals(that: Any): Boolean = {
    if (this == that) true
    if (!that.isInstanceOf[Instance]) false
    attrs.equals(that.asInstanceOf[Instance].attrs)
  }
}
