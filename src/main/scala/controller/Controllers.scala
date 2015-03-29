package controller

import _root_.controller.api.{CompaniesController, AnimatorsController, AnimesController}
import skinny._
import skinny.controller.AssetsController

object Controllers {

  def mount(ctx: ServletContext): Unit = {
    apiCompanies.mount(ctx)
    apiAnimes.mount(ctx)
    apiAnimators.mount(ctx)
    //root.mount(ctx)
    AssetsController.mount(ctx)
  }

  /*
  object root extends RootController with Routes {
    val indexUrl = get("/?")(index).as('index)
  }
  */
  object apiAnimators extends AnimatorsController with Routes {
  }

  object apiAnimes extends AnimesController with Routes {
  }

  object apiCompanies extends CompaniesController with Routes {
  }

}
