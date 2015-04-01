package controller.api

import model.Company
import scalikejdbc._
import skinny._
import skinny.controller.feature.AngularXHRServerFeature

class CompaniesController extends SkinnyApiController with AngularXHRServerFeature {
  protectFromForgery()

  def index = {
    val name = params.get("name")

    val sql = name.map(x => sqls.like(Company.defaultAlias.name, s"%$x%"))

    val result = sql.map(x => Company.findAllBy(x)).getOrElse(Company.findAll())
    renderWithFormat(result)(Format.JSON)
  }
}
