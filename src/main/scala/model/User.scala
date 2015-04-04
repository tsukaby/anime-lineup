package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class User(
  userId: Long,
  name: String,
  facebookUserId: Option[Long] = None,
  googleUserId: Option[Long] = None,
  twitterUserId: Option[Long] = None,
  createdAt: DateTime,
  updatedAt: DateTime
)

object User extends SkinnyCRUDMapper[User] {
  override lazy val tableName = "user"
  override lazy val defaultAlias = createAlias("u")
  override lazy val primaryKeyFieldName = "userId"

  /*
   * If you're familiar with ScalikeJDBC/Skinny ORM, using #autoConstruct makes your mapper simpler.
   * (e.g.)
   * override def extract(rs: WrappedResultSet, rn: ResultName[User]) = autoConstruct(rs, rn)
   *
   * Be aware of excluding associations like this:
   * (e.g.)
   * case class Member(id: Long, companyId: Long, company: Option[Company] = None)
   * object Member extends SkinnyCRUDMapper[Member] {
   *   override def extract(rs: WrappedResultSet, rn: ResultName[Member]) =
   *     autoConstruct(rs, rn, "company") // "company" will be skipped
   * }
   */
  override def extract(rs: WrappedResultSet, rn: ResultName[User]): User = new User(
    userId = rs.get(rn.userId),
    name = rs.get(rn.name),
    facebookUserId = rs.get(rn.facebookUserId),
    googleUserId = rs.get(rn.googleUserId),
    twitterUserId = rs.get(rn.twitterUserId),
    createdAt = rs.get(rn.createdAt),
    updatedAt = rs.get(rn.updatedAt)
  )
}
