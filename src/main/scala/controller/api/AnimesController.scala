package controller.api

import model.Anime
import scalikejdbc._
import skinny._
import skinny.controller.feature.AngularXHRServerFeature

class AnimesController extends SkinnyApiController with AngularXHRServerFeature {
  protectFromForgery()

  def index = {
    val name = params.get("name")
    val year = params.get("year")
    val seasonType = params.get("season_type")

    val sql = sqls.toAndConditionOpt(
      name.map(x => sqls.like(Anime.defaultAlias.name, s"%$x%")),
      year.map(x => sqls.eq(Anime.defaultAlias.year, x)),
      seasonType.map(x => sqls.eq(Anime.defaultAlias.seasonType, x))
    )
    val result = sql.map(x => Anime.findAllBy(x)).getOrElse(Anime.findAll())
    renderWithFormat(result)(Format.JSON)
  }
}
