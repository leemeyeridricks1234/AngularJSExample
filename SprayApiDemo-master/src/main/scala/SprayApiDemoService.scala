package com.christophergagne.sprayapidemo

import akka.actor.{Actor, Props}
import spray.routing._
import com.christophergagne.sprayapidemo.services.AccountService
import ModelJsonProtocol._
import spray.httpx.SprayJsonSupport
import SprayJsonSupport._

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
                val accountService = actorRefFactory.actorOf(Props(new AccountService(requestContext)))
                accountService ! AccountService.GetSummary(accountNumber)
            }
          }
        } ~
        cors {
          path("profile") {
            post {
              entity(as[User]) { user =>
                requestContext =>
                  val accountService = actorRefFactory.actorOf(Props(new AccountService(requestContext)))
                  accountService ! AccountService.UpdateProfile(user)
              }
            }
          }
        }
    }
}
