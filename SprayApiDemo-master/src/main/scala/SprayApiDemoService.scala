package com.christophergagne.sprayapidemo

import akka.actor.{Actor, Props}
import spray.routing._
import com.christophergagne.sprayapidemo.services.AccountSummaryService

class SprayApiDemoServiceActor extends Actor with SprayApiDemoService {
  
  def actorRefFactory = context

  def receive = runRoute(sprayApiDemoRoute)
}

trait SprayApiDemoService extends HttpService with CorsSupport {
  val sprayApiDemoRoute =
    pathPrefix("api") {
      path("ElevationService" / DoubleNumber / DoubleNumber) { (long, lat) =>
        requestContext =>
          val elevationService = actorRefFactory.actorOf(Props(new ElevationService(requestContext)))
          elevationService ! ElevationService.Process(long, lat)
      } ~
        cors {
          path("funds") {
            get {
              requestContext =>
                val fundService = actorRefFactory.actorOf(Props(new FundService(requestContext)))
                fundService ! FundService.Process()
            }
          }
        } ~
        cors {
          path("account" / Segment) { accountNumber =>
            get {
              requestContext =>
                val accountService = actorRefFactory.actorOf(Props(new AccountSummaryService(requestContext)))
                accountService ! AccountSummaryService.Process(accountNumber)
            }
          }
        }
    }
}
