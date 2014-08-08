package com.christophergagne.sprayapidemo.repositories

import com.christophergagne.sprayapidemo.Instruction
import scala.collection.mutable.ListBuffer

class InstructionRepository {
  val instructions = new ListBuffer[Instruction]

  val instruction1 = new Instruction("AGUT12345", "Allan Gray Equity Fund", 1010.10, "Initial")
  val instruction2 = new Instruction("AGUT12345", "Allan Gray Balance Fund", 2000.00, "Additional Contribution")
  instructions.append(instruction1)
  instructions.append(instruction2)

  def getInstructions() = {
    instructions.seq
  }

  def add(instruction: Instruction) {
    instructions.append(instruction)
  }
}
