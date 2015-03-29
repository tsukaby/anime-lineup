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

}
