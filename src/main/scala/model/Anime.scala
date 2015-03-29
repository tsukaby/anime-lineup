package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Anime(
  animeId: Long,
  name: String,
  year: Int,
  seasonType: Int,
  productionCompanyId: Long,
  directorId: Option[Long] = None,
  originalPiece: String,
  officialSiteUrl: String,
  wikipediaSiteUrl: String,
  productionCompany: Option[Company] = None,
  director: Option[Animator] = None
)

object Anime extends SkinnyCRUDMapper[Anime] {
  override lazy val tableName = "anime"
  override lazy val defaultAlias = createAlias("anime")
  override lazy val primaryKeyFieldName = "animeId"

  belongsToWithFk[Company](Company, "production_company_id", (a, pc) => a.copy(productionCompany = pc)).byDefault

  //lazy val directorIdRef =
  belongsToWithFk[Animator](Animator, "director_id", (a, opt) => a.copy(director = opt)).byDefault

  override def extract(rs: WrappedResultSet, rn: ResultName[Anime]): Anime = {
    autoConstruct(rs, rn, "productionCompany", "director")
  }
}
