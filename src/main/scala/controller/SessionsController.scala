package controller

import skinny.controller.feature.TwitterLoginFeature

class SessionsController extends ApplicationController with TwitterLoginFeature {
  // these env variables are expected by default
  // SKINNY_OAUTH1_CONSUMER_KEY_TWITTER
  // SKINNY_OAUTH1_CONSUMER_SECRET_TWITTER

  // Twitter API doesn't allow localhost app, edit /etc/hosts
  override def isLocalDebug = true

  override protected def saveAuthorizedUser(twUser: twitter4j.User): Unit = {
  }

  override protected def handleWhenLoginSucceeded(): Any = {

  }
}
