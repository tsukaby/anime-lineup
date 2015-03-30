package controller.api

import model.Anime
import org.joda.time.DateTime
import scalikejdbc._
import skinny._
import skinny.controller.feature.AngularXHRServerFeature

class AnimesController extends SkinnyApiController with AngularXHRServerFeature {
  protectFromForgery()

  def index = {
    val name = params.get("name")
    val year = params.get("year").getOrElse(DateTime.now().getYear)
    val seasonType = params.get("season_type").getOrElse(1)

    val baseSql = sqls.eq(Anime.defaultAlias.year, year).and.eq(Anime.defaultAlias.seasonType, seasonType)
    val sql = name.foldLeft(baseSql)((x, y) => x.like(Anime.defaultAlias.name, s"%$y%"))

    val result = Anime.findAllBy(sql)
    renderWithFormat(result)(Format.JSON)
  }
}
