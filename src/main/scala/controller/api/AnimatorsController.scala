package controller.api

import model.Animator
import skinny._
import skinny.controller.SkinnyApiResource
import skinny.controller.feature.AngularXHRServerFeature
import skinny.validator._

class AnimatorsController extends SkinnyApiResource with AngularXHRServerFeature {
  protectFromForgery()

  override def model = Animator
  override def resourcesName = "animators"
  override def resourceName = "animator"

  override def resourcesBasePath = s"/api/$resourcesName"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("name") is required & maxLength(255),
    paramKey("wikipedia_site_url") is required & maxLength(255)
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "wikipedia_site_url" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("name") is required & maxLength(255),
    paramKey("wikipedia_site_url") is required & maxLength(255)
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "wikipedia_site_url" -> ParamType.String
  )

}
