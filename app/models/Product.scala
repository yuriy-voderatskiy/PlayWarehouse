package models

/**
 * Created by Home on 15.03.2015.
 */

import anorm._
import anorm.SqlParser._

import play.api.db._
import play.api.Play.current

case class Product(ean: Long, name: String, description: String)

object Product {
//  private var products = Set(
//    Product(5010255079763L, "Paperclips Large",
//      "Large Plain Pack of 1000"),
//    Product(5018206244666L, "Giant Paperclips",
//      "Giant Plain 51mm 100 pack"),
//    Product(5018306332812L, "Paperclip Giant Plain",
//      "Giant Plain Pack of 10000"),
//    Product(5018306312913L, "No Tear Paper Clip",
//      "No Tear Extra Large Pack of 1000"),
//    Product(5018206244611L, "Zebra Paperclips",
//      "Zebra Length 28mm Assorted 150 Pack")
//  )
//
//  def findAll = products.toList.sortBy(_.ean)
//
//  def findByEan(ean: Long) = products.find(_.ean == ean)
//
//  def add(product: Product) = {
//    products = products + product
//  }

  val mapping = {
    get[Long]("ean") ~
    get[String]("name") ~
    get[String]("description") map {
      case ean~name~description => Product(ean, name, description)
    }
  }

  def findAll = {
    DB.withConnection { implicit connection =>
      SQL("SELECT ean, name, description FROM product ORDER BY ean").as(Product.mapping *)
    }
  }

  def findByEan(ean: Long): Option[Product] = {
    DB.withConnection { implicit connection =>
      SQL("SELECT ean, name, description from product where ean={ean}").on(
        'ean -> ean
      ).as(Product.mapping *) match {
        case Nil => None
        case head :: tail => Some(head)
      }
    }
  }

  def add(product: Product) = {
    DB.withConnection { implicit connection =>
      SQL("INSERT INTO product(ean, name, description) VALUES({ean}, {name}, {description}) ").on(
        'ean -> product.ean,
        'name -> product.name,
        'description -> product.description
      ).executeUpdate()
    }
  }

}
