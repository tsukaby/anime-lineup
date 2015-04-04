package controller.api

import controller.ApplicationController
import model.User
import scalikejdbc._
import skinny.controller.feature.FacebookLoginFeature
import skinny.oauth2.client.facebook.FacebookUser

class FBAuthController extends ApplicationController with FacebookLoginFeature {
  // these env variables are expected by default
  // SKINNY_OAUTH2_CLIENT_ID_FACEBOOK
  // SKINNY_OAUTH2_CLIENT_SECRET_FACEBOOK


  override def redirectURI = if (isDevelopmentMode) {
    "http://local.anime-lineup.tsukaby.com:8080/"
  } else {
    "https://anime-lineup.tsukaby.com/"
  }

  override protected def saveAuthorizedUser(fbUser: FacebookUser): Unit = {
    val user = User.findBy(sqls.eq(User.defaultAlias.facebookUserId, fbUser.id)).getOrElse {
      val createdId = User.createWithAttributes(
        'name -> "foo",
        'facebook_user_id -> fbUser.id
      )
      User.findById(createdId).get
    }
    session.setAttribute("currentUser", user)
  }

  override protected def handleWhenLoginFailed(): Any = {
    //flash("warn") = "Login failed. Please try again."
    println("Login failed. Please try again.")
    redirect302("/")
  }

  override protected def handleWhenLoginSucceeded(): Any = {
    //flash("info") = "You have successfully registered and logged in."
    println("You have successfully registered and logged in.")
    redirect302("/")
  }
}
