package com.autotest.framework.library;

import java.text.MessageFormat;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.autotest.configure.Environment;
import com.autotest.configure.AutoTestConstants;
import com.autotest.utils.StackTraceInfo;


@SuppressWarnings("javadoc")
public class JQueryFunctions extends Environment
{

	CommonFunctions commonFunctions;

	/**
	 * @param commonFunctions
	 */
	public JQueryFunctions(final CommonFunctions commonFunctions)
	{
		this.commonFunctions = commonFunctions;
	}

	private WebDriver getWebDriver()
	{
		return commonFunctions.getWebDriver();
	}


	/**
	 * @param locator
	 * @throws Exception
	 */
	public void mouseOverByText(final String locator) throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			String tagname;
			if (locator.lastIndexOf(")") != -1)
			{
				tagname = locator.substring(locator.lastIndexOf("/") + 1, locator.lastIndexOf(")"));
			}
			else
			{
				tagname = locator.substring(locator.lastIndexOf("/") + 1, locator.length());
			}
			final String text = driver.elementEventControling.getText(locator);
			final String xpath = tagname + ":contains('" + text + "')";
			final String script = "$(\"" + xpath + "\").mouseover();";
			js.executeScript(script);

			log.info(MessageFormat.format("Mouse over Text= \"{0}\" of Object with XPATH= \"{1}\" is executed", text, locator));

		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	/**
	 * @param locator
	 * @param text
	 * @throws Exception
	 */
	public void mouseOverByText(final String locator, final String text) throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			String tagname;
			if (locator.lastIndexOf(")") != -1)
			{
				tagname = locator.substring(locator.lastIndexOf("/") + 1, locator.lastIndexOf(")"));
			}
			else
			{
				tagname = locator.substring(locator.lastIndexOf("/") + 1, locator.length());
			}
			final String xpath = tagname + ":contains('" + text + "')";
			final String script = "$(\"" + xpath + "\").mouseover();";
			js.executeScript(script);

			log.info(MessageFormat.format("Mouse over Text= \"{0}\" of Object with XPATH= \"{1}\" is executed", text, locator));

		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	/**
	 * @param locator
	 * @throws Exception
	 */
	public void mouseOverByClass(final String locator) throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			String tagname;
			if (locator.lastIndexOf(")") != -1)
			{
				tagname = locator.substring(locator.lastIndexOf("/") + 1, locator.lastIndexOf(")"));
			}
			else
			{
				tagname = locator.substring(locator.lastIndexOf("/") + 1, locator.length());
			}
			final String className = driver.elementEventControling.getAttribute(locator, "class").replace(" ", ".");
			final String jqueryLocator = tagname + "." + className;
			final String script = "$(\"" + jqueryLocator + "\").mouseover();";

			js.executeScript(script);

			log.info(MessageFormat.format("Mouse over ClassName= \"{0}\" of Object with XPATH= \"{1}\" is executed", className,
					locator));

		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	/**
	 * @param locator
	 * @throws Exception
	 */
	public void mouseLeaveByClass(final String locator) throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			String tagname;
			if (locator.lastIndexOf(")") != -1)
			{
				tagname = locator.substring(locator.lastIndexOf("/") + 1, locator.lastIndexOf(")"));
			}
			else
			{
				tagname = locator.substring(locator.lastIndexOf("/") + 1, locator.length());
			}
			final String className = driver.elementEventControling.getAttribute(locator, "class").replace(" ", ".");
			final String jqueryLocator = tagname + "." + className;
			final String script = "$(\"" + jqueryLocator + "\").mouseleave();";

			js.executeScript(script);

			log.info(MessageFormat.format("Mouse leave ClassName= \"{0}\" on Object with XPATH= \"{1}\" is executed", className,
					locator));

		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	/**
	 * @param locator
	 * @throws Exception
	 */
	public void mouseOverByCSS(final String locator) throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final WebElement obj = driver.elementEventControling.getWebElementDisplay(locator);
			final String tagname = obj.getTagName();

			final String text = driver.elementEventControling.getText(locator);
			final String xpath = tagname + ":contains('" + text + "')";
			final String script = "$($(\"" + xpath + "\")[0].parentNode.parentNode).children(\"ul\").css(\"display\",\"block\")";
			js.executeScript(script);

