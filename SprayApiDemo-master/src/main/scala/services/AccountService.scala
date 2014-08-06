package com.christophergagne.sprayapidemo.services

import akka.actor.Actor
import akka.event.Logging

import spray.routing.RequestContext
import com.christophergagne.sprayapidemo._
import spray.httpx.SprayJsonSupport
import com.christophergagne.sprayapidemo.repositories.FundRepository
import com.christophergagne.sprayapidemo.AccountProduct
import spray.routing.RequestContext
import com.christophergagne.sprayapidemo.AccountFundSummary
import ModelJsonProtocol._
import SprayJsonSupport._


object AccountService {
  case class GetSummary(accountNumber: String)
  case class UpdateProfile(user: User)
}

class AccountService(requestContext: RequestContext) extends Actor {

  import AccountService._

  implicit val system = context.system
  val log = Logging(system, getClass)

  def receive = {
    case GetSummary(accountNumber: String) =>
      getSummary(accountNumber)
      context.stop(self)

    case UpdateProfile(user: User) =>
      updateProfile(user)
      context.stop(self)
  }

  def getSummary(accountNumber: String) = {
    log.info("Requesting fund list")

    val funds = new FundRepository().getFunds()
    val fundSummary1 = new AccountFundSummary(funds(0), 2343.1884, 100.0, funds(0).unitPrice * 2343.1884)
    val accountProduct1 = new AccountProduct(accountNumber, "Allan Gray Retirement Annuity Fund", funds(0).unitPrice * 2343.1884)
    val accountSummary1 = new AccountSummary(Seq(accountProduct1), 0.0, Seq(fundSummary1))

    requestContext.complete(accountSummary1)
  }

  def updateProfile(user: User) = {
    log.info("Updating profile")

    requestContext.complete(user)
  }
}
