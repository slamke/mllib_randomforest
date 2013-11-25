package com.qiuzhuang.df.data

/**
 * All rights reserved by Qiuzhuang.Lian
 */
class Dataset(
    var attributes: Array[FeatureAttribute],
    var values: Array[Array[String]],
    var nbInstances: Int
  ) {

  private var ignored: Array[Int] = null
  private var labelId: Int = -1

  def init(attrsIn: Array[FeatureAttribute],
           valuesIn: Array[List[String]],
           regression: Boolean): Unit = {
    val nbattrs = countAttributes(attrsIn)
    attributes = new Array[FeatureAttribute](nbattrs)
    values = new Array[Array[String]](nbattrs)
    ignored = new Array[Int](attrsIn.length - nbattrs)
    var ignoredId: Int = 0
    var ind: Int = 0
    for (attr <- 0 until attrsIn.length) {
      if (attrsIn(attr).isIgnored) {
        ignored(ignoredId) = attr
        ignoredId = ignoredId + 1
      } else {
        if (attrsIn(attr).isLabel) {
          if (labelId != -1) {
            throw new IllegalStateException("Label found more than once")
          }
          labelId = ind
          if (regression) {
            attrsIn(attr) =  new FeatureAttribute(FeatureType.NUMERICAL.toString)
          } else {
            attrsIn(attr) =  new FeatureAttribute(FeatureType.CATEGORICAL.toString)
          }
        }

        if (attrsIn(attr).isCategorical || (!regression && attrsIn(attr).isLabel)) {
          values(ind) = new Array[String](valuesIn(attr).size)
          values(ind) = valuesIn(attr).toArray
        }
        attributes(ind) = attrsIn(attr)
        ind = ind + 1
     }
    }

    if (labelId != -1) {
      throw new IllegalStateException("Label not found")
    }
  }

  private def countAttributes(attrsIn: Array[FeatureAttribute]): Int = {
    var nbAttrs = 0
    for (featureAttribute <- attrsIn if !featureAttribute.isIgnored()) yield nbAttrs = (nbAttrs+1)
    nbAttrs
  }
}

class FeatureAttribute(val featureType: String)  {

  def isNumerical(): Boolean = {
    "NUMERICAL".equalsIgnoreCase(featureType)
  }

  def isCategorical(): Boolean = {
    "CATEGORICAL".equalsIgnoreCase(featureType)
  }

  def isLabel(): Boolean = {
    "LABEL".equalsIgnoreCase(featureType)
  }

  def isIgnored(): Boolean = {
    "IGNORED".equalsIgnoreCase(featureType)
  }
}

object FeatureType extends Enumeration("NUMERICAL", "CATEGORICAL", "LABEL", "IGNORED") {
  type NodeType = Value
  val NUMERICAL, CATEGORICAL, LABEL, IGNORED = Value
}

