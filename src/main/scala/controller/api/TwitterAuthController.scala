package controller.api

import controller.ApplicationController
import model.User
import scalikejdbc._
import skinny.controller.feature.TwitterLoginFeature

class TwitterAuthController extends ApplicationController with TwitterLoginFeature {
  // these env variables are expected by default
  // SKINNY_OAUTH1_CONSUMER_KEY_TWITTER
  // SKINNY_OAUTH1_CONSUMER_SECRET_TWITTER

  // Twitter API doesn't allow localhost app, edit /etc/hosts
  override def isLocalDebug = true

  override protected def saveAuthorizedUser(twUser: twitter4j.User): Unit = {
    val user = User.findBy(sqls.eq(User.defaultAlias.googleUserId, twUser.getId)).getOrElse {
      val createdId = User.createWithAttributes(
        'name -> "tw",
        'twitter_user_id -> twUser.getId
      )
      User.findById(createdId).get
    }
    session.setAttribute("currentUser", user)
  }

  override protected def handleWhenLoginSucceeded(): Any = {
    redirect302("/")
  }
}
