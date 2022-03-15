package com.cub2.org;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {

	public static WebDriver driver;
	static Alert alert;
	static Select s;
	static Robot r;
	static Select dropDown;
	static Scanner sc;
	static List<String> list;

	// To Launch Browser
	public static void browser(String browser) {
		try {
			switch (browser) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				System.err.println("check a driver");
				break;
			}
		} catch (SessionNotCreatedException e) {
			System.out.println("Check the driver verions:" + e.getMessage());
		} catch (WebDriverException e) {
			System.out.println("selenium exceptions:" + e.getMessage());
		}
		driver.manage().window().maximize();
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} catch (TimeoutException e) {
			System.out.println("Time is over element not to be found:" + e.getMessage());
		}
	}

	// To Launch URL
	public static void url(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			System.out.println("Check the URL");
			System.out.println(e.getMessage());
		}
	}

	// To Navigate
	public static void Navigate(String movement) {
		try {
			switch (movement) {
			case "r":
				driver.navigate().refresh();
				break;
			case "b":
				driver.navigate().back();
				break;
			case "f":
				driver.navigate().forward();
				break;
			default:
				System.out.println("Check a navigation");
				break;
			}
		} catch (WebDriverException e) {
			System.out.println("Check a navigation command:" + e.getMessage());
		}
	}

	// To find element
	public static WebElement findelement(String locator, String value) {
		try {
			switch (locator) {
			case "id":
				return driver.findElement(By.id(value));
			case "name":
				return driver.findElement(By.name(value));
			case "classname":
				return driver.findElement(By.className(value));
			case "xpath":
				return driver.findElement(By.xpath(value));
			case "tagname":
				return driver.findElement(By.tagName(value));
			case "link":
				return driver.findElement(By.linkText(value));
			case "partical":
				return driver.findElement(By.partialLinkText(value));
			default:
				System.out.println("To check a locator(findelement)");
				break;
			}
		} catch (WebDriverException e) {
			System.out.println("Check a findelement method:" + e.getMessage());
		}
		return null;
	}

	// To find elements
	public static List<WebElement> elements(String locator, String value) {
		try {
			switch (locator) {
			case "id":
				return driver.findElements(By.id(value));
			case "name":
				return driver.findElements(By.name(value));
			case "classname":
				return driver.findElements(By.className(value));
			case "xpath":
				return driver.findElements(By.xpath(value));
			case "tagname":
				return driver.findElements(By.tagName(value));
			case "link":
				return driver.findElements(By.linkText(value));
			case "partical":
				return driver.findElements(By.partialLinkText(value));

			default:
				System.out.println("To check a locator(findelements)");
				break;
			}
		} catch (WebDriverException e) {
			System.out.println("Check a findelements method:" + e.getMessage());
		}
		return null;
	}

	// To get driver method
	public static String drivermethod(String get) {
		try {
			switch (get) {
			case "t":
				return driver.getTitle();
			case "cu":
				return driver.getCurrentUrl();
			case "ps":
				return driver.getPageSource();
			case "wh":
				return driver.getWindowHandle();
			default:
				System.out.println("Check a driver method");
				break;
			}
		} catch (WebDriverException e) {
			System.out.println("check the drivermethod:" + e.getMessage());
		}
		return get;
	}

	// To get WindowHandles
	public static Set<String> windowhandles() {
		try {
			driver.getWindowHandles();
		} catch (WebDriverException e) {
			System.out.println("Check a windowhandles method:" + e.getMessage());
		}
		return null;
	}

	// To Window handling
	public static void windowhandlings(Set<String> windowhandles, int index) {
		try {
			List<String> list = new ArrayList<String>(windowhandles);
			driver.switchTo().window(list.get(index));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Check the index value:" + e.getMessage());
		} catch (NoSuchElementException e) {
			System.out.println("Check the if window is close or not:" + e.getMessage());
		}
	}

	// To Windows
	public static void windows(Set<String> windowhandles) {
		for (String string : windowhandles()) {
			String title = driver.switchTo().window(string).getTitle();
			System.out.println(title);
		}
	}

	// To windows1
	public static void windows1(Set<String> windowhandles, String Strings) {
		String s = Strings;
		for (String string : windowhandles()) {
			if (driver.switchTo().window(string).getTitle().equals(s)) {
			}
			break;
		}

	}

	// To boolean
	public static boolean booleans(String booleans, WebElement element) {
		switch (booleans) {
		case "isdisplayed":
			return element.isDisplayed();
		case "isselected":
			return element.isSelected();
		case "isenabled":
			return element.isEnabled();
		default:
			System.out.println("Check boolean condition");
			break;
		}
		return false;
	}

	// To alert
	public static void alertbox(String alerts, String alerttext) {
		try {
			switch (alerts) {
			case "simple":
				alert = driver.switchTo().alert();
				alert.accept();
				break;
			case "confirm":
				alert = driver.switchTo().alert();
				alert.accept();
				break;
			case "confirmdismiss":
				alert = driver.switchTo().alert();
				alert.dismiss();
				break;
			case "prompt":
				alert = driver.switchTo().alert();
				alert.accept();
				alert.sendKeys(alerttext);
				break;
			case "promptdismiss":
				alert = driver.switchTo().alert();
				alert.dismiss();
			default:
				System.out.println("To check alerbox");
				break;
			}
		} catch (UnhandledAlertException e) {
			System.out.println("Handle the alert first:" + e.getMessage());
		} catch (NoAlertPresentException e) {
			System.out.println("Check the alert is there or NOT there:" + e.getMessage());
		} catch (WebDriverException e) {
			System.out.println("Check a alert box:" + e.getMessage());
		}
	}

	// To Frame
	public static void frame(String frames, int index, String string, WebElement element) {
		try {
			switch (frames) {
			case "index":
				driver.switchTo().frame(index);
				break;
			case "string":
				driver.switchTo().frame(string);
				break;
			case "element":
				driver.switchTo().frame(element);
				break;
			default:
				System.out.println("To check frame");
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Check a element iniside a frame:" + e.getMessage());
		} catch (NoSuchFrameException e) {
			System.out.println("check the frame condition:" + e.getMessage());
		}
	}

	// To Out of Frame
	public static void outof(String frames) {
		switch (frames) {
		case "default":
			driver.switchTo().defaultContent();
			break;
		case "parent":
			driver.switchTo().parentFrame();
			break;
		default:
			System.out.println("To check frame syntax");
			break;
		}
	}

	// To Drop down
	public static void dd(String drop, WebElement element, int index, String value, String text) {
		try {
			s = new Select(element);
			switch (drop) {
			case "byindex":
				s.selectByIndex(index);
				break;
			case "byvalue":
				s.selectByValue(value);
				break;
			case "byvisible":
				s.selectByVisibleText(text);
				break;
			default:
				System.out.println("To check a dropdown");
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Check the element value:" + e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Check the index value:" + e.getMessage());
		}
	}

	// To Options
	public static void ddoption(String functions) {
		switch (functions) {
		case "firstselect":
			WebElement firstSelectedOption = s.getFirstSelectedOption();
			System.out.println("First select dropdown text :" + firstSelectedOption.getText());
			break;
		case "allselect":
			List<WebElement> allSelectedOptions = s.getAllSelectedOptions();
			for (WebElement webElement : allSelectedOptions) {
				System.out.println("Allselect dropdown text :" + webElement.getText());
				System.out.println("size of allselect dropdown :" + allSelectedOptions.size());
			}
			break;
		case "getoption":
			List<WebElement> options = s.getOptions();
			for (WebElement web : options) {
				System.out.println(web.getText());
			}
			int size = options.size();
			System.out.println("size of option dropdown:" + size);
			break;
		default:
			System.out.println("Undefined Function in the Select Class Please To Check it");
			break;
		}
	}

	// To Multiple Drop down
	public static void multidd(String selectordeselect, WebElement element) {
		switch (selectordeselect) {
		case "selecting":
			s = new Select(element);
			boolean multiple = s.isMultiple();
			System.out.println("If multiple option is there :" + multiple);
			System.out.println("Size of multiple option :" + s.getOptions().size());
			driver.manage().window().minimize();
			for (int i = 0; i < s.getOptions().size(); i++) {
				sc = new Scanner(System.in);
				System.err.println("Enter the Index Value Please");
				int indexValue = sc.nextInt();
				if (i == indexValue) {
					s.selectByIndex(i);
				}
			}
			driver.manage().window().maximize();
		default:
			break;
		}
	}

	// To Screenshot
	public static void screenshot(String location) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sa = ts.getScreenshotAs(OutputType.FILE);
		File f = new File(location);
		try {
			FileUtils.copyFile(sa, f);
		} catch (IOException e) {
			System.out.println("To check a file path:" + e.getMessage());
		}
	}

	// To Robotic
	public static void robo(int times) {
		try {
			r = new Robot();
		} catch (AWTException e) {
			System.out.println("To check a robo:" + e.getMessage());
		}
		for (int i = 0; i <= times; i++) {
			r.keyPress(KeyEvent.VK_DOWN);

			r.keyPress(KeyEvent.VK_ENTER);
		}
	}

	// To Actions
	public static void actions(String options, WebElement element) {
		try {
			Actions a = new Actions(driver);
			switch (options) {
			case "mouse":
				a.moveToElement(element).build().perform();
				break;
			case "rightclick":
				a.contextClick(element).build().perform();
				break;
			case "doubleclick":
				a.doubleClick(element).build().perform();
				break;
			case "clickhold":
				a.clickAndHold(element).build().perform();
				break;
			case "release":
				a.release(element).build().perform();
				break;
			default:
				System.out.println("To check a mouseover operation");
				break;
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element is not there:" + e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("driver is not to call:" + e.getMessage());
		}
	}

	// To Scroll
	public static void scroll(String scrolls, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			switch (scrolls) {
			case "elementscroll":
				js.executeScript("agruments[0].scrollIntoview();", element);
				break;
			case "top":
				js.executeScript("window.scrollTo(0,document.body.scrollheight)");
				break;
			case "bottom":
				js.executeScript("window.scrollTo(0,0)");
				break;
			case "click":
				js.executeScript("agruments[0].click()", element);
				break;
			default:
				System.out.println("To check a scroll");
				break;
			}
		} catch (JavascriptException e) {
			System.out.println("Check the script:" + e.getMessage());
		}
	}

	// To waits
	public static void waits(String option, WebElement element) {
		try {
			WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
			switch (option) {
			case "elementtoclick":
				waits.until(ExpectedConditions.elementToBeClickable(element));
				break;
			case "elementtoselect":
				waits.until(ExpectedConditions.elementToBeSelected(element));
			case "elementtovisible":
				waits.until(ExpectedConditions.visibilityOf(element));
			default:
				System.out.println("To check a waits");
				break;
			}
		} catch (TimeoutException e) {
			System.out.println("Check await condition:" + e.getMessage());
		}
	}

	// To Get text
	public static String gettext(WebElement element) {
		return element.getText();
	}

	// To click
	public static void click(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			System.out.println("Element is not there :" + e.getMessage());
		}
	}

	// To clear
	public static void clear(WebElement element) {
		try {
			element.clear();
		} catch (Exception e) {
			System.out.println("Element is not there :" + e.getMessage());
		}
	}

	// To Send keys
	public static void sendkeys(WebElement element, String string) {
		try {
			element.sendKeys(string);
		} catch (Exception e) {
			System.out.println("Element is not there:" + e.getMessage());
		}
	}

	// To submit
	public static void submit(WebElement element) {
		try {
			element.submit();
		} catch (Exception e) {
			System.out.println("Element is not there:" + e.getMessage());
		}
	}

	// To close
	public static void close() {
		driver.close();
	}

	// To quit
	public static void quit() {
		driver.quit();
	}
}