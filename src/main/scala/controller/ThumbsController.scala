package controller

import java.io.{ByteArrayOutputStream, FileInputStream}

import com.sksamuel.scrimage.Position
import model.Image
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.{Dimension, OutputType}
import skinny._

class ThumbsController extends SkinnyController {
  protectFromForgery()

  def show = params.getAs[String]("url").map { url =>
    contentType = "image/jpeg"

    Image.findById(url).map(_.content).getOrElse {
      val img = getImage(url)
      Image.createWithAttributes(
        'image_id -> url,
        'content -> img
      )
      img
    }
  }.getOrElse(haltWithBody(404))

  /**
   * 引数で指定したWebページのサムネイル画像を取得します。
   * @param url サムネイル画像を取得するページのURL
   * @return サムネイル画像のバイナリ
   */
  def getImage(url: String): Array[Byte] = {
    implicit val driverTmp = new PhantomJSDriver()
    driverTmp.get(url)
    driverTmp.manage().window().setSize(new Dimension(1024, 768))
    val file = driverTmp.getScreenshotAs(OutputType.FILE)

    val fis = new FileInputStream(file)
    // 横幅を400に変更 GhostDriver側ではあくまでウィンドウのサイズを設定できるだけで、キャプチャは意図通りのサイズにならない
    // そのため、ここで400に変更
    //val img = Image(fis).scaleToWidth(400).resizeTo(400, 100, Position.TopLeft)
    val img = com.sksamuel.scrimage.Image(fis).scaleToWidth(400).resizeTo(400, 400, Position.TopLeft).writer(com.sksamuel.scrimage.Format.JPEG).withCompression(80)

    fis.close()
    driverTmp.close()


    val baos = new ByteArrayOutputStream()
    img.write(baos)
    val result = baos.toByteArray
    baos.close()

    result
  }
}
