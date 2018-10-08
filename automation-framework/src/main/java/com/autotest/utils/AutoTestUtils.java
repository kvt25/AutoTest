package com.autotest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.autotest.configure.Environment;
import com.autotest.configure.AutoTestConstants;

import jxl.Sheet;
import jxl.Workbook;


@SuppressWarnings("javadoc")
public class AutoTestUtils extends Environment
{
	public static String getDatetimeStamp()
	{
		final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MMM-yyyy");
		final Date date = new Date();
		return dateFormat.format(date);
	}


	/*public void setTrueResult(final String actual) throws Exception
	{
		try
		{
			AutoTestConstants.result = new Object[2];
			AutoTestConstants.result[0] = "true";
			AutoTestConstants.result[1] = actual;
			log.info("PASSED: " + actual);
		}
		catch (final Exception e)
		{
			log.error("COULD NOT SET TRUE RESULT FOR TESTCASE");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	public void setFalseResult(final String reason) throws Exception
	{
		try
		{
			AutoTestConstants.result = new Object[2];
			AutoTestConstants.result[0] = "false";
			AutoTestConstants.result[1] = reason;
			log.error("FAILED: " + reason);
		}
		catch (final Exception e)
		{
			log.error("COULD NOT SET FAIL RESULT FOR TESTCASE");
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}*/


	public String getXmlNodeValue(final String category, final String note) throws Exception
	{
		String result = "";
		try
		{

			final File file = new File(AutoTestConstants.FW_CONFIG_PATH);
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			final NodeList nodeLst = doc.getElementsByTagName(category);

			for (int s = 0; s < nodeLst.getLength(); s++)
			{
				final Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE)
				{

					final Element fstElmnt = (Element) fstNode;
					final NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(note);
					final Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					final NodeList fstNm = fstNmElmnt.getChildNodes();
					result = fstNm.item(0).toString();
				}
			}
			if (result != "")
			{
				result = result.substring(8, result.length() - 1);
			}

		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
		return result;
	}



