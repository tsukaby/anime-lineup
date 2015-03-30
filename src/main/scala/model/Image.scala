package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Image(
  imageId: String,
  content: Array[Byte]
)

object Image extends SkinnyCRUDMapperWithId[String, Image] {
  override lazy val tableName = "image"
  override lazy val defaultAlias = createAlias("i")
  override lazy val primaryKeyFieldName = "imageId"
  override def idToRawValue(id: String): Any = id
  override def rawValueToId(value: Any): String = value.toString
  override def useExternalIdGenerator = true
  override def generateId = java.util.UUID.randomUUID.toString

  /*
   * If you're familiar with ScalikeJDBC/Skinny ORM, using #autoConstruct makes your mapper simpler.
   * (e.g.)
   * override def extract(rs: WrappedResultSet, rn: ResultName[Image]) = autoConstruct(rs, rn)
   *
   * Be aware of excluding associations like this:
   * (e.g.)
   * case class Member(id: Long, companyId: Long, company: Option[Company] = None)
   * object Member extends SkinnyCRUDMapper[Member] {
   *   override def extract(rs: WrappedResultSet, rn: ResultName[Member]) =
   *     autoConstruct(rs, rn, "company") // "company" will be skipped
   * }
   */
  override def extract(rs: WrappedResultSet, rn: ResultName[Image]): Image = new Image(
    imageId = rs.get(rn.imageId),
    content = rs.get(rn.content)
  )
}
