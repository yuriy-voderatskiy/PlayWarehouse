package models

trait ProductStore {

  def findAll: List[Product]

  def findByEan(ean: Long): Option[Product]

  def add(product: Product): Unit
}