	public HashMap<String, String> mapIndicator(final String sheetName) throws Exception
	{

		final HashMap<String, String> indicatorMap = new HashMap<String, String>();
		try
		{
			final Workbook workbook = Workbook.getWorkbook(new File(AutoTestConstants.EXCEL_CONFIG));
			final Sheet sheet = workbook.getSheet(sheetName);

			for (int i = 1; i < sheet.getRows(); i++)
			{
				final String objectName = sheet.getCell(0, i).getContents();
				final String indicator = sheet.getCell(1, i).getContents();
				indicatorMap.put(objectName, new String(indicator));
			}
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
		return indicatorMap;
	}


	public String parseToUpperCase(final String input) throws Exception
	{
		try
		{
			String result = "";
			result = input.toUpperCase();
			log.info(MessageFormat.format("String = \"{0}\" is parse to UpperCase = \"{1}\"", input, result));
			return result;
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	public String parseToLowerCase(final String input) throws Exception
	{
		try
		{
			String result = "";
			result = input.toLowerCase();
			log.info(MessageFormat.format("String = \"{0}\" is parse to LowerCase = \"{1}\"", input, result));
			return result;
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}


	public boolean compareStringIgnoreCase(final String firstString, final String secondString) throws Exception
	{
		try
		{
			final int index = firstString.compareToIgnoreCase(secondString);
			if (index == 0)
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


	public boolean compareString(final String firstString, final String secondString) throws Exception
	{
		try
		{
			final int index = firstString.compareTo(secondString);
			if (index == 0)
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

	public int parseStringToInt(final String input) throws Exception
	{
		try
		{
			int parseRes = 0;
			parseRes = Integer.parseInt(input);
			log.info(MessageFormat.format("String = \"{0}\" is parse to integer = \"{1}\"", input, parseRes));
			return parseRes;
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	public double calculateStepExecutionTime(final String startTime, final String endTime) throws Exception
	{
		try
		{
			final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			final Date startDate = format.parse(startTime);
			final Date endDate = format.parse(endTime);
			double executionTime = endDate.getTime() - startDate.getTime();
			executionTime = executionTime / 1000.0;
			return executionTime;
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}

	/*public void createResultFolder() throws Exception
	{
		try
		{
			String projectPath = new File("").getAbsolutePath();
			projectPath = projectPath.replace("\\", "/");
			final String frameworkPath = projectPath.substring(0, projectPath.lastIndexOf("/"));

			final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MMM-yyyy");
			// get current date time with Date()
			final Date date = new Date();

			// Configuration other file
			final String preoutputFile = AutoTestConstants.EXCEL_FILENAME.substring(AutoTestConstants.EXCEL_FILENAME.indexOf("_") + 1,
					AutoTestConstants.EXCEL_FILENAME.length() - 4) + "_" + dateFormat.format(date);

			AutoTestConstants.resultFolder = frameworkPath + "/" + AutoTestConstants.RESULT_FOLDER + "/"
					+ preoutputFile.replaceAll(":", "-").replaceAll(" ", "_") + "/";
			new File(AutoTestConstants.resultFolder).mkdir();
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}*/

	public void addFolder(final ZipOutputStream zos, final String folderName, final String baseFolderName) throws Exception
	{
		try
		{
			final File f = new File(folderName);
			if (f.exists())
			{
				if (f.isDirectory())
				{
					//For pointing out missing entry for empty folder
					if (!folderName.equalsIgnoreCase(baseFolderName))
					{
						final String entryName = folderName.substring(baseFolderName.length() + 1, folderName.length())
								+ File.separatorChar;
						System.out.println("Adding folder entry: " + entryName);
						log.info(StackTraceInfo.getCurrentClassName());
						log.info(log.tab + "Adding folder entry: " + entryName);
						final ZipEntry ze = new ZipEntry(entryName);
						zos.putNextEntry(ze);
					}
					final File f2[] = f.listFiles();
					for (int i = 0; i < f2.length; i++)
					{
						addFolder(zos, f2[i].getAbsolutePath(), baseFolderName);
					}
				}
				else
				{
					//add file
					//extract the relative name for entry purpose
					final String entryName = folderName.substring(baseFolderName.length() + 1, folderName.length());
					System.out.print("Adding file entry: " + entryName + "...");
					log.info(StackTraceInfo.getCurrentClassName());
					log.info(log.tab + "Adding file entry: " + entryName + "...");
					final ZipEntry ze = new ZipEntry(entryName);
					zos.putNextEntry(ze);
					final FileInputStream in = new FileInputStream(folderName);
					int len;
					final byte buffer[] = new byte[1024];
					while ((len = in.read(buffer)) > 0)
					{
						zos.write(buffer, 0, len);
					}
					in.close();
					zos.closeEntry();
					System.out.println("OK!");
					log.info(log.tab + "Adding file entry: " + entryName + "OK!");

				}
			}
			else
			{
				System.out.println("File or directory not found " + folderName);
				log.info(StackTraceInfo.getCurrentClassName());
				log.info(log.tab + "File or directory not found" + folderName);
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


	/*public String zipResultFolder() throws Exception
	{

		String sourceFolderPath = "";
		String sourceFolderName = "";
		String outputFilePath = "";
		String outputFileName = "";

		try
		{
			// get result folder name from result path
			if (!AutoTestConstants.resultFolder.equals(""))
			{
				sourceFolderPath = AutoTestConstants.resultFolder.substring(0, AutoTestConstants.resultFolder.length() - 1);
				final String[] arrSourceFolderPath = sourceFolderPath.split("/");
				sourceFolderName = arrSourceFolderPath[arrSourceFolderPath.length - 1];
				outputFileName = sourceFolderName + ".zip";
				outputFilePath = sourceFolderPath + ".zip";

				final FileOutputStream fos = new FileOutputStream(outputFilePath);
				final ZipOutputStream zos = new ZipOutputStream(fos);
				//level - the compression level (0-9)
				zos.setLevel(9);

				System.out.println("Begin to compress folder : " + sourceFolderName + " to " + outputFileName);
				log.info(StackTraceInfo.getCurrentClassName());
				log.info(log.tab + "Begin to compress folder : " + sourceFolderName + " to " + outputFileName);
				addFolder(zos, sourceFolderPath, sourceFolderPath);

				zos.close();
				System.out.println("Zip Result folder is successfully!");
				log.info(log.tab + "Zip Result folder is successfully!");
			}
			else
			{
				log.error(StackTraceInfo.getCurrentClassName());
				log.error(StackTraceInfo.getCurrentFileName());
				log.error(StackTraceInfo.getCurrentMethodName());
				log.error(log.tab + "Result Folder is not existed. Please check framework configuration");
				throw new Exception();
			}
			return outputFilePath;
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}

	}*/

	/*
	public void sendEmailReport() throws Exception
	{

		// SMTP server information
		String sendingStatus = "";

		// get email information setting from Property file
		try
		{

			final Properties prop = new Properties();
			InputStream input = null;

			try
			{

				//input = new FileInputStream("src/main/java/com/sai/framework/properties/emailsetting.properties");
				input = new FileInputStream("src/main/java/com/cerebos/properties/emailsetting.properties");
				// load a properties file
				prop.load(input);
				// get sendingStatus
				sendingStatus = prop.getProperty("reportsending");
				// get the property value


			}
			catch (final IOException ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				if (input != null)
				{
					try
					{
						input.close();
					}
					catch (final IOException e)
					{
						e.printStackTrace();
					}
				}
			}

			if (sendingStatus.equals("YES"))
			{
				// zip result folder
				final String attachedReportPath = utils.zipResultFolder();
				final String host = prop.getProperty("host");
				final String port = prop.getProperty("port");
				final String mailFrom = prop.getProperty("mailFrom");
				final String password = prop.getProperty("password");
				final String mailTo1 = prop.getProperty("mailTo1");
				final String mailTo2 = prop.getProperty("mailTo2");
				final String mailTo3 = prop.getProperty("mailTo3");
				String subject = prop.getProperty("subject");
				subject = subject + " " + getDatetimeStamp();

				// message contains HTML markups
				String message = "<i>Hello!</i><br>";
				message += "<b>Please find executed result on attached file!</b><br>";
				message += "<font color=red>Dat</font>";

				// attachments
				final String[] attachFiles = new String[1];
				attachFiles[0] = attachedReportPath;

				try
				{
					sendEmailWithAttachments(host, port, mailFrom, password, mailTo1, subject, message, attachFiles);
					System.out.println("Email sent.");
				}
				catch (final Exception ex)
				{
					System.out.println("Could not send email.");
					ex.printStackTrace();
				}
			}
			else
			{
				log.info("Email reporting is disabled!");
			}
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
			throw new Exception();
		}
	}*/


	public static void sendEmailWithAttachments(final String host, final String port, final String userName, final String password,
			final String toAddress, final String subject, final String message, final String[] attachFiles)
					throws AddressException, MessagingException
	{
		// sets SMTP server properties
		final Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		final Authenticator auth = new Authenticator()
		{
			@Override
			public PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(userName, password);
			}
		};
		final Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		final Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		final InternetAddress[] toAddresses =
		{ new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		final MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		final Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		if (attachFiles != null && attachFiles.length > 0)
		{
			for (final String filePath : attachFiles)
			{
				final MimeBodyPart attachPart = new MimeBodyPart();

				try
				{
					attachPart.attachFile(filePath);
				}
				catch (final IOException ex)
				{
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}
		}

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);
	}


	public void copyFolder(final File src, final File dest) throws IOException
	{

		if (src.isDirectory())
		{

			//if directory not exists, create it
			if (!dest.exists())
			{
				dest.mkdir();
				log.info("Directory copied from " + src + "  to " + dest);
			}

			//list all the directory contents
			final String files[] = src.list();

			for (final String file : files)
			{
				//construct the src and dest file structure
				final File srcFile = new File(src, file);
				final File destFile = new File(dest, file);
				//recursive copy
				copyFolder(srcFile, destFile);
			}

		}
		else
		{
			//if file, then copy it
			//Use bytes stream to support all file types
			final InputStream in = new FileInputStream(src);
			final OutputStream out = new FileOutputStream(dest);

			final byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes
			while ((length = in.read(buffer)) > 0)
			{
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			log.info("File copied from " + src + " to " + dest);
		}
	}


	public void copyJavaScriptFoldertoResult(final String copyFolderPath, final String desFolderPath) throws Exception
	{
		final File srcFolder = new File(copyFolderPath);
		final File destFolder = new File(desFolderPath);

		//make sure source exists
		if (!srcFolder.exists())
		{
			log.error("JavaScript folder does not exist.");
			//just exit
			System.exit(0);

		}
		else
		{

			try
			{
				copyFolder(srcFolder, destFolder);
			}
			catch (final IOException e)
			{
				e.printStackTrace();
				//error, just exit
				System.exit(0);
			}
		}
		log.info("Copying JavaScript folder to Result folder is done");
	}

	// compare 2 arraylists
	public boolean equalLists(final ArrayList<String> a, final ArrayList<String> b)
	{
		// Check for sizes and nulls
		if ((a.size() != b.size()) || (a == null && b != null) || (a != null && b == null))
		{
			return false;
		}

		if (a == null && b == null)
		{
			return true;
		}

		// Sort and compare the two lists
		Collections.sort(a);
		Collections.sort(b);
		return a.equals(b);
	}

	public String getPropertyKey(final String propertyPath, final String propertyValue) throws Exception
	{
		String returnKey = "";
		try
		{
			final Properties prop = new Properties();
			InputStream input = null;
			try
			{
				input = new FileInputStream(propertyPath);
				// load a properties file
				prop.load(input);
			}
			catch (final FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}

			// get all keys
			final Set<Object> keys = prop.keySet();
			for (final Object k : keys)
			{
				final String key = (String) k;
				final String value = prop.getProperty(key);
				if (value.equals(propertyValue))
				{
					returnKey = key;
					break;
				}
			}
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
		}
		return returnKey;
	}

	public String getPropertyValue(final String propertyPath, final String propertyKey) throws Exception
	{
		String returnValue = "";
		try
		{
			final Properties prop = new Properties();
			InputStream input = null;
			try
			{
				input = new FileInputStream(propertyPath);
				// load a properties file
				prop.load(input);
			}
			catch (final FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}

			// get all keys
			final Set<Object> keys = prop.keySet();
			for (final Object k : keys)
			{
				final String key = (String) k;
				if (key.equals(propertyKey))
				{
					returnValue = prop.getProperty(key);
					break;
				}
			}
		}
		catch (final Exception e)
		{
			log.error(StackTraceInfo.getCurrentClassName());
			log.error(StackTraceInfo.getCurrentFileName());
			log.error(StackTraceInfo.getCurrentMethodName() + log.tab + e.getMessage());
		}
		return returnValue;
	}
}
