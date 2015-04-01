package controller.api

import model.Animator
import scalikejdbc._
import skinny._
import skinny.controller.feature.AngularXHRServerFeature

class AnimatorsController extends SkinnyApiController with AngularXHRServerFeature {
  protectFromForgery()

  def index = {
    val name = params.get("name")

    val sql = name.map(x => sqls.like(Animator.defaultAlias.name, s"%$x%"))

    val result = sql.map(x => Animator.findAllBy(x)).getOrElse(Animator.findAll())
    renderWithFormat(result)(Format.JSON)
  }
}
