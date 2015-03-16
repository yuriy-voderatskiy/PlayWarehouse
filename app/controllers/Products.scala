package controllers

import models.Product
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

object Products extends Controller {
  val productForm = Form(
    mapping (
      "ean" -> longNumber.verifying("EAN duplicate", Product.findByEan(_).isEmpty),
      "name" -> nonEmptyText,
      "description" -> nonEmptyText
    )(Product.apply)(Product.unapply)
  )

  def list() = Action { implicit request =>
    Ok(views.html.products.list(Product.findAll))
  }

  def details(ean: Long) = Action { implicit request =>
    Product.findByEan(ean).map {
      product =>
        Ok(views.html.products.details(product))
    }.getOrElse{
      NotFound(s"Product $ean not found")
    }
  }

  def create() = Action { implicit request =>
    Ok(views.html.products.createForm(productForm))
  }

  def save() = Action { implicit request =>
    val form = productForm.bindFromRequest()
    form.fold(
      hasErrors = { form =>
        Redirect(routes.Products.create()).flashing("error" -> "Creation failed")
      },

      success = { newProduct =>
        Product.add(newProduct)
        Redirect(routes.Products.details(newProduct.ean)).flashing("success" -> "Product added")
      }
    )
  }
}