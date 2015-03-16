import models.Product
import play.api.{Application, GlobalSettings}

import scala.Product

/**
 * Created by Home on 16.03.2015.
 */
object Global extends GlobalSettings {
  override def onStart(app: Application): Unit = {
      Set(
        Product(5010255079763L, "Paperclips Large",
          "Large Plain Pack of 1000"),
        Product(5018206244666L, "Giant Paperclips",
          "Giant Plain 51mm 100 pack"),
        Product(5018306332812L, "Paperclip Giant Plain",
          "Giant Plain Pack of 10000"),
        Product(5018306312913L, "No Tear Paper Clip",
          "No Tear Extra Large Pack of 1000"),
        Product(5018206244611L, "Zebra Paperclips",
          "Zebra Length 28mm Assorted 150 Pack")
      ).map { product =>
        Product.add(product)
      }
  }
}
