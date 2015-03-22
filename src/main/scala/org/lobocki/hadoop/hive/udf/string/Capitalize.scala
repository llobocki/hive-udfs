package org.lobocki.hadoop.hive.udf.string

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF
import org.apache.hadoop.io.Text

@Description(
  name = "Capitalize",
  value = "_FUNC_(word) takes a string and capitalizes it",
  extended = "SELECT capitalize('lukasz') FROM test LIMIT 1;")
class Capitalize extends UDF {
  def evaluate(input: Text): Text = input match {
    case null => null
    case _ => {
      if (input.getLength == 0) new Text("")
      else {
        val s = input.toString
        new Text(s(0).toString.toUpperCase + s.substring(1, s.length).toString.toLowerCase)
      }
    }
  }
}

