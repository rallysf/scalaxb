import scalaxb._
import allcontent._

object AllUsage {
  def main(args: Array[String]) = {
    allTests
  }

  def allTests = {
    testAll
    testChoice
    testChoiceWithComplexAbstract
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

  def testChoice {
    val subject = <foo xmlns="http://www.example.com/all">
          <concrete1>bar</concrete1>
          <field2>12345</field2>
        </foo>
    val obj = fromXML[ChoiceSeq](subject)
    obj match {
      case ChoiceSeq(
          DataRecord(
            _,
            _,
            ChoiceSeqSequence2(
              DataRecord(Some("http://www.example.com/all"), Some("concrete1"), _),
              _
            )
          )
        ) =>
      case _ => sys.error("match failed: " + obj.toString)
    }
    val document = toXML[ChoiceSeq](obj, "foo", defaultScope)
    println(document)
  }

  def testChoiceWithComplexAbstract {
    val subject = <foo xmlns="http://www.example.com/all">
          <complexConcrete2>
            <field1>bar</field1>
          </complexConcrete2>
        </foo>
    val obj = fromXML[ChoiceWithComplexAbstractType](subject)
    obj match {
      case ChoiceWithComplexAbstractType(
            DataRecord(_, _, ComplexAbstractType("bar"))
          ) =>
      case _ => sys.error("match failed: " + obj.toString)
    }
    val document = toXML[ChoiceWithComplexAbstractType](obj, "foo", defaultScope)
    println(document)
  }
}
