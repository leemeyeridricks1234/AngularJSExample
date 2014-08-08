package com.christophergagne.sprayapidemo.services

import akka.actor.Actor
import akka.event.Logging

import spray.routing.RequestContext
import com.christophergagne.sprayapidemo._
import spray.httpx.SprayJsonSupport
import com.christophergagne.sprayapidemo.repositories.{InstructionRepository, FundRepository}
import com.christophergagne.sprayapidemo.AccountProduct
import spray.routing.RequestContext
import com.christophergagne.sprayapidemo.AccountFundSummary
import ModelJsonProtocol._
import SprayJsonSupport._
import scala.collection.mutable.ListBuffer


object InstructionService {
  case class GetInstructions(accountNumber: String)
  case class AddInstruction(instruction: Instruction)
}

class InstructionService(requestContext: RequestContext) extends Actor {

  import InstructionService._

  implicit val system = context.system
  val log = Logging(system, getClass)
  val repo = new InstructionRepository()

  val instruction1 = new Instruction("AGUT12345", "Allan Gray Equity Fund", 1010.10, "Initial")
  val instruction2 = new Instruction("AGUT12345", "Allan Gray Balance Fund", 2000.00, "Additional Contribution")

  var instructionList = Seq(instruction1, instruction2)

  def receive = {
    case GetInstructions(accountNumber: String) =>
      getInstructions(accountNumber)
      context.stop(self)

    case AddInstruction(instruction: Instruction) =>
      addInstruction(instruction)
      context.stop(self)
  }

  def getInstructions(accountNumber: String) = {
    log.info("Requesting fund list")
    requestContext.complete(instructionList)
  }

  def addInstruction(instruction: Instruction) = {
    log.info("Updating profile")

    instructionList = instructionList :+ instruction

    requestContext.complete(instruction)
  }
}
