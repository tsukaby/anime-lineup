package controller

import java.io.{ByteArrayOutputStream, FileInputStream}

import com.sksamuel.scrimage.Position
import model.Image
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.{Dimension, OutputType}
import skinny._

class ThumbsController extends SkinnyController {
  protectFromForgery()

  def show = {
    (for {
      url <- params.getAs[String]("url")
      width <- params.getAs[Int]("width")
      height <- params.getAs[Int]("height")
    } yield {
        contentType = "image/jpeg"

        val imageId = s"$url?width=$width&height=$height"
        Image.findById(imageId).map(_.content).getOrElse {
          val content = getImage(url, width, height)
          Image.createWithAttributes(
            'image_id -> imageId,
            'content -> content
          )
          content
        }
      }) getOrElse haltWithBody(404)
  }

  /**
   * Get a thumbnail image on the web page.
   *
   * @param url Web site url for capturing.
   * @param width Capture image size.
   * @param height Capture image size.
   * @return Binary of thumbnail.
   */
  def getImage(url: String, width: Int, height: Int): Array[Byte] = {
    implicit val driverTmp = new PhantomJSDriver()
    driverTmp.get(url)
    driverTmp.manage().window().setSize(new Dimension(1024, 768))
    val file = driverTmp.getScreenshotAs(OutputType.FILE)

    val fis = new FileInputStream(file)
    val img = com.sksamuel.scrimage.Image(fis).scaleToWidth(width).resizeTo(width, height, Position.TopLeft).writer(com.sksamuel.scrimage.Format.JPEG).withCompression(80)

    fis.close()
    driverTmp.close()


    val baos = new ByteArrayOutputStream()
    img.write(baos)
    val result = baos.toByteArray
    baos.close()

    result
  }
}
