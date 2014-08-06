package com.christophergagne.sprayapidemo.repositories

import com.christophergagne.sprayapidemo.FundInfo

class FundRepository {
  def getFunds() = {
    val fund1 = new FundInfo("AGEF", "Allan Gray Equity Fund", -4.21, 4.23, "August 2014", 8744.81)
    val fund2 = new FundInfo("AGBF", "Allan Gray Balance Fund", 6.21, 3.23, "August 2014", 8244.81)
    val fund3 = new FundInfo("AGSF", "Allan Gray Stable Fund", 2.21, 1.23, "August 2014", 4744.81)
    val fund4 = new FundInfo("AGOF", "Allan Gray Optimal Fund", 6.64, 3.23, "August 2014", 3744.81)
    val fund5 = new FundInfo("AGMF", "Allan Gray Money Market Fund", 2.31, 1.53, "August 2014", 1744.81)

    Seq(fund1, fund2, fund3, fund4, fund5)
  }
}
