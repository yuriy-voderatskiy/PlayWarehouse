package controllers

import javax.inject.{Inject, Singleton}

import models.{Product, ProductStore}
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import play.api.mvc._

@Singleton
class ProductsController @Inject()(productStore: ProductStore, cc: ControllerComponents)
    extends AbstractController(cc)
    with I18nSupport {

  val productForm = Form(
    mapping(
      "ean"         -> longNumber.verifying("EAN duplicate", productStore.findByEan(_).isEmpty),
      "name"        -> nonEmptyText,
      "description" -> nonEmptyText
    )(Product.apply)(Product.unapply)
  )

  def list() = Action { implicit request =>
    Ok(views.html.products.list(productStore.findAll))
  }

  def details(ean: Long) = Action { implicit request =>
    productStore
      .findByEan(ean)
      .map { product =>
        Ok(views.html.products.details(product))
      }
      .getOrElse {
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
        Redirect(routes.ProductsController.create()).flashing("error" -> "Creation failed")
      },
      success = { newProduct =>
        productStore.add(newProduct)
        Redirect(routes.ProductsController.details(newProduct.ean)).flashing("success" -> "Product added")
      }
    )
  }
}
