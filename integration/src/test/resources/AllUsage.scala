import scalaxb._
import allcontent._

object AllUsage {
  def main(args: Array[String]) = {
    allTests
  }

  def allTests = {
    testAll
    true
  }

  def testAll {
    val subject = <foo xmlns="http://www.example.com/all">
          <concrete1>bar</concrete1>
        </foo>
    val obj = fromXML[AllWithSubstitution](subject)
    obj match {
      case AllWithSubstitution(
          None,
          Some(DataRecord(Some("http://www.example.com/all"), Some("concrete1"), _))
        ) =>
      case _ => sys.error("match failed: " + obj.toString)
    }
    val document = toXML[AllWithSubstitution](obj, "foo", defaultScope)
    println(document)
  }
}
