package com.platform.server.spark

import java.io.File

import org.apache.spark.{SparkConf, SparkContext}

import scala.Iterator
import scala.collection.mutable.ArrayBuffer
import scala.reflect.io.Directory

class AppTest {

}

object AppTest {
  val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkTest")
  val spark = new SparkContext(sparkConf)
  val result = new Directory(new File("data/result"))
  val result2 = new Directory(new File("data/result2"))

  def main(args: Array[String]): Unit = {
    delResult()
    val file = spark.textFile("data/testfile")
    val map1 = file.flatMap(a => a.split(","))
    val map2 = file.mapPartitions(a => {
      val array = ArrayBuffer[(String, String)]()
      var key = ""
      var value = ""
      while(a.hasNext){
        val line = a.next()
        key = line.split(",")(0)
        value = line.split(",")(1)
        array.append((key, value))
      }
      array.iterator
    })

    map1.saveAsTextFile("data/result")
    map2.saveAsTextFile("data/result2")
    println("RDD info ==> Partitions : " + map2.getNumPartitions)
  }


  def delResult() = {
    if ( this.result.exists ) {
      this.result.deleteRecursively()
    }

    if ( this.result2.exists ) {
      this.result2.deleteRecursively()
    }
  }
}


