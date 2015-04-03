package controller

import skinny.controller.feature.FacebookLoginFeature
import skinny.oauth2.client.facebook.FacebookUser

class FBAuthController extends ApplicationController with FacebookLoginFeature {
  // these env variables are expected by default
  // SKINNY_OAUTH2_CLIENT_ID_FACEBOOK
  // SKINNY_OAUTH2_CLIENT_SECRET_FACEBOOK

  override def redirectURI = "http://localhost:8080/auth/facebook/callback"

  override protected def saveAuthorizedUser(fbUser: FacebookUser): Unit = {
    /*
    val user = User.findById(fbUser.id).getOrElse {
      User.create(fbUser)
    }
    session.setAttribute("currentUser", user)
    */
  }

  override protected def handleWhenLoginFailed(): Any = {
    //flash("warn") = "Login failed. Please try again."
    redirect302("/auth")
  }

  override protected def handleWhenLoginSucceeded(): Any = {
    //flash("info") = "You have successfully registered and logged in."
    redirect302("/")
  }
}
