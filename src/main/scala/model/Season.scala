package model

import scalikejdbc._
import skinny.orm._

case class Season(
                   year: Int,
                   seasonType: Int
                   )

object Season extends SkinnyNoIdCRUDMapper[Season] {
  override lazy val tableName = "season"
  override lazy val defaultAlias = createAlias("s")

  override def extract(rs: WrappedResultSet, rn: ResultName[Season]): Season = {
    autoConstruct(rs, rn)
  }
}