			log.info(MessageFormat.format("Mouse over by CSS on Object with XPATH= \"{0}\" is executed", locator));

		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	/**
	 * @param locator
	 * @throws Exception
	 */
	public void mouseLeaveByCSS(final String locator) throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final WebElement obj = driver.elementEventControling.getWebElementDisplay(locator);

			driver.elementEventControling.highlightElement(obj);
			final String tagname = obj.getTagName();
			final String text = driver.elementEventControling.getText(locator);
			final String xpath = tagname + ":contains('" + text + "')";
			final String script = "$($(\"" + xpath + "\")[0].parentNode.parentNode).children(\"ul\").css(\"display\",\"none\")";
			js.executeScript(script);

			log.info(MessageFormat.format("Mouse leave by CSS on Object with XPATH= \"{0}\" is executed", locator));
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	/**
	 * @throws Exception
	 */
	public void openNewWindow() throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "window.open()";
			js.executeScript(script);
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	/**
	 * @throws Exception
	 */
	public void browserMaximize() throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "if (window.screen) {window.moveTo(0, 0);window.resizeTo(window.screen.availWidth, window.screen.availHeight);};";
			js.executeScript(script);

		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	/**
	 * @throws Exception
	 */
	public void scrollDownPage() throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			js.executeScript("window.scrollBy(0,300)");

		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	/**
	 * @param locatorID
	 * @param value
	 * @throws Exception
	 */
	public void setValue(final String locatorID, final String value) throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "document.getElementById('" + locatorID + "').setValue('" + value + "');";
			js.executeScript(script);

			log.info(MessageFormat.format("Value: \"{0}\" is selected on Combobox with ObjectID= \"{1}\"", locatorID, value));
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @param locatorID
	 * @param value
	 * @throws Exception
	 */
	public void setValueByID(final String locatorID, final String value) throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "$('#" + locatorID + "').val('" + value + "');";
			js.executeScript(script);

			log.info(MessageFormat.format("ObjectID= \"{1}\" is set Value:\"{0}\"", locatorID, value));
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @param locatorID
	 * @return string
	 * @throws Exception
	 */
	public String getValueByID(final String locatorID) throws Exception
	{
		String returnObj;
		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "return $('#" + locatorID + "').val();";
			returnObj = (String) js.executeScript(script);
			log.info(MessageFormat.format("ObjectID= \"{0}\" has a value: \"{1}\"", locatorID, returnObj.toString()));
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
		return returnObj;
	}

