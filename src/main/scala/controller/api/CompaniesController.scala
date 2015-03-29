package controller.api

import model.Company
import skinny._
import skinny.controller.SkinnyApiResource
import skinny.controller.feature.AngularXHRServerFeature
import skinny.validator._

class CompaniesController extends SkinnyApiResource with AngularXHRServerFeature {
  protectFromForgery()

  override def model = Company
  override def resourcesName = "companies"
  override def resourceName = "company"

  override def resourcesBasePath = s"/api/$resourcesName"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("name") is required & maxLength(255),
    paramKey("company_type") is required & numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "company_type" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("name") is required & maxLength(255),
    paramKey("company_type") is required & numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "company_type" -> ParamType.Int
  )

}
