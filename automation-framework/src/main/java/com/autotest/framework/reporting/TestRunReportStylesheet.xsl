<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>												<!-- insert page title -->
				<title>Automation Test Results</title>
			</head>
			<body>
				<h4 align="center">								<!-- insert page name -->
					<font size="6" face="Arial" color="660066">
						<b>
							<u>Automation Test Results</u>
						</b>
					</font>
				</h4>
				<h4>
					<font size="6" face="Arial" color="660066">
						<font size="4.5" face="Arial" color="660000">
							<u>Test Details :</u>
						</font>
					</font>
				</h4>
				<table cellspacing="1" cellpadding="1" border="1">
					<tbody>
						<tr>	<!-- project name output -->
							<td width="150" bgcolor="#153E7E" align="left">
								<font size="2.75" face="Arial" color="#E0E0E0">
									<b>Project Name</b>
								</font>
							</td>
							<td width="250" align="left">
								<font size="2.75" face="Arial" color="#153E7E">
									<b>
										<xsl:for-each select="testRunReport/testrun">
											<xsl:value-of select="projectName" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
						<tr>	<!-- Run Date output -->
							<td width="150" bgcolor="#153E7E" align="left">
								<font size="2.75" face="Arial" color="#E0E0E0">
									<b>Run Date</b>
								</font>
							</td>
							<td width="150" align="left">
								<font size="2.75" face="Arial" color="#153E7E">
									<b>
										<xsl:for-each select="testRunReport/testrun">
											<xsl:value-of select="runDate" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
						<tr>	<!-- Window Name output -->
							<td width="150" bgcolor="#153E7E" align="left">
								<font size="2.75" face="Arial" color="#E0E0E0">
									<b>Window Name</b>
								</font>
							</td>
							<td width="150" align="left">
								<font size="2.75" face="Arial" color="#153E7E">
									<b>
										<xsl:for-each select="testRunReport/testrun">
											<xsl:value-of select="windowName" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
						<tr>	<!-- Window Version output -->
							<td width="150" bgcolor="#153E7E" align="left">
								<font size="2.75" face="Arial" color="#E0E0E0">
									<b>Window Version</b>
								</font>
							</td>
							<td width="150" align="left">
								<font size="2.75" face="Arial" color="#153E7E">
									<b>
										<xsl:for-each select="testRunReport/testrun">
											<xsl:value-of select="windowVersion" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
						<tr>	<!-- Window Version output -->
							<td width="150" bgcolor="#153E7E" align="left">
								<font size="2.75" face="Arial" color="#E0E0E0">
									<b>Browsers Run</b>
								</font>
							</td>
							<td width="150" align="left">
								<font size="2.75" face="Arial" color="#153E7E">
									<b>
										<xsl:for-each select="testRunReport/testrun">
											<xsl:value-of select="browserRun" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
					</tbody>
				</table>

				<h4>
					<font size="6" face="AriaL" color="660066">
						<font size="4.5" face="Arial" color="660000">
							<font size="4.5" face="Arial" color="660000">
								<u>Report :</u>
							</font>
						</font>
					</font>
				</h4>
				<!-- Report Detail based on Browser Environment -->

				<table width="100%" cellspacing="1" cellpadding="1" border="1">
					<tbody>
						<tr>
							<td width="25%" bgcolor="#153E7E" align="center">
								<font size="2" face="Arial" color="#E0E0E0">
									<b>BROWSER NAME</b>
								</font>
							</td>
							<td width="15%" bgcolor="#153E7E" align="center">
								<font size="2" face="Arial" color="#E0E0E0">
									<b>VERSION</b>
								</font>
							</td>
							<td width="20%" bgcolor="#153E7E" align="center">
								<font size="2" face="Arial" color="#E0E0E0">
									<b>PASS SCENARIOS</b>
								</font>
							</td>
							<td width="20%" bgcolor="#153E7E" align="center">
								<font size="2" face="Arial" color="#E0E0E0">
									<b>FAIL SCENARIOS</b>
								</font>
							</td>
							<td width="20%" bgcolor="#153E7E" align="center">
								<font size="2" face="Arial" color="#E0E0E0">
									<b>SKIP SCENARIOS</b>
								</font>
							</td>
						</tr>
						<xsl:for-each select="testRunReport/browserEnvTest">
							<tr>
								<td width="25%" align="center">
									<font size="2" face="Arial" color="#153E7E">
										<b>

											<a>
												<xsl:attribute name="href">
    													<xsl:value-of select="linkResult" />
  													</xsl:attribute>
												<xsl:value-of select="browserName" />
											</a>
										</b>
									</font>
								</td>
								<td width="15%" align="center">
									<font size="2" face="Arial" color="#153E7E">
										<b>
											<xsl:value-of select="browserVersion" />
										</b>
									</font>
								</td>
								<td width="20%" bgcolor="green" align="center">
									<font size="2" face="Arial" color="153E7E">
										<b>
											<xsl:value-of select="passScenarios" />
										</b>
									</font>
								</td>
								<td width="20%" bgcolor="red" align="center">
									<font size="2" face="Arial" color="153E7E">
										<b>
											<xsl:value-of select="failScenarios" />
										</b>
									</font>
								</td>
								<td width="20%" bgcolor="yellow" align="center">
									<font size="2" face="Arial" color="153E7E">
										<b>
											<xsl:value-of select="skipScenarios" />
										</b>
									</font>
								</td>
							</tr>
						</xsl:for-each>
					</tbody>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>