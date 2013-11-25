package com.qiuzhuang.df.split

import java.util.Locale

/**
 * All rights reserved by Qiuzhuang.Lian
 */
class Split(
    private var attr: Int,
    private var ig: Double,
    private var split: Double = Double.NaN) {

  override def toString(): String = {
    "attr: %d, ig: %f, split: %f".formatLocal(Locale.getDefault, attr, ig, split)
  }
}
