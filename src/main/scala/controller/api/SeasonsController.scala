package controller.api

import model.Season
import skinny._
import skinny.controller.feature.AngularXHRServerFeature

class SeasonsController extends SkinnyApiController with AngularXHRServerFeature {
  protectFromForgery()

  def index = {
    val result = Season.findAll()
    renderWithFormat(result)(Format.JSON)
  }
}
