package assignment3

import org.jsoup.Jsoup
import org.jsoup.nodes.{Document, Element}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}

import java.time.LocalDateTime
import scala.util.Random

/**
 * Created by Tan Dang on 14/03/2024
 * This program uses Jsoup and Selenium to scrape data from URLs, so please make sure you have installed them before running it.
 */

val options = ChromeOptions().addArguments("--headless")
val driver = ChromeDriver(options)
val originUrl = "https://baomoi.com"

@main
def main(): Unit = {
  try {
    val doc = Jsoup.connect("https://www.sggp.org.vn/vu-ngo-doc-nghi-do-an-com-ga-tai-nha-trang-so-ca-ngo-doc-tiep-tuc-tang-post731058.html").get()
    println(doc.body().wholeText())

//        println(createArticle("https://baomoi.com/thu-pham-quen-thuoc-gay-vu-ngo-doc-com-ga-o-nha-trang-r48580065.epi"))
//    println(createArticle("https://baomoi.com/vu-ngo-doc-nghi-do-an-com-ga-tai-nha-trang-so-ca-ngo-doc-tiep-tuc-tang-c48579935.epi"))
    //    var htmlContent = getHTMLContent(originUrl)

    //    val doc: Document = Jsoup.parse(htmlContent) // user Jsoup to parse HTML content
    //    val mainSections = doc.getElementsByClass("menu-list").first() // get 15 main sections

    //    val n = 11
    //    val section = mainSections.child(n) // get the nth section

    //    val linkSection = getLinkSection(section.getElementsByTag("a").first()) // get the link of the section
    //    println(linkSection)

    //    driver.get(linkSection)
    //    val jsExecutor = driver.asInstanceOf[org.openqa.selenium.JavascriptExecutor]
    //    jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)") // scroll to the bottom of the page to load more articles
    //    Thread.sleep(2000) // wait for 2 seconds to load more articles
    //    htmlContent = driver.getPageSource // get the HTML content after loading more articles from the page

    //    val content = Jsoup.parse(htmlContent)
    //    var count = 0;
    //    driver.get(getLinkSection(content.getElementsByClass("bm-card-header").first().getElementsByTag("a").first()))
    //    val linkOrigin = driver.findElement(By.linkText("Gốc"))
    //    linkOrigin.click()
    //    println(driver.getCurrentUrl)
    //    println(driver.getPageSource)
    //    println(createArticle(getLinkSection(content.getElementsByClass("bm-card-header").first().getElementsByTag("a").first())).originLink)
    //    content.getElementsByClass("bm-card-header").forEach(card => {
    //      val title = card.getElementsByTag("a").first().text()
    //      val link = getLinkSection(card.getElementsByTag("a").first())
    //      println(s"Title: $title")
    //      println(s"Link: $link")
    //      count += 1
    //      println(count)
    //    })
  } catch {
    case e: Exception => println("Error: " + e.getMessage)
  } finally {
    driver.quit()
  }
}

def getHTMLContent(url: String): String = {
  driver.get(url)
  driver.getPageSource
}

def getRandomNumber(max: Int): Int = {
  Random.nextInt(max + 1)
}

// Get the link from href attribute
def getLinkSection(section: Element): String = {
  originUrl + section.attribute("href").getValue
}

def createArticle(link: String): Article = {
  driver.get(link)
  try {
    val linkOrigin = driver.findElement(By.linkText("Gốc"))
    linkOrigin.click()
    Thread.sleep(2000)
  } catch {
    case _: Exception => println("No original link")
  }
  Article(driver.getCurrentUrl, driver.getPageSource, LocalDateTime.now().toString)
}

case class Article(originalLink: String, content: String, dateTime: String)