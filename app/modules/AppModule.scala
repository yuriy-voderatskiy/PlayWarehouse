package modules

import com.google.inject.AbstractModule
import models.{InMemoryProductStore, ProductStore}


class AppModule extends AbstractModule {
  def configure(): Unit = {
    bind(classOf[ProductStore]).to(classOf[InMemoryProductStore])
  }
}
