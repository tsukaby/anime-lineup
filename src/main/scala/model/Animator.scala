package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Animator(
  animatorId: Long,
  name: String,
  wikipediaSiteUrl: String
)

object Animator extends SkinnyCRUDMapper[Animator] {
  override lazy val tableName = "animator"
  override lazy val defaultAlias = createAlias("animator")
  override lazy val primaryKeyFieldName = "animatorId"

  override def extract(rs: WrappedResultSet, rn: ResultName[Animator]): Animator = {
    autoConstruct(rs, rn)
  }
}
