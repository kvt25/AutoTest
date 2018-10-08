package com.autotest.framework.library;

import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autotest.configure.Environment;
import com.autotest.configure.AutoTestConstants;
import com.opera.core.systems.OperaDriver;
import com.autotest.utils.StackTraceInfo;


@SuppressWarnings("javadoc")
public class CommonFunctions extends Environment
{

	public BrowserControling browserControling = new BrowserControling();
	public WaitControling waitControling = new WaitControling();
	public ElementEventControling elementEventControling = new ElementEventControling();
	public ElementChecking elementChecking = new ElementChecking();
	public KeyEventControling keyEventControling = new KeyEventControling();

	// for searching all child elements of webelement
	public static List<WebElement> returnElementList = new ArrayList<WebElement>();
	public static List<String> returnXpathList = new ArrayList<String>();

	public static WebDriver webdriver;
	public static WebDriverWait webdriverWait;

	public static String lnkIESecurityCerfID = "overridelink";

	public WebDriver getWebDriver()
	{
		return webdriver;
	}

	public WebDriverWait getWebDriverWait()
	{
		return webdriverWait;
	}


	/**
	 * @ClassName: BrowserControling
	 * @Description: This class contains all functions for browser controling
	 * @author DatNguyen
	 * @CreatedDate: 07/04/2014
	 */
	public static class BrowserControling
	{

