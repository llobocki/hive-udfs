package org.lobocki.hadoop.hive.udf.string

import org.apache.hadoop.io.Text
import org.scalatest.FunSuite

class TestCapitalize extends FunSuite {
  var Capitalze = new Capitalize
  test("null") {
    assert(Capitalze.evaluate(null) == null)
  }
  test("empty string") {
    assert(Capitalze.evaluate(new Text("")) == new Text(""))
  }
  test("first") {
    assert(Capitalze.evaluate(new Text("l")) == new Text("L"))
  }
  test("First") {
    assert(Capitalze.evaluate(new Text("L")) == new Text("L"))
  }

  test("lower") {
    assert(Capitalze.evaluate(new Text("lukasz")) == new Text("Lukasz"))
  }
  test("upper") {
    assert(Capitalze.evaluate(new Text("LUKASZ")) == new Text("Lukasz"))
  }
}