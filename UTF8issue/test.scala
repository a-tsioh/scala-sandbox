import scala.collection.mutable.ListBuffer
import java.lang.String
import java.io.{File, FileInputStream}


object CodePointReader {

  def apply(data: Array[Byte]):Array[Int] = {
    val utf8 = new String(data, "UTF8")
    var i = 0
    var result = new ListBuffer[Int]()
    while (i < utf8.length) {
      val cp = utf8.codePointAt(i)
      i += Character.charCount(cp)
      result.append(cp)
    }
    return result.toArray
  }
}

object Main extends App {
  val buffsize = 256
  val path = args(0)
  val file = new File(path)
  val stream = new FileInputStream(path)
  val length: Int = file.length().toInt
  val data = new Array[Byte](length)
  stream.read(data)

  for (i <- CodePointReader(data))
    println(i)
}