		public void startBrowser(final String browser) throws Exception
		{
			try
			{
				FirefoxProfile profile = null;

				if (browser.equals("FirefoxDefault"))
				{
					profile = new FirefoxProfile();
					profile.setEnableNativeEvents(false);
					profile.setPreference("plugin.state.flash", 0);
					webdriver = new FirefoxDriver(profile);
				}
				else if (browser.equals("Chrome"))
				{
					System.setProperty("webdriver.chrome.driver", AutoTestConstants.CHROME_DRIVER);
					webdriver = new ChromeDriver();
				}
				else if (browser.equals("InternetExplorer"))
				{
					System.setProperty("webdriver.ie.driver", AutoTestConstants.IS_DIVER);
					final DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
					capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					capability.setJavascriptEnabled(true);
					webdriver = new InternetExplorerDriver(capability);

				}
				else if (browser.equals("Safari"))
				{
					System.setProperty("webdriver.safari.driver", AutoTestConstants.SAFARI_DRIVER);
					webdriver = new SafariDriver();
				}
				else if (browser.equals("Opera"))
				{
					final DesiredCapabilities operaCapabilities = DesiredCapabilities.opera();
					operaCapabilities.setCapability(CapabilityType.BROWSER_NAME, "opera");
					operaCapabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
					operaCapabilities.setCapability(CapabilityType.VERSION, "12.16");
					operaCapabilities.setCapability("opera.binary", "C:\\Users\\admin\\Downloads\\Compressed\\operadriver_win64\\operadriver.exe");
					operaCapabilities.setCapability("opera.port", 9515);
					webdriver = new OperaDriver(operaCapabilities);
				}
				webdriverWait = new WebDriverWait(webdriver, 20);
				browserMaximize(browser);
				driver.waitControling.implicitlyWaitBySecond(20);
				verifyFunc.isTrue(getBrowserNumber() == 1, MessageFormat.format("Started browser \"{0}\"", browser));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void startBrowser(final String hub, final String browser) throws Exception
		{
			try
			{
				DesiredCapabilities capability = null;
				final FirefoxProfile profile = new FirefoxProfile();
				profile.setEnableNativeEvents(false);
				final URL url = new URL(AutoTestConstants.HUBADDRESS);

				if (browser.startsWith("FirefoxDefault"))
				{
					capability = DesiredCapabilities.firefox();
					//capability.setPlatform(Platform.WINDOWS);
					//capability.setVersion("28.0");
					webdriver = new RemoteWebDriver(url, capability);
				}
				else if (browser.startsWith("Chrome"))
				{
					capability = DesiredCapabilities.chrome();
					capability.setPlatform(Platform.WINDOWS);
					capability.setVersion("45");
					webdriver = new RemoteWebDriver(url, capability);
				}
				else if (browser.startsWith("InternetExplorer"))
				{
					capability = DesiredCapabilities.internetExplorer();
					capability.setPlatform(Platform.WINDOWS);
					capability.setVersion("9");
					capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					webdriver = new RemoteWebDriver(url, capability);
				}
				webdriverWait = new WebDriverWait(webdriver, 15);
				browserMaximize(browser);
				driver.waitControling.implicitlyWaitBySecond(10);
				verifyFunc.isTrue(getBrowserNumber() == 1, MessageFormat.format("Could not start browser \"{0}\"", browser));
				log.info(MessageFormat.format("Started browser \"{0}\"", browser));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void browserMaximize(final String browser) throws Exception
		{
			try
			{
				if (!browser.equals("Opera"))
				{
					webdriver.manage().window().maximize();
				}
				else
				{
					jqueryFunc.browserMaximize();
				}
				log.info("Browser is maximized");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void refreshPage() throws Exception
		{
			try
			{
				webdriver.navigate().refresh();
				log.info("Page is refreshed");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public int getBrowserNumber() throws Exception
		{

			try
			{
				int browserNo = 0;
				browserNo = webdriver.getWindowHandles().size();
				if (browserNo > 0)
				{
					log.info(MessageFormat.format("Browser number is \"{0}\"", browserNo));
				}
				else
				{
					log.error("Have no browser");
				}
				return browserNo;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void browserQuit() throws Exception
		{
			try
			{
				webdriver.quit();
				log.info("Closed browser!!!");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public Dimension getBrowserSize() throws Exception
		{
			try
			{
				return webdriver.manage().window().getSize();
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public boolean acceptIESecurityCerf() throws Exception
		{
			boolean isAccepted = false;
			final String expCertificateWindow = "Certificate Error";
			try
			{
				WebElement lnkIESecurityCerf = null;
				if (AutoTestConstants.curBrowser.equals("InternetExplorer"))
				{
					// accept link via Jquery
					lnkIESecurityCerf = jqueryFunc.getElementByID(lnkIESecurityCerfID);
					// accept by JQuery
					if (lnkIESecurityCerf != null)
					{
						webdriver.get("javascript:document.getElementById('overridelink').click();");
						driver.waitControling.waitPageLoadTimeOutBySecond(15);
						// verify
						final String currWindowTile = driver.browserControling.getBrowserTitle();
						if (!currWindowTile.contains(expCertificateWindow))
						{
							isAccepted = true;
						}
					}
					// accept link via image
					if (!isAccepted && sikuliFunc.vrfImagePresent(AutoTestConstants.IESecurity))
					{
						sikuliFunc.click(AutoTestConstants.IESecurity);
						driver.waitControling.waitPageLoadTimeOutBySecond(15);
						// verify
						final String currWindowTile = driver.browserControling.getBrowserTitle();
						if (currWindowTile.contains(expCertificateWindow))
						{
							isAccepted = true;
						}
					}
					if (isAccepted)
					{
						log.info("Accepted IE Security Certification successful");
					}
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return false;
			}
			return isAccepted;
		}


		public void openURL(final String url) throws Exception
		{
			try
			{
				webdriver.get(url);

				if (AutoTestConstants.curBrowser.equals("InternetExplorer"))
				{
					this.acceptIESecurityCerf();
				}
				log.info(MessageFormat.format("Opened URL \"{0}\"", url));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void goBack() throws Exception
		{
			try
			{
				webdriver.navigate().back();
				log.info("Go back to previous page successfully");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void goForward() throws Exception
		{
			try
			{
				webdriver.navigate().forward();
				log.info("Go to next page in browser successfully");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getBrowserTitle() throws Exception
		{

			try
			{
				String titleBrowser = "";
				titleBrowser = webdriver.getTitle();
				if (titleBrowser.equals("") || titleBrowser.equals(" "))
				{
					log.error("Could not get browser title.");
				}
				else
				{
					log.info("Browser Title is: " + titleBrowser);
				}
				return titleBrowser;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void browserClose() throws Exception
		{

			try
			{
				final String title = getBrowserTitle();
				webdriver.close();
				log.info("Closed browser \"" + title + "\" successfully");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void selectBrowser(final String id) throws Exception
		{

			try
			{
				webdriver.switchTo().window(id);
				log.info("Selected browser with ID " + id + " successfully");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getBrowserURL() throws Exception
		{

			try
			{
				String url = "";
				url = webdriver.getCurrentUrl();
				log.info(MessageFormat.format("URL is \"{0}\"", url));
				return url;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getBrowserID() throws Exception
		{

			try
			{
				String browserID = "";
				browserID = webdriver.getWindowHandle();
				if (browserID == "")
				{
					log.error("Browser ID is null");
					throw new Exception();
				}
				else
				{
					log.info(MessageFormat.format("Browser ID is \"{0}\"", browserID));
					return browserID;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public final Object[] getBrowserIDs() throws Exception
		{
			try
			{
				Object[] objIDs = null;
				objIDs = webdriver.getWindowHandles().toArray();
				if (objIDs == null)
				{
					log.error("Could not get browser IDs. Browser IDs is null");
					throw new Exception();
				}
				else
				{
					log.info("Get Browser IDs is executed.");
					return objIDs;
				}

			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void selectNewBrowser(final String currentBrowserID) throws Exception
		{

			try
			{
				final Object[] allBrowserID = getBrowserIDs();
				if (allBrowserID.length == 1)
				{
					log.error("Do not have any new browser for selecting");
					throw new Exception();
				}
				else
				{
					for (int i = 0; i < allBrowserID.length; i++)
					{
						if (!currentBrowserID.equals(allBrowserID[i].toString()))
						{
							selectBrowser(allBrowserID[i].toString());
							log.info("Select new " + allBrowserID[i].toString() + " is executed");
							break;
						}
					}
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void closeBrowser(final String BrowserID) throws Exception
		{

			try
			{
				final Object[] ids = getBrowserIDs();
				if (ids.length == 1)
				{
					log.error("Do not have any new browser which is opened");
					throw new Exception();
				}
				else
				{
					for (int i = 0; i < ids.length; i++)
					{
						if (BrowserID.equals(ids[i].toString()))
						{
							browserClose();
							break;
						}
					}
					log.info("Close BROWSER which is opened is executed");
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void closeNewBrowser(final String currentBrowserID) throws Exception
		{

			try
			{
				final Object[] ids = getBrowserIDs();
				if (ids.length == 1)
				{
					log.error("Do not have any new browser which is opened");
					throw new Exception();
				}
				else
				{
					for (int i = 0; i < ids.length; i++)
					{
						if (!currentBrowserID.equals(ids[i].toString()))
						{
							browserClose();
							break;
						}
					}
					selectBrowser(currentBrowserID);
					log.info("Close BROWSER which is opened is executed");
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void selectNewBlankWindow() throws Exception
		{

			try
			{
				final Set<String> handles = webdriver.getWindowHandles();

				//Switch to new window opened
				for (final String winHandle : webdriver.getWindowHandles())
				{
					driver.browserControling.selectBrowser(winHandle);
					final String windowTitle = driver.browserControling.getBrowserTitle();
					if (windowTitle.equals(""))
					{
						webdriver.switchTo().window(winHandle);
					}
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void deleteCookies() throws Exception
		{

			try
			{
				webdriver.manage().deleteAllCookies();
				log.info("Deleted all cookies on browser");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void ignoreCertificateErrorOnIE() throws Exception
		{

			try
			{
				driver.waitControling.sleep(10000);
				webdriver.navigate().to("javascript:document.getElementById('overridelink').click()");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}
	}


	/**
	 * @ClassName: WaitControling
	 * @Description: This class contains all functions for waiting action
	 * @author DatNguyen
	 * @CreatedDate: 07/04/2014
	 */
	public static class WaitControling
	{

		public void implicitlyWaitBySecond(final long sec) throws Exception
		{
			try
			{
				webdriver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void implicitlyWaitByMiliSecond(final long miliSec) throws Exception
		{
			try
			{
				webdriver.manage().timeouts().implicitlyWait(miliSec, TimeUnit.MILLISECONDS);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void implicitlyWaitByMinute(final long minute) throws Exception
		{
			try
			{
				webdriver.manage().timeouts().implicitlyWait(minute, TimeUnit.MINUTES);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void waitPageLoadTimeOutBySecond(final long sec) throws Exception
		{
			try
			{
				webdriver.manage().timeouts().pageLoadTimeout(sec, TimeUnit.SECONDS);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void waitPageLoadTimeOutByMinute(final long minute) throws Exception
		{
			try
			{
				webdriver.manage().timeouts().pageLoadTimeout(minute, TimeUnit.MINUTES);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void waitPageLoadTimeOutByHour(final long hour) throws Exception
		{
			try
			{
				webdriver.manage().timeouts().pageLoadTimeout(hour, TimeUnit.HOURS);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void sleep(final long time) throws Exception
		{

			try
			{
				Thread.sleep(time);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		// This function for resolving issue: Element not found in the cache
		public boolean webDriverWaitElementLocated(final String xpath, final int secs) throws Exception
		{

			try
			{
				int i;
				boolean condition = false;
				for (i = 0; i <= secs; i++)
				{
					webdriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
					condition = driver.elementChecking.isElementDisplay(xpath, 100);
					if (condition)
					{
						log.info(MessageFormat.format("Object with XPATH= \"{0}\" is presented on browser", xpath));
						break;
					}
					else
					{
						driver.waitControling.sleep(1000);
					}
				}

				if (!condition && i == secs)
				{
					log.error(MessageFormat.format("Object with XPATH= \"{0}\" is not presented on browser", xpath));
					return false;
				}
				else
				{
					return true;
				}

			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		public void webDriverWaitElementClickable(final WebElement element, final int secs) throws Exception
		{

			try
			{
				webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}
	}


	/**
	 * @ClassName: ElementEventControling
	 * @Description: This class contains all functions for element event controling
	 * @author DatNguyen
	 * @CreatedDate: 07/04/2014
	 */
	public static class ElementEventControling
	{
		
		public WebElement getWebElementHidden(final String locator) throws Exception
		{

			WebElement returnObj = null;
			try
			{
				String xpath = "";
				if (locator.contains("Xpath=") || locator.startsWith("//") || locator.startsWith("("))
				{
					xpath = locator.replace("Xpath=", "");
					returnObj = webdriver.findElement(By.xpath(xpath));
					// ELEMENT NOT FOUND IN THE CACHE
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
						returnObj = webdriver.findElement(By.xpath(xpath));
					}
				}
				//else if ((!locator.contains("//") && !locator.contains("@")) || locator.contains(">"))
				else if ((!locator.contains("//") && !locator.contains("@")) && locator.contains(">"))
				{
					returnObj = webdriver.findElement(By.cssSelector(locator));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
						returnObj = webdriver.findElement(By.cssSelector(locator));
					}
				}
				else if (locator.contains("id="))
				{
					xpath = locator.replace("id=", "");
					returnObj = webdriver.findElement(By.id(xpath));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id(xpath)));
						returnObj = webdriver.findElement(By.id(xpath));
					}
				}
				else if (locator.contains("class="))
				{
					xpath = locator.replace("class=", "");
					returnObj = webdriver.findElement(By.className(xpath));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.className(xpath)));
						returnObj = webdriver.findElement(By.className(xpath));
					}
				}
				else if (locator.contains("name="))
				{
					xpath = locator.replace("name=", "");
					returnObj = webdriver.findElement(By.name(xpath));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.name(xpath)));
						returnObj = webdriver.findElement(By.name(xpath));
					}
				}
				else
				{
					log.info(MessageFormat.format("Wrong / have not support XPATH/CSS= \"{0}\"", locator));
					return returnObj;
				}

				if (!returnObj.isDisplayed())
				{
					log.info("Object is displayed with XPATH/CSS=: " + locator);
					return returnObj;
				}
				else
				{
					log.info(MessageFormat.format("XPATH/CSS= \"{0}\" is existed but did not visible in browser", locator));
					return null;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return returnObj;
			}
		}

		/////======================================================
		public WebElement getWebElementDisplay(final String locator) throws Exception
		{

			WebElement returnObj = null;
			try
			{
				String xpath = "";
				if (locator.contains("Xpath=") || locator.startsWith("//") || locator.startsWith("("))
				{
					xpath = locator.replace("Xpath=", "");
					returnObj = webdriver.findElement(By.xpath(xpath));
					// ELEMENT NOT FOUND IN THE CACHE
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
						returnObj = webdriver.findElement(By.xpath(xpath));
					}
				}
				//else if ((!locator.contains("//") && !locator.contains("@")) || locator.contains(">"))
				else if ((!locator.contains("//") && !locator.contains("@")) && locator.contains(">"))
				{
					returnObj = webdriver.findElement(By.cssSelector(locator));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
						returnObj = webdriver.findElement(By.cssSelector(locator));
					}
				}
				else if (locator.contains("id="))
				{
					xpath = locator.replace("id=", "");
					returnObj = webdriver.findElement(By.id(xpath));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id(xpath)));
						returnObj = webdriver.findElement(By.id(xpath));
					}
				}
				else if (locator.contains("class="))
				{
					xpath = locator.replace("class=", "");
					returnObj = webdriver.findElement(By.className(xpath));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.className(xpath)));
						returnObj = webdriver.findElement(By.className(xpath));
					}
				}
				else if (locator.contains("name="))
				{
					xpath = locator.replace("name=", "");
					returnObj = webdriver.findElement(By.name(xpath));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.name(xpath)));
						returnObj = webdriver.findElement(By.name(xpath));
					}
				}
				else
				{
					log.info(MessageFormat.format("Wrong / have not support XPATH/CSS= \"{0}\"", locator));
					return returnObj;
				}

				if (returnObj.isDisplayed())
				{
					log.info("Object is displayed with XPATH/CSS=: " + locator);
					return returnObj;
				}
				else
				{
					log.info(MessageFormat.format("XPATH/CSS= \"{0}\" is existed but did not visible in browser", locator));
					return null;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return returnObj;
			}
		}

		public WebElement getWebElementDisplay(final String locator, final boolean isValidate) throws Exception
		{

			WebElement returnObj = null;
			try
			{
				String xpath = "";
				if (locator.contains("Xpath=") || locator.startsWith("//") || locator.startsWith("("))
				{
					xpath = locator.replace("Xpath=", "");
					returnObj = webdriver.findElement(By.xpath(xpath));
					// ELEMENT NOT FOUND IN THE CACHE
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
						returnObj = webdriver.findElement(By.xpath(xpath));
					}
				}
				else if ((!locator.contains("//") && !locator.contains("@")) || locator.contains(">"))
				{
					returnObj = webdriver.findElement(By.cssSelector(locator));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
						returnObj = webdriver.findElement(By.cssSelector(locator));
					}
				}
				else if (locator.contains("id="))
				{
					xpath = locator.replace("id=", "");
					returnObj = webdriver.findElement(By.id(xpath));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.id(xpath)));
						returnObj = webdriver.findElement(By.id(xpath));
					}
				}
				else if (locator.contains("class="))
				{
					xpath = locator.replace("class=", "");
					returnObj = webdriver.findElement(By.className(xpath));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.className(xpath)));
						returnObj = webdriver.findElement(By.className(xpath));
					}
				}
				else if (locator.contains("name="))
				{
					xpath = locator.replace("name=", "");
					returnObj = webdriver.findElement(By.name(xpath));
					if (!returnObj.isDisplayed())
					{
						new WebDriverWait(webdriver, 25).until(ExpectedConditions.presenceOfElementLocated(By.name(xpath)));
						returnObj = webdriver.findElement(By.name(xpath));
					}
				}
				else
				{
					log.info(MessageFormat.format("Wrong / have not support XPATH/CSS= \"{0}\"", locator));
					return returnObj;
				}

				if (isValidate)
				{
					if (returnObj.isDisplayed())
					{
						log.info("Object is displayed with XPATH/CSS=: " + locator);
						return returnObj;
					}
					else
					{
						log.info(MessageFormat.format("XPATH/CSS= \"{0}\" is existed but did not visible in browser", locator));
						return null;
					}
				}
				else
				{
					if (returnObj != null)
					{
						log.info("Object is displayed with XPATH/CSS=: " + locator);
						return returnObj;
					}
					else
					{
						log.info(MessageFormat.format("XPATH/CSS= \"{0}\" is existed but did not visible in browser", locator));
						return null;
					}
				}

			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return returnObj;
			}
		}


		public WebElement getWebElementDisplayByCSS(final String cssSelector) throws Exception
		{

			WebElement returnObj = null;
			try
			{
				returnObj = webdriver.findElement(By.cssSelector(cssSelector));

				if (returnObj.isDisplayed())
				{
					log.info("Object is displayed with CSS= " + cssSelector);
					return returnObj;
				}
				else
				{
					log.info(MessageFormat.format("CSS= \"{0}\" is exist but did not visible in browser", cssSelector));
					return null;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return returnObj;
			}
		}


		public List<WebElement> getWebElementListDisplay(final String locator) throws Exception
		{

			List<WebElement> returnObjList = null;

			try
			{
				String xpath;
				if (locator.contains("Xpath=") || locator.startsWith("//") || locator.startsWith("("))
				{
					xpath = locator.replace("Xpath=", "");
					returnObjList = webdriver.findElements(By.xpath(xpath));
				}
				else if ((!locator.contains("//") && !locator.contains("@")) || locator.contains(">"))
				{
					returnObjList = webdriver.findElements(By.cssSelector(locator));
				}
				else if (locator.contains("id="))
				{
					xpath = locator.replace("id=", "");
					returnObjList = webdriver.findElements(By.id(xpath));
				}
				else if (locator.contains("class="))
				{
					xpath = locator.replace("class=", "");
					returnObjList = webdriver.findElements(By.className(xpath));
				}
				else if (locator.contains("name="))
				{
					xpath = locator.replace("name=", "");
					returnObjList = webdriver.findElements(By.name(xpath));
				}
				else
				{
					log.info(MessageFormat.format("Wrong / have not support XPATH/CSS= \"{0}\"", locator));
					return returnObjList;
				}

				if (returnObjList != null)
				{
					log.info("Got object childs of Object with XPATH/CSS= " + locator);
					return returnObjList;
				}
				else
				{
					log.info(MessageFormat.format("XPATH/CSS= \"{0}\" is exist but did not visible in browser", locator));
					return null;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return returnObjList;
			}
		}


		public List<WebElement> getWebElementListDisplayByCSS(final String cssSelector) throws Exception
		{

			List<WebElement> returnObjList = null;

			try
			{
				returnObjList = webdriver.findElements(By.cssSelector(cssSelector));

				if (returnObjList != null)
				{
					log.info("Got object childs of Object with CSS= " + cssSelector);
					return returnObjList;
				}
				else
				{
					log.info(MessageFormat.format("CSS= \"{0}\" is exist but did not visible in browser", cssSelector));
					return null;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return returnObjList;
			}
		}


		public void type(final String locator, final String value) throws Exception
		{
			driver.waitControling.implicitlyWaitBySecond(10);
			try
			{
				final WebElement obj = getWebElementDisplay(locator);
				if (!getAttribute(locator, "value").equals(""))
				{
					obj.clear();
				}
				//obj.clear();
				obj.sendKeys(value);
				utils.setTrueResult(MessageFormat.format("Type on \"{0}\" with value \"{1}\"", locator, value));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		public void clearText(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);

				if (!getAttribute(locator, "value").equals(""))
				{
					obj.clear();
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getText(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);

				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH: \"{0}\" did not exist in browser", locator));
					return "";
				}
				else
				{
					log.info(MessageFormat.format("Text of object with XPATH: \"{0}\" is \"{1}\"", locator, obj.getText()));
					return obj.getText();
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		public String getValue(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);

				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH: \"{0}\" did not exist in browser", locator));
					return "";
				}
				else
				{
					log.info(MessageFormat.format("Text of object with XPATH: \"{0}\" is \"{1}\"", locator, obj.getText()));
					return obj.getAttribute("value");
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		public String getTextByCSS(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplayByCSS(locator);

				if (obj == null)
				{
					log.error(MessageFormat.format("Object with CSS: \"{0}\" did not exist in browser", locator));
					return "";
				}
				else
				{
					log.info(MessageFormat.format("Text of object with CSS: \"{0}\" is \"{1}\"", locator, obj.getText()));
					return obj.getText();
				}
			}
			catch (final Exception e)
			{
				log.error(MessageFormat.format("Could not get text of object with CSS: \"{0}\"", locator));
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void click(final String locator) throws Exception
		{
			driver.waitControling.implicitlyWaitBySecond(10);
			try
			{
				final WebElement obj = getWebElementDisplay(locator);

				if (obj == null)
				{
					utils.setFalseResult(MessageFormat.format("Object with XPATH: \"{0}\" did not exist in browser", locator));
					throw new Exception();
				}
				else
				{
					obj.click();
					log.info(MessageFormat.format("Clicked object with XPATH: \"{0}\"", locator));
				}
			}
			catch (final Exception e)
			{
				log.error(MessageFormat.format("Could not click object with XPATH: \"{0}\"", locator));
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		public void click(final String locator, final boolean isValidate) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator, isValidate);

				if (obj == null)
				{
					utils.setFalseResult(MessageFormat.format("Object with XPATH: \"{0}\" did not exist in browser", locator));
					throw new Exception();
				}
				else
				{
					obj.click();
					log.info(MessageFormat.format("Clicked object with XPATH: \"{0}\"", locator));
				}
			}
			catch (final Exception e)
			{
				log.error(MessageFormat.format("Could not click object with XPATH: \"{0}\"", locator));
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		public void click(final String locator, final String CSSType) throws Exception
		{
			try
			{
				WebElement obj;
				if (CSSType.equals("CSS"))
				{
					obj = getWebElementDisplayByCSS(locator);
				}
				else
				{
					obj = getWebElementDisplay(locator);
				}

				if (obj == null)
				{
					utils.setFalseResult(MessageFormat.format("Object with XPATH: \"{0}\" did not exist in browser", locator));
					throw new Exception();
				}
				else
				{
					obj.click();
					log.info(MessageFormat.format("Clicked object with XPATH: \"{0}\"", locator));
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void selectFrame(final String locator) throws Exception
		{

			try
			{
				webdriver.switchTo().frame(getAttribute(locator, "name"));
				log.info("Select frame with XPATH: \"" + locator + "\" is executed");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		public void selectFrameByIndex(final int index) throws Exception
		{

			try
			{
				webdriver.switchTo().frame(index);
				log.info("Select frame with Index: \"" + index + "\" is executed");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void deselectFrame() throws Exception
		{

			try
			{
				webdriver.switchTo().defaultContent();
				log.info("Select default frame is executed");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getAttribute(final String locator, final String attribute) throws Exception
		{

			try
			{
				String attName = "";
				final WebElement obj = getWebElementDisplay(locator);
				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH= \"{0}\" did not exist on browser", locator));
					throw new Exception();
				}
				else
				{
					attName = obj.getAttribute(attribute);
					log.info(MessageFormat.format("Attribute of object with XPATH= \"{0}\" is \"{1}\"", locator, attName));
					return attName;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		//new added 2/04/2018
		//get hidden attribute
		
		public String getHiddenAttribute(final String locator, final String attribute) throws Exception
		{

			try
			{
				String attName = "";
				final WebElement obj = getWebElementHidden(locator);
				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH= \"{0}\" did not exist on browser", locator));
					throw new Exception();
				}
				else
				{
					attName = obj.getAttribute(attribute);
					log.info(MessageFormat.format("Attribute of object with XPATH= \"{0}\" is \"{1}\"", locator, attName));
					return attName;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}
		
		//==============
		

		public String getAttribute(final String locator, final String CSSType, final String attribute) throws Exception
		{

			try
			{
				String attName = "";
				final WebElement obj = getWebElementDisplayByCSS(locator);
				if (obj == null)
				{
					log.error(MessageFormat.format("Object with CSS= \"{0}\" did not exist on browser", locator));
					throw new Exception();
				}
				else
				{
					attName = obj.getAttribute(attribute);
					log.info(MessageFormat.format("Attribute of object with CSS= \"{0}\" is \"{1}\"", locator, attName));
					return attName;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String captureScreen() throws Exception
		{

			try
			{
				final String preoutputFile = utils.getDatetimeStamp() + ".png";
				final String postoutputFile = preoutputFile.replaceAll(":", "-");
				//New update to append current method to name 19/04/2018
				String outputFile = AutoTestConstants.screenshootFolder + postoutputFile;
	
				File source;
				if (AutoTestConstants.HUBADDRESS.equals(""))
				{
					source = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
				}
				else
				{
					final WebDriver capture = new Augmenter().augment(webdriver);
					source = ((TakesScreenshot) capture).getScreenshotAs(OutputType.FILE);
				}

				FileUtils.copyFile(source, new File(outputFile));

				outputFile = ":" + outputFile.substring(outputFile.indexOf("/Results"));
				
				log.info(MessageFormat.format("Capture screenshot successfully. Output file was on PATH: \"{0}\"", outputFile));
				return outputFile;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		
		public String captureFullScreen() throws Exception
		{

			try
			{
				final String preoutputFile = utils.getDatetimeStamp() + ".png";
				final String postoutputFile = preoutputFile.replaceAll(":", "-").replaceAll(" ", "_");
			
				String outputFile = AutoTestConstants.screenshootFolder  + postoutputFile;
	
				
				File source;
				if (AutoTestConstants.HUBADDRESS.equals(""))
				{
					source = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
				}
				else
				{
					final WebDriver capture = new Augmenter().augment(webdriver);
					source = ((TakesScreenshot) capture).getScreenshotAs(OutputType.FILE);
				}

				FileUtils.copyFile(source, new File(outputFile));

				//outputFile = ":" + outputFile.substring(outputFile.indexOf("/Results"));
				
				log.info(MessageFormat.format("Capture screenshot successfully. Output file was on PATH: \"{0}\"", outputFile));
				return outputFile;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		
		
		public void select(final String locator, final String value, final String element) throws Exception
		{

			try
			{

				final WebElement object = getWebElementDisplay(locator);
				final Select obj = new Select(object);
				obj.selectByVisibleText(value);
				//verifyFunc.isTrue(getSelectedText(locator).equals(value), "Set value: " + value + " in Combobox: " + element);
				log.info("Select value: \"" + value + "\" in Combobox is executed");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void selectCombobox(final String combobox, final String selected, final String value) throws Exception
		{

			try
			{
				click(combobox);
				driver.elementChecking.waitForElementPresent(selected, 5);
				clickItemMatchValueByIndex(selected, value);
				utils.setTrueResult(MessageFormat.format("Selected value \"{0}\" from combobox \"{1}\"", value, combobox));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getSelectedText(final String locator) throws Exception
		{

			try
			{
				final WebElement object = getWebElementDisplay(locator);
				final Select obj = new Select(object);
				return obj.getFirstSelectedOption().getText();
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public List<String> getListXpathDisplay(final String locator) throws Exception
		{

			try
			{
				final List<WebElement> listObj = webdriver.findElements(By.xpath(locator));
				final List<String> resultList = new ArrayList<String>();

				for (int i = 0; i < listObj.size(); i++)
				{
					if (listObj.get(i).isDisplayed())
					{
						final String xpath = driver.elementEventControling.getXpathByIndex(locator, i + 1);
						resultList.add(xpath);
					}
				}

				if (resultList.size() == 0)
				{
					log.warning(MessageFormat.format("No any xpath \"{0}\" in browser", locator));
					throw new Exception();
				}
				else
				{
					return resultList;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void keyPress(final String locator, final Keys keySequence) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);
				if (obj == null)
				{
					log.error(MessageFormat.format("Could not find object with XPATH \"{0}\" in browser", locator));
				}
				else
				{
					obj.sendKeys(keySequence);
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void mouseOver(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);
				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH/CSS= \"{0}\" is not existed on browser", locator));
					throw new Exception();
				}

				final Actions builder = new Actions(webdriver);
				final Action myAction = builder.moveToElement(obj).build();
				myAction.perform();
				log.info(MessageFormat.format("Mouse over object with XPATH/CSS = \"{0}\" is executed", locator));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void mouseOver(final WebElement webElement) throws Exception
		{

			try
			{
				final Actions builder = new Actions(webdriver);
				final Action myAction = builder.moveToElement(webElement).build();
				myAction.perform();
				log.info(MessageFormat.format("Mouse over object with TEXT = \"{0}\" is executed", webElement.getText()));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void mouseOverandClick(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);
				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH= \"{0}\" is not existed on browser", locator));
					throw new Exception();
				}

				final Actions builder = new Actions(webdriver);
				final Action myAction = builder.moveToElement(obj).click().build();
				myAction.perform();
				log.info(MessageFormat.format("Mouse over and click on object with XPATH= \"{0}\" is executed", locator));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public int getPositionX(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);
				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH = \"{0}\" is NULL", locator));
					throw new Exception();
				}
				return obj.getLocation().x;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public int getPositionY(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);
				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH = \"{0}\" is NULL", locator));
					throw new Exception();
				}
				return obj.getLocation().y;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public Dimension getDimension(final String locator) throws Exception
		{
			try
			{
				final WebElement obj = getWebElementDisplay(locator);
				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH = \"{0}\" is NULL", locator));
					throw new Exception();
				}

				return obj.getSize();
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getXpathPlusAttribute(final String locator, final String attribute, final String value) throws Exception
		{
			try
			{
				String returnXpath = "";

				if (locator.contains(")"))
				{
					final String tagName = locator.substring(locator.lastIndexOf("/") + 1, locator.indexOf(")"));
					returnXpath = locator.replace("/" + tagName + ")", "/" + tagName + "[@" + attribute + "=\"" + value + "\"])");
				}
				else
				{
					returnXpath = locator + "[@" + attribute + "='" + value + "']";
				}
				log.info(MessageFormat.format("New XPATH of object with Attritube = \"{0}\" and Value=\"{1}\" is XPATH= \"{2}\"",
						attribute, value, returnXpath));
				return returnXpath;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void highlightElement(final WebElement obj) throws Exception
		{

			try
			{
				final JavascriptExecutor js = (JavascriptExecutor) webdriver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", obj, "color: green; border: 4px solid green;");

			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getXpathMatchValueByIndex(final String locator, final String value) throws Exception
		{

			try
			{
				String retXpath = "";

				final List<String> listObj = getListXpathDisplay(locator);
				final int size = listObj.size();

				for (int i = 0; i < size; i++)
				{
					final String locatorText = getText(listObj.get(i));
					if (locatorText.toLowerCase().equals(value.toLowerCase()))
					{
						retXpath = listObj.get(i);
						break;
					}
				}

				if (retXpath.equals(""))
				{
					log.error(MessageFormat.format("Do not have any xpath from XPATH= \"{0}\" match \"{1}\"", locator, value));
					throw new Exception();
				}
				else
				{
					log.info(MessageFormat.format("XPATH from XPATH= \"{0}\" match \"{1}\" is \"{2}\"", locator, value, retXpath));
					return retXpath;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return "";
			}
		}


		public String getXpathMatchValue(final String locator, final String value) throws Exception
		{

			try
			{
				final String xpathValue = "[text()='" + value + "']";
				return locator + xpathValue;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getXpathContainValue(final String locator, final String value) throws Exception
		{
			try
			{
				String retXpath = "";

				final List<String> listObj = getListXpathDisplay(locator);
				final int size = listObj.size();
				for (int i = 0; i < size; i++)
				{
					final String locatorText = getText(listObj.get(i));
					if (locatorText.contains(value))
					{
						retXpath = listObj.get(i);
						break;
					}
				}

				if (retXpath.equals(""))
				{
					log.error(MessageFormat.format("Do not have any XPATH= \"{0}\" contain TEXT= \"{1}\"", locator, value));
					throw new Exception();
				}
				else
				{
					log.info(MessageFormat.format("XPATH from XPATH= \"{0}\" contain TEXT= \"{1}\" is XPATH= \"{2}\"", locator, value,
							retXpath));
					return retXpath;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getXpathContainAttributeByIndex(final String locator, final String attribute, final String value)
				throws Exception
		{

			try
			{
				String retXpath = "";

				final List<String> listObj = getListXpathDisplay(locator);
				final int size = listObj.size();

				for (int i = 0; i < size; i++)
				{
					final String locatorAtt = getAttribute(listObj.get(i), attribute);
					if (locatorAtt.toLowerCase().contains(value.toLowerCase()))
					{
						retXpath = listObj.get(i);
						break;
					}
				}

				if (retXpath.equals(""))
				{
					log.error(
							MessageFormat.format("Do not have any XPATH from XPATH= \"{0}\" match Attribute = \"{1}\", Value=\"{2}\"",
									locator, attribute, value));
					throw new Exception();
				}
				else
				{
					log.info(
							MessageFormat.format("XPATH from XPATH= \"{0}\" match Attribute = \"{1}\", Value=\"{2}\" is XPATH= \"{3}\"",
									locator, attribute, value, retXpath));
					return retXpath;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void doubleClick(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);

				final Actions builder = new Actions(webdriver);
				final Action myAction = builder.doubleClick(obj).build();
				myAction.perform();
				driver.waitControling.sleep(1000);
				log.info(MessageFormat.format("Double-Click on Object with XPATH= \"{0}\" is executed", locator));

			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void clickItemMatchValue(final String xpath, final String value) throws Exception
		{

			try
			{
				final String item = getXpathMatchValue(xpath, value);
				driver.elementEventControling.click(item);
				log.info(MessageFormat.format("Item which is matched with Value= \"{0}\" is clicked", value));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void clickItemMatchValueByIndex(final String xpath, final String value) throws Exception
		{

			try
			{
				final String item = getXpathMatchValueByIndex(xpath, value);
				driver.elementEventControling.click(item);
				log.info(MessageFormat.format("Item which is matched with Index= \"{0}\" is clicked", value));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void clickItemContainValueByIndex(final String xpath, final String value) throws Exception
		{

			try
			{
				final String item = getXpathContainValue(xpath, value);
				driver.elementEventControling.click(item);
				log.info(MessageFormat.format("Item which is contained value by Index= \"{0}\" is clicked", value));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void dragAndDrop(final String locator, final String target) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);
				final WebElement targetObj = getWebElementDisplay(target);

				obj.click();

				final Actions builder = new Actions(webdriver);
				final Action myAction = builder.dragAndDrop(obj, targetObj).build();
				myAction.perform();

				log.info(MessageFormat.format(
						"Drag from Object with XPATH= \"{0}\" and drop to Object with XPATH= \"{1}\" is executed", locator, target));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void dragAndDropforCopy(final String locator, final String target) throws Exception
		{
			try
			{
				final WebElement obj = getWebElementDisplay(locator);
				final WebElement targetObj = getWebElementDisplay(target);

				obj.click();

				final Actions builder = new Actions(webdriver);
				final Action myAction = builder.keyDown(Keys.SHIFT).dragAndDrop(obj, targetObj).keyUp(Keys.SHIFT).build();
				myAction.perform();

				log.info(MessageFormat.format(
						"Copy by drag from Object with XPATH= \"{0}\" and drop to Object with XPATH= \"{1}\" is executed", locator,
						target));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void selecAllByMouse(final String startItem, final String endItem) throws Exception
		{
			try
			{
				final WebElement start = getWebElementDisplay(startItem);
				final WebElement end = getWebElementDisplay(endItem);

				final Actions builder = new Actions(webdriver);
				final Action myAction = builder.click(start).keyDown(Keys.SHIFT).click(end).keyUp(Keys.SHIFT).build();
				myAction.perform();

				log.info(MessageFormat.format(
						"Selected all Item by Mouse from Object with XPATH= \"{0}\" and drop to Object with XPATH= \"{1}\" is executed",
						startItem, endItem));

			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void rightClick(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = getWebElementDisplay(locator);
				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH= \"{0}\" is not existed on browser", locator));
					throw new Exception();
				}

				final Actions builder = new Actions(webdriver);
				final Action myAction = builder.contextClick(obj).build();
				myAction.perform();
				driver.waitControling.sleep(1000);
				log.info(MessageFormat.format("Right click on object with XPATH= \"{0}\" is executed", locator));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void clickOKConfirmOnAlert() throws Exception
		{

			String dialogContent = "";
			try
			{
				final Alert alert = webdriver.switchTo().alert();
				if (alert == null)
				{
					log.error("Confirmation dialog is not existed");
					throw new Exception();
				}

				dialogContent = alert.getText();
				alert.accept();
				log.info(MessageFormat.format("Click OK Confirmation on dialog \"{0}\" is executed", dialogContent));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void clickCancelConfirmOnAlert() throws Exception
		{

			String dialogContent = "";
			try
			{
				final Alert alert = webdriver.switchTo().alert();
				if (alert == null)
				{
					log.error("Confirmation dialog is not existed");
					throw new Exception();
				}

				dialogContent = alert.getText();
				alert.dismiss();
				log.info(MessageFormat.format("Click Cancel Confirmation on dialog \"{0}\" is executed", dialogContent));
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getXpathByIndex(final String locator, final int num) throws Exception
		{

			try
			{
				String xpathObj;
				if (num < 0)
				{
					log.error(MessageFormat.format("Could not get XPATH with negative Index= \"{0}\"", num));
					throw new Exception();
				}

				if (num == 1)
				{
					xpathObj = locator;
				}
				else if (!locator.startsWith("(") && !locator.endsWith(")"))
				{
					xpathObj = "(" + locator + ")[" + num + "]";
				}
				else if (locator.startsWith("(") && !locator.endsWith(")"))
				{
					xpathObj = "(" + locator + ")[" + num + "]";
				}
				else
				{
					xpathObj = locator + "[" + num + "]";
				}

				return xpathObj;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public int getListElementSize(final String locator) throws Exception
		{

			try
			{
				final List<String> list = getListXpathDisplay(locator);

				return list.size();
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public boolean isChildElement(final String locator) throws Exception
		{

			try
			{
				final String newRootLocator = locator + "/*";
				final List<WebElement> childList = driver.elementEventControling.getWebElementListDisplay(newRootLocator);
				if (childList.size() == 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		public boolean isChildElementByCss(final String cssSelector) throws Exception
		{

			try
			{
				final String newRootLocator = cssSelector + "> *";
				final List<WebElement> childList = driver.elementEventControling.getWebElementListDisplayByCSS(newRootLocator);
				if (childList.size() == 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public boolean checkStringInList(final String strItem, final List<String> strList) throws Exception
		{

			try
			{
				for (final String item : strList)
				{
					if (item.equals(strItem))
					{
						return true;
					}
				}
				return false;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return false;
			}
		}


		public List<WebElement> getAllChildElements(final String parentLocator) throws Exception
		{

			try
			{
				final String newRootLocator = parentLocator + "/*";
				final List<WebElement> childList = driver.elementEventControling.getWebElementListDisplay(newRootLocator);
				if (childList.size() > 1)
				{
					returnElementList.addAll(childList);

					for (final WebElement childElement : childList)
					{
						final String childXpath = newRootLocator.substring(0, newRootLocator.length() - 2) + "/"
								+ childElement.getTagName();
						if (!isChildElement(childXpath) && !checkStringInList(childXpath, returnXpathList))
						{
							returnElementList.addAll(getAllChildElements(childXpath));
						}
						returnXpathList.add(childXpath);
					}
				}
				return returnElementList;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public List<WebElement> getAllChildElementsByCSS(final String parentLocator) throws Exception
		{

			try
			{
				final String newRootLocator = parentLocator + " > *";
				final List<WebElement> childList = driver.elementEventControling.getWebElementListDisplayByCSS(newRootLocator);
				if (childList.size() > 1)
				{
					//returnElementList.addAll(childList);

					for (final WebElement childElement : childList)
					{
						final String childXpath = newRootLocator.substring(0, newRootLocator.length() - 1) + childElement.getTagName();
						if (!isChildElementByCss(childXpath) && !checkStringInList(childXpath, returnXpathList))
						{
							returnElementList.addAll(getAllChildElementsByCSS(childXpath));
						}
						returnXpathList.add(childXpath);
						returnElementList.add(childElement);
					}
				}
				return returnElementList;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public List<WebElement> getChildElementsByTagName(final String parentLocator, final String tagType) throws Exception
		{
			try
			{
				final WebElement eleParent = driver.elementEventControling.getWebElementDisplay(parentLocator);

				return eleParent.findElements(By.tagName(tagType));

			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}
	}


	/**
	 * @ClassName: ElementChecking
	 * @Description: This class contains all functions for element checking
	 * @author DatNguyen
	 * @CreatedDate: 07/04/2014
	 */
	public static class ElementChecking
	{

		public boolean isElementDisplay(final String locator) throws Exception
		{
			try
			{
				final WebElement obj = driver.elementEventControling.getWebElementDisplay(locator);

				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH: \"{0}\" is not displayed", locator));
					return false;
				}
				else
				{
					log.info(MessageFormat.format("Object with XPATH: \"{0}\" is displayed", locator));
					return true;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return false;
			}
		}

		public boolean isElementDisplay(final String locator, final boolean isValidate) throws Exception
		{
			try
			{
				final WebElement obj = driver.elementEventControling.getWebElementDisplay(locator, isValidate);

				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH: \"{0}\" is not displayed", locator));
					return false;
				}
				else
				{
					log.info(MessageFormat.format("Object with XPATH: \"{0}\" is displayed", locator));
					return true;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return false;
			}
		}

		public boolean isElementDisplay(final String locator, final String CSSType) throws Exception
		{
			try
			{
				WebElement obj;
				if (CSSType.equals("CSS"))
				{
					obj = driver.elementEventControling.getWebElementDisplayByCSS(locator);
				}
				else
				{
					obj = driver.elementEventControling.getWebElementDisplay(locator);
				}

				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH: \"{0}\" is not displayed", locator));
					return false;
				}
				else
				{
					log.info(MessageFormat.format("Object with XPATH: \"{0}\" is displayed", locator));
					return true;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return false;
			}
		}

		public boolean isElementEnabled(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = driver.elementEventControling.getWebElementDisplay(locator);

				if (obj == null)
				{
					utils.setFalseResult(MessageFormat.format("Object with XPATH: \"{0}\" IS NOT displayed", locator));
					log.error(MessageFormat.format("Object with XPATH: \"{0}\" IS NOT displayed", locator));
					return false;
				}
				else if (obj.isEnabled())
				{
					log.info(MessageFormat.format("Object with XPATH: \"{0}\" is enabled", locator));
					return true;
				}
				else
				{
					log.info(MessageFormat.format("Object with XPATH: \"{0}\" IS NOT enabled", locator));
					return false;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return false;
			}
		}

		public boolean isElementSelected(final String locator) throws Exception
		{

			try
			{
				final WebElement obj = driver.elementEventControling.getWebElementDisplay(locator);

				if (obj == null)
				{
					utils.setFalseResult(MessageFormat.format("Object with XPATH: \"{0}\" IS NOT displayed", locator));
					log.error(MessageFormat.format("Object with XPATH: \"{0}\" IS NOT displayed", locator));
					return false;
				}
				else if (obj.isSelected())
				{
					log.info(MessageFormat.format("Object with XPATH: \"{0}\" is selected", locator));
					return true;
				}
				else
				{
					log.info(MessageFormat.format("Object with XPATH: \"{0}\" IS NOT selected", locator));
					return false;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return false;
			}
		}

		public boolean isElementSelected(final String locator, final boolean isValidate) throws Exception
		{

			try
			{
				final WebElement obj = driver.elementEventControling.getWebElementDisplay(locator, isValidate);

				if (obj == null)
				{
					utils.setFalseResult(MessageFormat.format("Object with XPATH: \"{0}\" IS NOT displayed", locator));
					log.error(MessageFormat.format("Object with XPATH: \"{0}\" IS NOT displayed", locator));
					return false;
				}
				else if (obj.isSelected())
				{
					log.info(MessageFormat.format("Object with XPATH: \"{0}\" is selected", locator));
					return true;
				}
				else
				{
					log.info(MessageFormat.format("Object with XPATH: \"{0}\" IS NOT selected", locator));
					return false;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return false;
			}
		}

		public boolean isElementDisplay(final String locator, final long miliSec) throws Exception
		{

			try
			{
				driver.waitControling.implicitlyWaitByMiliSecond(miliSec);
				final WebElement obj = driver.elementEventControling.getWebElementDisplay(locator);
				driver.waitControling.implicitlyWaitByMiliSecond(10);

				if (obj == null)
				{
					log.error(MessageFormat.format("Object with XPATH: \"{0}\" is not displayed", locator));
					return false;
				}
				else
				{
					log.info(MessageFormat.format("Object with XPATH: \"{0}\" is displayed", locator));
					return true;
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return false;
			}
		}


		public boolean isElementNotDisplay(final String locator) throws Exception
		{
			try
			{

				driver.waitControling.implicitlyWaitBySecond(2);
				for (int i = 1; i <= 2; i++)
				{
					final WebElement obj = driver.elementEventControling.getWebElementDisplay(locator);

					if (obj == null)
					{
						log.info(MessageFormat.format("Object with XPATH: \"{0}\" is not displayed as expected", locator));
						break;
					}
					else if (i == 2)
					{
						log.error(MessageFormat.format("Object with XPATH: \"{0}\" is displayed as unexpected", locator));
						throw new Exception();
					}
					else
					{
						log.error(MessageFormat.format("Object with XPATH: \"{0}\" is displayed as unexpected", locator));
						driver.waitControling.sleep(1000);
					}
				}
				driver.waitControling.implicitlyWaitBySecond(2);
				return true;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				return false;
			}
		}


		public void waitForCondition(final boolean condition, final int secs) throws Exception
		{

			try
			{
				int i;
				for (i = 0; i <= secs; i++)
				{
					if (condition)
					{
						log.info("WaitForCondition function is executed passed");
						break;
					}
					else
					{
						driver.waitControling.sleep(1000);
					}
				}
				if (condition && i == secs)
				{
					log.info("WaitForCondition function is executed timeout");
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void waitForTextEqual(final String xpath, final String compareStr, final int secs) throws Exception
		{

			try
			{
				int i;
				String con = "";
				for (i = 0; i <= secs; i++)
				{
					con = driver.elementEventControling.getText(xpath);
					if (con.equals(compareStr))
					{
						log.info("WaitForTextEqual function is executed passed");
						break;
					}
					else
					{
						driver.waitControling.sleep(1000);
					}
				}
				if (!con.equals(compareStr) && i == secs)
				{
					log.info("WaitForTextEqual function is executed timeout");
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void waitForAttributeContainValue(final String xpath, final String att, final String compareStr, final int secs)
				throws Exception
		{

			try
			{
				int i;
				for (i = 0; i <= secs; i++)
				{
					if (driver.elementEventControling.getAttribute(xpath, "src").contains(compareStr))
					{
						log.info("waitForAttributeContainValue() function is executed passed");
						break;
					}
					else
					{
						driver.waitControling.sleep(1000);
					}
				}
				if (!driver.elementEventControling.getAttribute(xpath, "src").contains(compareStr) && i == secs)
				{
					log.info("waitForAttributeContainValue() function is executed timeout");
				}
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public boolean waitForElementPresent(final String xpath, final int secs) throws Exception
		{

			try
			{
				int i;
				boolean condition = false;
				for (i = 0; i <= secs; i++)
				{
					condition = driver.elementChecking.isElementDisplay(xpath, 100);
					if (condition)
					{
						log.info(MessageFormat.format("Object with XPATH= \"{0}\" is presented on browser", xpath));
						break;
					}
					else
					{
						driver.waitControling.sleep(1000);
					}
				}

				if (!condition && i == secs)
				{
					log.error(MessageFormat.format("Object with XPATH= \"{0}\" is not presented on browser", xpath));
					return false;
				}
				else
				{
					return true;
				}

			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

	}


	/**
	 * @ClassName: KeyEventControling
	 * @Description: This class contains all functions for key event controling
	 * @author DatNguyen
	 * @CreatedDate: 07/04/2014
	 */
	public static class KeyEventControling
	{

		public void keyPaste() throws Exception
		{

			try
			{
				Thread.sleep(AutoTestConstants.MIN_MILISECOND);
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void keyBackSpace() throws Exception
		{

			try
			{
				Thread.sleep(AutoTestConstants.MIN_MILISECOND);
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void keyEsc() throws Exception
		{

			try
			{
				Thread.sleep(AutoTestConstants.MIN_MILISECOND);
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void keyK() throws Exception
		{
			try
			{
				Thread.sleep(AutoTestConstants.MIN_MILISECOND);
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_K);
				robot.keyRelease(KeyEvent.VK_K);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

		public void keyY() throws Exception
		{
			try
			{
				Thread.sleep(AutoTestConstants.MIN_MILISECOND);
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_Y);
				robot.keyRelease(KeyEvent.VK_Y);
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void copyToClipboard(final String string) throws Exception
		{

			try
			{
				final StringSelection stringSelection = new StringSelection(string);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			}
			catch (final HeadlessException e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void uploadFile(final String locator, final String filePath) throws Exception
		{

			try
			{
				driver.elementEventControling.type(locator, filePath);
				log.info(MessageFormat.format("Type on \"{0}\" with file path \"{0}\"", locator, filePath));

			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void setClipboardData(final String string) throws Exception
		{

			try
			{
				final StringSelection stringSelection = new StringSelection(string);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

				log.info("Set clipboard data");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void keyPasteClipboard() throws Exception
		{

			try
			{
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);

				log.info("Pasted to clipboard");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void keySelectAll() throws Exception
		{

			try
			{
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_A);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);

				log.info("Selected all items by pressed key from keyboard");

			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void keyNewBroswer() throws Exception
		{
			try
			{
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_N);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);

				log.info("Opened new browser by pressed key from keyboard");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void keyEnter() throws Exception
		{

			try
			{
				Thread.sleep(AutoTestConstants.MIN_MILISECOND);
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);

				log.info("Pressed ENTER from keyboard");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void keyRefresh() throws Exception
		{

			try
			{
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_F5);
				robot.keyRelease(KeyEvent.VK_F5);
				Thread.sleep(2000);

				log.info("Refreshed browser by pressed from keyboard");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public void keyClose() throws Exception
		{

			try
			{
				final Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_F4);
				robot.keyRelease(KeyEvent.VK_F4);
				robot.keyRelease(KeyEvent.VK_ALT);
				Thread.sleep(2000);

				log.info("Closed browser by pressed from keyboard");
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public int getResponseCode(final String URLaddress) throws Exception
		{

			try
			{
				int resCode = 0;
				final URL url = new URL(URLaddress);
				if (URLaddress.startsWith("https"))
				{
					final HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
					resCode = con.getResponseCode();
				}
				else
				{
					final HttpURLConnection con = (HttpURLConnection) url.openConnection();
					resCode = con.getResponseCode();
				}
				return resCode;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}


		public String getDataDynamic(final String data) throws Exception
		{

			try
			{
				final String dynamicdata = utils.getDatetimeStamp();

				return data + " " + dynamicdata;
			}
			catch (final Exception e)
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
				throw new Exception();
			}
		}

	}
}