	/**
	 * @param css
	 * @param value
	 * @throws Exception
	 */
	public void val(final String css, final String value) throws Exception
	{

		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "$('" + css + "').val('" + value + "');";
			js.executeScript(script);

			log.info(MessageFormat.format("Value: \"{0}\" is selected on Combobox with ObjectID= \"{1}\"", css, value));
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @param css
	 * @param locatorType
	 * @throws Exception
	 */
	public void click(final String css, final String locatorType) throws Exception
	{

		try
		{
			if (locatorType.equals(AutoTestConstants.AutoTestConstants.CSS.toString()))
			{
				final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
				final String script = "$('" + css + "').click();";
				js.executeScript(script);
				log.info(MessageFormat.format("Object CSS: \"{0}\" is clicked", css));
			}
			else
			{
				utils.setFalseResult(MessageFormat.format("Wrong Locator Type CSS: \"{0}\"", css));
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

	/**
	 * @param locatorID
	 * @param value
	 * @param executeCommand
	 * @throws Exception
	 */
	public void setValue(final String locatorID, final String value, final String executeCommand) throws Exception
	{

		try
		{
			driver.waitControling.sleep(1000);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "document.getElementById('" + locatorID + "').setValue('" + value + "');";
			js.executeScript(script);
			driver.waitControling.sleep(500);
			final String scriptExe = "document.getElementById('" + locatorID + "')." + executeCommand + "();";
			js.executeScript(scriptExe);
			driver.waitControling.sleep(500);

			log.info(MessageFormat.format("Value: \"{0}\" is selected on Combobox with ObjectID= \"{1}\"", locatorID, value));
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @param locatorID
	 * @return
	 * @throws Exception
	 */
	public String getValue(final String locatorID) throws Exception
	{

		try
		{
			driver.waitControling.sleep(1000);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "return document.getElementById('" + locatorID + "').getValue();";
			final Object returnObj = js.executeScript(script);
			log.info(MessageFormat.format("ObjectID= \"{0}\" has a value: \"{1}\"", locatorID, returnObj.toString()));
			return returnObj.toString();
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @param locatorID
	 * @throws Exception
	 */
	public void onclick(final String locatorID) throws Exception
	{

		try
		{
			driver.waitControling.sleep(1000);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String scriptExe = "document.getElementById('" + locatorID + "').onclick();";
			js.executeScript(scriptExe);

			log.info(MessageFormat.format("ObjectID= \"{0}\" is clicked", locatorID, locatorID));
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @param locatorID
	 * @throws Exception
	 */
	public void click(final String locatorID) throws Exception
	{

		try
		{
			driver.waitControling.sleep(1000);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String scriptExe = "document.getElementById('" + locatorID + "').click();";
			js.executeScript(scriptExe);

			log.info(MessageFormat.format("ObjectID= \"{0}\" is clicked", locatorID, locatorID));
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @param locatorID
	 * @return
	 * @throws Exception
	 */
	public String getTextContent(final String locatorID) throws Exception
	{
		String returnText = "";
		try
		{
			driver.waitControling.sleep(1000);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String scriptExe = "return document.getElementById('" + locatorID + "').textContent";
			returnText = (String) js.executeScript(scriptExe);
			log.info(MessageFormat.format("Text Content of ObjectID= \"{0}\" is: \"{1}\"", locatorID, returnText));
			return returnText;
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}

	}

	/**
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public WebElement getElementByClass(final String className) throws Exception
	{

		try
		{
			driver.waitControling.sleep(1000);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "return document.getElementsByClassName('" + className + "')";
			final WebElement returnObj = (WebElement) js.executeScript(script);
			return returnObj;
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public WebElement getElementByID(final String id) throws Exception
	{

		try
		{
			driver.waitControling.sleep(1000);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "return document.getElementById('" + id + "')";
			final WebElement returnObj = (WebElement) js.executeScript(script);
			return returnObj;
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	public String getAttributeValueViaID(final String locatorID, final String attributeName) throws Exception
	{
		final String returnValue;
		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "return $('#" + locatorID + "').prop('" + attributeName + "');";
			returnValue = (String) js.executeScript(script);
			log.info(MessageFormat.format("Attribute \"{0}\" of ObjectID= \"{1}\" has a value: \"{2}\"", locatorID, attributeName,
					returnValue.toString()));
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
		return returnValue;
	}

	public WebElement getElementByTagAndAttribute(final String tagName, final String attributeName, final String value)
			throws Exception
	{
		try
		{
			driver.waitControling.sleep(1000);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "return $('" + tagName + "[" + attributeName + "=" + value + "]');";
			final WebElement returnObj = (WebElement) js.executeScript(script);
			return returnObj;
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	public boolean isElementChecked(final String locatorID) throws Exception
	{
		try
		{
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "return $('#" + locatorID + "').prop('checked');";
			if ((boolean) js.executeScript(script))
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

	public boolean isElementChecked(final String tagName, final String attributeName, final String value) throws Exception
	{
		try
		{
			final WebElement returnObj = this.getElementByTagAndAttribute(tagName, attributeName, value);
			if (returnObj != null)
			{
				return returnObj.isSelected();
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

	public void click(final String tagName, final String attName, final String attValue) throws Exception
	{
		try
		{
			driver.waitControling.sleep(1000);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			final String script = "return $('" + tagName + "[" + attName + "=" + attValue + "]').click();";
			js.executeScript(script);
			log.info(MessageFormat.format("Clicked element TagName=\"{0}\", AttName=\"{1}\", AttValue=\"{2}\"", tagName, attName,
					attValue));

		}
		catch (

		final Exception e)

		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}

	}

	public boolean execScript(final String script) throws Exception
	{
		try
		{
			driver.waitControling.sleep(1000);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			js.executeScript(script);
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

	public WebElement getParentElement(final WebElement childElement) throws Exception
	{

		try
		{
			driver.waitControling.sleep(500);
			final JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			// get parent element
			final WebElement returnObj = (WebElement) js.executeScript("return arguments[0].parentNode;", childElement);
			return returnObj;
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
