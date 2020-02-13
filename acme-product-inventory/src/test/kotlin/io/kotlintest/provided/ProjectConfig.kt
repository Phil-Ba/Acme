package io.kotlintest.provided.io.kotlintest.provided
import io.kotlintest.AbstractProjectConfig

object ProjectConfsig : AbstractProjectConfig() {

  private var started: Long = 0

  override fun beforeAll() {
    started = System.currentTimeMillis()
  }

  override fun afterAll() {
    val time = System.currentTimeMillis() - started
    println("overall time [ms]: " + time)
  }
}