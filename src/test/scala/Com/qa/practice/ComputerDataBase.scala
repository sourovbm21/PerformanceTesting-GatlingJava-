package Com.qa.practice

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ComputerDataBase extends Simulation {

	val httpProtocol = http
		.baseUrl("https://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-GB,en-US;q=0.9,en;q=0.8,pt;q=0.7")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36")

	val headers_0 = Map(
		"sec-ch-ua" -> """Google Chrome";v="113", "Chromium";v="113", "Not-A.Brand";v="24""",
		"sec-ch-ua-mobile" -> "?0",
		"sec-ch-ua-platform" -> "macOS",
		"sec-fetch-dest" -> "document",
		"sec-fetch-mode" -> "navigate",
		"sec-fetch-site" -> "same-origin",
		"sec-fetch-user" -> "?1")

	val headers_2 = Map(
		"origin" -> "https://computer-database.gatling.io",
		"sec-ch-ua" -> """Google Chrome";v="113", "Chromium";v="113", "Not-A.Brand";v="24""",
		"sec-ch-ua-mobile" -> "?0",
		"sec-ch-ua-platform" -> "macOS",
		"sec-fetch-dest" -> "document",
		"sec-fetch-mode" -> "navigate",
		"sec-fetch-site" -> "same-origin",
		"sec-fetch-user" -> "?1")



	val scn = scenario("ComputerDataBase")
		.exec(http("request_0")
			.get("/computers")
			.headers(headers_0))
		.pause(4)
		.exec(http("request_1")
			.get("/computers/new")
			.headers(headers_0))
		.pause(15)
		.exec(http("request_2")
			.post("/computers")
			.headers(headers_2)
			.formParam("name", "TO1")
			.formParam("introduced", "")
			.formParam("discontinued", "")
			.formParam("company", "2"))
		.pause(8)
		.exec(http("request_3")
			.get("/computers?f=TO1")
			.headers(headers_0))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}