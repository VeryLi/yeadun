package com.platform.platform.spark

import java.util

import scala.collection.mutable.ArrayBuffer

object App {
  def main(args: Array[String]): Unit = {
    println( "Hello World!" )
    val a = new util.ArrayList[String]()
    val ite = Iterator(1,2,3,4)
    val array = ArrayBuffer[(String, String)]()
    while(ite.hasNext){
      val a = ite.next()
      val key = a + "key"
      val value = a + "value"
      array.append((key, value))
    }
    val itera = array.iterator
    println(itera.length)

  }

}
