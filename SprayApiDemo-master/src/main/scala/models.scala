package com.christophergagne.sprayapidemo

import spray.json.{JsonFormat, DefaultJsonProtocol}

case class FundInfo(code: String, name: String, percentageMovement: BigDecimal, yearToDate: BigDecimal, link: String, unitPrice: BigDecimal)
case class FundPrices(fund: FundInfo, prices: Seq[DataPoint])
case class DataPoint(date: String, value: BigDecimal)

case class AccountSummary(products: Seq[AccountProduct], investmentsInProgress: BigDecimal, funds: Seq[AccountFundSummary])
case class AccountProduct(accountNumber: String, productName: String, marketValue: BigDecimal)
case class AccountFundSummary(fund: FundInfo, units: BigDecimal, percentageAllocation: BigDecimal, marketValue: BigDecimal)

case class Elevation(location: Location, elevation: Double)
case class Location(lat: Double, lng: Double)
case class GoogleElevationApiResult[T](status: String, results: List[T])

case class User(username: String, personalDetails: PersonalDetails, contactDetails: ContactDetails)
case class PersonalDetails(title: String, firstName: String, lastName: String, dateOfBirth: String, idNumber: String, investorNumber: String)
case class ContactDetails(homePhone: String, workPhone: String, workFax: String, mobilePhone: String, email: String)

case class Instruction(accountNumber: String, fundCode: String, amount: BigDecimal, instructionType: String)


object ModelJsonProtocol extends DefaultJsonProtocol {
  implicit val dataPointFormat = jsonFormat2(DataPoint)
  implicit val fundInfoFormat = jsonFormat6(FundInfo)
  implicit val fundPricesFormat = jsonFormat2(FundPrices)

  implicit val accountFundSummaryFormat = jsonFormat4(AccountFundSummary)
  implicit val accountProductFormat = jsonFormat3(AccountProduct)
  implicit val accountSummaryFormat = jsonFormat3(AccountSummary)

  implicit val locationFormat = jsonFormat2(Location)
  implicit val elevationFormat = jsonFormat2(Elevation)
  implicit def googleElevationApiResultFormat[T :JsonFormat] = jsonFormat2(GoogleElevationApiResult.apply[T])

  implicit val contactFormat = jsonFormat5(ContactDetails)
  implicit val personalFormat = jsonFormat6(PersonalDetails)
  implicit val userFormat = jsonFormat3(User)

  implicit val instructionFormat = jsonFormat4(Instruction)
}