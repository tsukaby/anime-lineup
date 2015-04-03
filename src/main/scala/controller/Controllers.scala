package controller

import _root_.controller.api.{SeasonsController, CompaniesController, AnimatorsController, AnimesController}
import skinny._
import skinny.controller.AssetsController

object Controllers {

  def mount(ctx: ServletContext): Unit = {
    apiCompanies.mount(ctx)
    apiAnimes.mount(ctx)
    apiAnimators.mount(ctx)
    apiSeasons.mount(ctx)
    thumbs.mount(ctx)
    fbAuth.mount(ctx)
    sAuth.mount(ctx)
    //root.mount(ctx)
    AssetsController.mount(ctx)
  }

  /*
  object root extends RootController with Routes {
    val indexUrl = get("/?")(index).as('index)
  }
  */
  object apiAnimators extends AnimatorsController with Routes {
    val indexUrl = get("/api/animator/?")(index).as('index)
  }

  object apiAnimes extends AnimesController with Routes {
    val indexUrl = get("/api/animes/?")(index).as('index)
  }

  object apiCompanies extends CompaniesController with Routes {
    val indexUrl = get("/api/companies/?")(index).as('index)
  }

  object apiSeasons extends SeasonsController with Routes {
    val indexUrl = get("/api/seasons/?")(index).as('index)
  }

  object thumbs extends ThumbsController with Routes {
    val showUrl = get("/thumbs/:url")(show).as('show)
  }

  object fbAuth extends FBAuthController with Routes {
    val facebookLoginUrl = post("/auth/facebook")(loginRedirect).as('facebookLogin)
    val facebookLoginCallbackUrl = get("/auth/facebook/callback")(callback).as('facebookLogin)
  }

  object sAuth extends SessionsController with Routes {
    val googleLoginUrl = post("/auth/google")(loginRedirect).as('googleLogin)
    val googleLoginCallbackUrl = get("/auth/google/callback")(callback).as('googleLogin)
    val twitterLoginUrl = post("/auth/twitter")(loginRedirect).as('twitterLogin)
    val twitterLoginCallbackUrl = get("/auth/twitter/callback")(callback).as('twitterLogin)
  }

}
