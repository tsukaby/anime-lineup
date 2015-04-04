package controller.api

import controller.ApplicationController
import model.User
import scalikejdbc._
import skinny.controller.feature.GoogleLoginFeature
import skinny.oauth2.client.google.GoogleUser

class GoogleAuthController extends ApplicationController with GoogleLoginFeature {
  // these env variables are expected by default
  // SKINNY_OAUTH2_CLIENT_ID_GOOGLE
  // SKINNY_OAUTH2_CLIENT_SECRET_GOOGLE

  override protected def saveAuthorizedUser(gUser: GoogleUser): Unit = {
    val user = User.findBy(sqls.eq(User.defaultAlias.googleUserId, gUser.id)).getOrElse {
      val createdId = User.createWithAttributes(
        'name -> "foo",
        'googler_user_id -> gUser.id
      )
      User.findById(createdId).get
    }
    session.setAttribute("currentUser", user)
  }

  override protected def redirectURI: String = if (isDevelopmentMode) {
    val port = if(serverPort == 80) "" else s":$serverPort"
    s"http://local.anime-lineup.tsukaby.com$port/api/auth/google/callback"
  } else {
    s"https://anime-lineup.tsukaby.com/api/auth/google/callback"
  }

  override protected def handleWhenLoginSucceeded(): Any = {
    redirect302("/")
  }
}
