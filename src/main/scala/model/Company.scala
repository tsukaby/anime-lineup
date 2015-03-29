package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Company(
  companyId: Long,
  name: String,
  companyType: Int
)

object Company extends SkinnyCRUDMapper[Company] {
  override lazy val tableName = "company"
  override lazy val defaultAlias = createAlias("c")
  override lazy val primaryKeyFieldName = "companyId"

  override def extract(rs: WrappedResultSet, rn: ResultName[Company]): Company = {
    autoConstruct(rs, rn)
  }
}
