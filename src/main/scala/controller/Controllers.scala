package controller

import _root_.controller.api._
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
    googleAuth.mount(ctx)
    twitterAuth.mount(ctx)
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
    val facebookLoginUrl = get("/api/auth/facebook")(loginRedirect).as('facebookLogin)
    val facebookLoginCallbackUrl = get("/api/auth/facebook/callback")(callback).as('facebookLogin)
  }
  
  object googleAuth extends GoogleAuthController with Routes {
    val googleLoginUrl = get("/api/auth/google")(loginRedirect).as('googleLogin)
    val googleLoginCallbackUrl = get("/api/auth/google/callback")(callback).as('googleLogin)
  }

  object twitterAuth extends TwitterAuthController with Routes {
    val twitterLoginUrl = get("/api/auth/twitter")(loginRedirect).as('twitterLogin)
    val twitterLoginCallbackUrl = get("/api/auth/twitter/callback")(callback).as('twitterLogin)
  }

}
