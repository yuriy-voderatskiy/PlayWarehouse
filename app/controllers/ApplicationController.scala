package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc._

@Singleton
class ApplicationController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action { implicit request =>
    Redirect(routes.ProductsController.list())
  }

}
