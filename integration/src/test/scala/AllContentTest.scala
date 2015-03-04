import java.io.{File}

object AllContentTest extends TestBase {
  val inFile = new File("integration/src/test/resources/all.xsd")
  lazy val generated = module.process(inFile, "allcontent", tmp)
  val usageFile = new File(tmp, "AllUsage.scala")
  copyFileFromResource("AllUsage.scala", usageFile)

  "all.scala file must compile together with AllUsage.scala" in {
    (List("AllUsage.allTests"),
      usageFile :: generated) must evaluateTo(true,
      outdir = "./tmp")
  }
}
