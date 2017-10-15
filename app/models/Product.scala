package models

import javax.inject.Singleton

case class Product(ean: Long, name: String, description: String)

@Singleton
class InMemoryProductStore extends ProductStore {
  private var products = Set(
    Product(5010255079763L, "Paperclips Large", "Large Plain Pack of 1000"),
    Product(5018206244666L, "Giant Paperclips", "Giant Plain 51mm 100 pack"),
    Product(5018306332812L, "Paperclip Giant Plain", "Giant Plain Pack of 10000"),
    Product(5018306312913L, "No Tear Paper Clip", "No Tear Extra Large Pack of 1000"),
    Product(5018206244611L, "Zebra Paperclips", "Zebra Length 28mm Assorted 150 Pack")
  )

  def findAll: List[Product] = products.toList

  def findByEan(ean: Long): Option[Product] =
    products.find(_.ean == ean)

  def add(product: Product): Unit =
    products = products + product
}
