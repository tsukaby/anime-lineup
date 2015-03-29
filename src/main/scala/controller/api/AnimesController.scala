package controller.api

import model.Anime
import skinny._
import skinny.controller.SkinnyApiResource
import skinny.controller.feature.AngularXHRServerFeature
import skinny.validator._

class AnimesController extends SkinnyApiResource with AngularXHRServerFeature {
  protectFromForgery()

  override def model = Anime
  override def resourcesName = "animes"
  override def resourceName = "anime"

  override def resourcesBasePath = s"/api/$resourcesName"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("name") is required & maxLength(255),
    paramKey("year") is required & numeric & intValue,
    paramKey("season_type") is required & numeric & intValue,
    paramKey("production_company_id") is required & numeric & longValue,
    paramKey("director_id") is numeric & longValue,
    paramKey("original_piece") is required & maxLength(255),
    paramKey("official_site_url") is required & maxLength(255),
    paramKey("wikipedia_site_url") is required & maxLength(255)
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "year" -> ParamType.Int,
    "season_type" -> ParamType.Int,
    "production_company_id" -> ParamType.Long,
    "director_id" -> ParamType.Long,
    "original_piece" -> ParamType.String,
    "official_site_url" -> ParamType.String,
    "wikipedia_site_url" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("name") is required & maxLength(255),
    paramKey("year") is required & numeric & intValue,
    paramKey("season_type") is required & numeric & intValue,
    paramKey("production_company_id") is required & numeric & longValue,
    paramKey("director_id") is numeric & longValue,
    paramKey("original_piece") is required & maxLength(255),
    paramKey("official_site_url") is required & maxLength(255),
    paramKey("wikipedia_site_url") is required & maxLength(255)
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "year" -> ParamType.Int,
    "season_type" -> ParamType.Int,
    "production_company_id" -> ParamType.Long,
    "director_id" -> ParamType.Long,
    "original_piece" -> ParamType.String,
    "official_site_url" -> ParamType.String,
    "wikipedia_site_url" -> ParamType.String
  )

  def index = {

  }

}
