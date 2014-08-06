package com.christophergagne.sprayapidemo

import akka.actor.{Actor, ActorRef}
import akka.event.Logging

import spray.routing.RequestContext
import spray.httpx.SprayJsonSupport
import spray.client.pipelining._

import scala.util.{ Success, Failure }
import com.christophergagne.sprayapidemo.repositories.FundRepository

object FundService {
  case class Process()
}

class FundService(requestContext: RequestContext) extends Actor {

  import FundService._

  implicit val system = context.system
  import system.dispatcher
  val log = Logging(system, getClass)

  def receive = {
    case Process() =>
      process()
      context.stop(self)
  }

  def process() = {

    log.info("Requesting...")

    import ModelJsonProtocol._
    import SprayJsonSupport._

    val repo = new FundRepository
    requestContext.complete(repo.getFunds())
  }
}
