package controller.api

import model.Anime
import scalikejdbc._
import skinny._
import skinny.controller.feature.AngularXHRServerFeature

class AnimesController extends SkinnyApiController with AngularXHRServerFeature {
  protectFromForgery()

  def index = {
    val name = params.get("name")

    val sql = name.map(x => sqls.like(Anime.defaultAlias.name, s"%$x%"))

    val result = sql.map(x => Anime.findAllBy(x)).getOrElse(Anime.findAll())
    renderWithFormat(result)(Format.JSON)
  }
}
