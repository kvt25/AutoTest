<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="scenarioReport/testcase">
		<xsl:for-each select="..">
			<p>
				<xsl:value-of select="scenarioID" />
			</p>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="/">
		<html>
			<head>
				<script type="text/javascript" src="js/prettify.js"></script>
				<script type="text/javascript"
					src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
				<script type="text/javascript" src="js/simple-expand.min.js"></script>
				<script type="text/javascript">
					$(function () {
					$('.expander').simpleexpand();
					prettyPrint();
					});
				</script>
				<script type="text/javascript">
					function toggleMe(a){
					var
					e=document.getElementById(a);
					if(!e)return true;
					if(e.style.display=="none"){
					e.style.display="block"
					}
					else{
					e.style.display="none"
					}
					return true;
					}
				</script>
				<title>Automation Test Results</title>			<!-- insert page title -->
			</head>
			<body>
				<!-- <a class="expander" href="#">click me</a> <div class="content" style="display:none"> 
					content to hide. </div> -->
				<h4 align="center">
					<font size="6" face="Arial" color="660066">
						<b>
							<u>Automation Test Results</u>		<!-- insert page name -->
						</b>
					</font>
				</h4>

				<h4>
					<font size="6" face="Arial" color="660066">
						<font size="4.5" face="Arial" color="660000">
							<a href="masterReport.html">Go to Master Report</a>
						</font>
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
										<xsl:for-each select="scenarioReport">
											<xsl:value-of select="projectName" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
						<tr>	<!-- Browser Name output -->
							<td width="150" bgcolor="#153E7E" align="left">
								<font size="2.75" face="Arial" color="#E0E0E0">
									<b>Browser Name</b>
								</font>
							</td>
							<td width="150" align="left">
								<font size="2.75" face="Arial" color="#153E7E">
									<b>
										<xsl:for-each
											select="scenarioReport/browserEnvTest">
											<xsl:value-of select="browserName" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
						<tr>	<!-- Browser Version output -->
							<td width="150" bgcolor="#153E7E" align="left">
								<font size="2.75" face="Arial" color="#E0E0E0">
									<b>Browser Version</b>
								</font>
							</td>
							<td width="150" align="left">
								<font size="2.75" face="Arial" color="#153E7E">
									<b>
										<xsl:for-each
											select="scenarioReport/browserEnvTest">
											<xsl:value-of select="browserVersion" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
						<tr>	<!-- Pass Scenarios output -->
							<td width="150" bgcolor="#153E7E" align="left">
								<font size="2.75" face="Arial" color="#E0E0E0">
									<b>Passed</b>
								</font>
							</td>
							<td width="150" bgcolor="green" align="left">
								<font size="2.75" face="Arial" color="#153E7E">
									<b>
										<xsl:for-each
											select="scenarioReport/browserEnvTest">
											<xsl:value-of select="passScenarios" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
						<tr>	<!-- Fail Scenarios output -->
							<td width="150" bgcolor="#153E7E" align="left">
								<font size="2.75" face="Arial" color="#E0E0E0">
									<b>Failed</b>
								</font>
							</td>
							<td width="150" bgcolor="red" align="left">
								<font size="2.75" face="Arial" color="#153E7E">
									<b>
										<xsl:for-each
											select="scenarioReport/browserEnvTest">
											<xsl:value-of select="failScenarios" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
						<tr>	<!-- Skip Scenarios output -->
							<td width="150" bgcolor="#153E7E" align="left">
								<font size="2.75" face="Arial" color="#E0E0E0">
									<b>Skiped</b>
								</font>
							</td>
							<td width="150" bgcolor="yellow" align="left">
								<font size="2.75" face="Arial" color="#153E7E">
									<b>
										<xsl:for-each
											select="scenarioReport/browserEnvTest">
											<xsl:value-of select="skipScenarios" />
										</xsl:for-each>
									</b>
								</font>
							</td>
						</tr>
					</tbody>
				</table>

				<!-- Report Detail based on Browser Environment -->
				<h4>
					<font size="6" face="AriaL" color="660066">
						<font size="4.5" face="Arial" color="660000">
							<font size="4.5" face="Arial" color="660000">
								<u>Report :</u>
							</font>
						</font>
					</font>
				</h4>
				<table width="100%" cellspacing="1" cellpadding="1" border="1">
					<tbody>
						<tr>
							<td width="20%" bgcolor="#153E7E" align="center">
								<font size="2" face="Arial" color="#E0E0E0">
									<b>SCENARIO NAME</b>
								</font>
							</td>
							<td width="30%" bgcolor="#153E7E" align="center">
								<font size="2" face="Arial" color="#E0E0E0">
									<b>TEST CASE LIST</b>
								</font>
							</td>
							<td width="30%" bgcolor="#153E7E" align="center">
								<font size="2" face="Arial" color="#E0E0E0">
									<b>DESCRIPTION</b>
								</font>
							</td>
							<td width="10%" bgcolor="#153E7E" align="center">
								<font size="2" face="Arial" color="#E0E0E0">
									<b>EXECUTION RESULT</b>
								</font>
							</td>
						</tr>

						<xsl:for-each select="scenarioReport/scenario">
							<tr>
								<td width="20%" align="center">
									<font size="2" face="Arial" color="#153E7E">
										<b>
											<xsl:value-of select="name" />
										</b>
									</font>
								</td>
								<td width="30%" align="center">
									<font size="2" face="Arial" color="#153E7E">
										<b>
											<xsl:value-of select="testCaseList" />
										</b>
									</font>
								</td>
								<td width="30%" align="center">
									<font size="2" face="Arial" color="#153E7E">
										<b>
											<xsl:value-of select="description" />
										</b>
									</font>
								</td>

								<xsl:choose>
									<xsl:when test="result = 'PASS'">
										<td width="10%" bgcolor="green" align="center">
											<font size="2" face="Arial" color="153E7E">
												<b>
													<xsl:value-of select="result" />
												</b>
											</font>
										</td>
									</xsl:when>
									<xsl:when test="result = 'FAIL'">
										<td width="10%" bgcolor="red" align="center">
											<font size="2" face="Arial" color="153E7E">
												<b>
													<xsl:value-of select="result" />
												</b>
											</font>
										</td>
									</xsl:when>
									<xsl:when test="result = 'SKIP'">
										<td width="10%" bgcolor="yellow" align="center">
											<font size="2" face="Arial" color="153E7E">
												<b>
													<xsl:value-of select="result" />
												</b>
											</font>
										</td>
									</xsl:when>
								</xsl:choose>
							</tr>
						</xsl:for-each>
					</tbody>
				</table>

				<!-- Report Detail based on Test Case -->
				<h4>
					<font size="6" face="AriaL" color="660066">
						<font size="4.5" face="Arial" color="660000">
							<font size="4.5" face="Arial" color="660000">
								<u>Report Detail Test Cases:</u>
							</font>
						</font>
					</font>
				</h4>

				<table class="TestCase table" width="100%" cellspacing="1"
					cellpadding="1" border="1">
					<tbody>
						<tr>	<!-- Table Header -->
							<td width="10" height="20" bgcolor="#153E7E" align="center">
								<font size="0.5" face="Arial" color="#E0E0E0">
									<b>SCENARIO REF</b>
								</font>
							</td>
							<td width="10" bgcolor="#153E7E" align="center">
								<font size="0.5" face="Arial" color="#E0E0E0">
									<b>TESTCASE ID</b>
								</font>
							</td>
							<td width="10" bgcolor="#153E7E" align="center">
								<font size="0.5" face="Arial" color="#E0E0E0">
									<b>TESTCASE NAME</b>
								</font>
							</td>
							<td width="10" bgcolor="#153E7E" align="center">
								<font size="0.5" face="Arial" color="#E0E0E0">
									<b>RUN NUMBER</b>
								</font>
							</td>
							<td width="10" bgcolor="#153E7E" align="center">
								<font size="0.5" face="Arial" color="#E0E0E0">
									<b>TESTCASE STATUS</b>
								</font>
							</td>
							<td width="10" bgcolor="#153E7E" align="center">
								<font size="0.5" face="Arial" color="#E0E0E0">
									<b>STEP AMOUNT</b>
								</font>
							</td>
						</tr>

						<xsl:for-each select="scenarioReport/testcase">
							<!-- Declare variable for set id of table -->
							<xsl:variable name="tableCounting">
								<xsl:number count="testCase" format="1" />
								<xsl:number level="any" />
							</xsl:variable>

							<tr onclick="return toggleMe('{$tableCounting}')">
								<td width="10" bgcolor="#FFFFFF" align="center">
									<font size="0.5" face="Arial">
										<b>
											<xsl:value-of select="scenarioID" />
										</b>
									</font>


								</td>
								<td width="10" bgcolor="#FFFFFF" align="center">
									<font size="0.5" face="Arial">
										<b>
											<xsl:value-of select="ID" />
										</b>
									</font>
								</td>
								<td width="10" bgcolor="#FFFFFF" align="center">
									<font size="0.5" face="Arial">
										<b>
											<xsl:value-of select="name" />
										</b>
									</font>
								</td>
								<td width="10" bgcolor="#FFFFFF" align="center">
									<font size="0.5" face="Arial">
										<b>
											<xsl:value-of select="runNumber" />
										</b>
									</font>
								</td>
								<xsl:choose>
									<xsl:when test="result = 'PASS'">
										<td width="10" bgcolor="green" align="center">
											<font size="0.5" face="Arial" color="153E7E">
												<b>
													<xsl:value-of select="result" />
												</b>
											</font>
										</td>
									</xsl:when>
									<xsl:when test="result = 'FAIL'">
										<td width="10" bgcolor="red" align="center">
											<font size="2" face="Arial" color="153E7E">
												<b>
													<xsl:value-of select="result" />
												</b>
											</font>
										</td>
									</xsl:when>
									<xsl:when test="result = 'SKIP'">
										<td width="10" bgcolor="yellow" align="center">
											<font size="2" face="Arial" color="153E7E">
												<b>
													<xsl:value-of select="result" />
												</b>
											</font>
										</td>
									</xsl:when>
								</xsl:choose>
								<td width="10" bgcolor="#FFFFFF" align="center">
									<font size="0.5" face="Arial">
										<b>
											<xsl:value-of select="stepAmount" />
										</b>
									</font>
								</td>
							</tr>

							<!-- for loop to find step depend on test case -->
							<tr>
								<td colspan="6">
									<table id="{$tableCounting}" class="TestStep table"
										style="display:none" width="100%" cellspacing="1"
										cellpadding="1" border="1">
										<tbody>
											<tr>	<!-- Table Header -->
												<td width="10" bgcolor="#0080FF" align="center">
													<font size="0.5" face="Arial" color="#E0E0E0">
														<b>STEP NUMBER</b>
													</font>
												</td>
												<td width="10" bgcolor="#0080FF" align="center">
													<font size="0.5" face="Arial" color="#E0E0E0">
														<b>STEP DESCRIPTION</b>
													</font>
												</td>
												<td width="10" bgcolor="#0080FF" align="center">
													<font size="0.5" face="Arial" color="#E0E0E0">
														<b>AUTOMATION STEPNAME</b>
													</font>
												</td>
												<td width="10" bgcolor="#0080FF" align="center">
													<font size="0.5" face="Arial" color="#E0E0E0">
														<b>TEST DATA</b>
													</font>
												</td>
												<td width="10" bgcolor="#0080FF" align="center">
													<font size="0.5" face="Arial" color="#E0E0E0">
														<b>EXPECTED RESULT</b>
													</font>
												</td>
												<td width="10" bgcolor="#0080FF" align="center">
													<font size="0.5" face="Arial" color="#E0E0E0">
														<b>OBTAINED RESULT</b>
													</font>
												</td>
												<td width="10" bgcolor="#0080FF" align="center">
													<font size="0.5" face="Arial" color="#E0E0E0">
														<b>STEP STATUS</b>
													</font>
												</td>
												<td width="10" bgcolor="#0080FF" align="center">
													<font size="0.5" face="Arial" color="#E0E0E0">
														<b>START TIME</b>
													</font>
												</td>
												<td width="10" bgcolor="#0080FF" align="center">
													<font size="0.5" face="Arial" color="#E0E0E0">
														<b>END TIME</b>
													</font>
												</td>
											</tr>

											<xsl:for-each select="testStep">
												<tr>
													<td width="10" bgcolor="#FFFFFF" align="center">
														<font size="0.5" face="Arial">
															<b>
																<xsl:value-of select="stepNumber" />
															</b>
														</font>
													</td>
													<td width="10" bgcolor="#FFFFFF" align="center">
														<font size="0.5" face="Arial">
															<b>
																<xsl:value-of select="stepDescription" />
															</b>
														</font>
													</td>
													<td width="10" bgcolor="#FFFFFF" align="center">
														<font size="0.5" face="Arial">
															<b>
																<xsl:value-of select="automationName" />
															</b>
														</font>
													</td>
													<td width="10" bgcolor="#FFFFFF" align="center">
														<font size="0.5" face="Arial">
															<b>
																<xsl:value-of select="testData" />
															</b>
														</font>
													</td>
													<td width="10" bgcolor="#FFFFFF" align="center">
														<font size="0.5" face="Arial">
															<b>
																<xsl:value-of select="expectedResult" />
															</b>
														</font>
													</td>
													<td width="10" bgcolor="#FFFFFF" align="center">
														<font size="0.5" face="Arial">
															<b>
																<xsl:value-of select="obtainedResult" />
															</b>
														</font>
													</td>

													<xsl:choose>
														<xsl:when test="result = 'PASS'">
															<td width="10%" bgcolor="green" align="center">
																<font size="2" face="Arial" color="153E7E">
																	<b>
																		<xsl:value-of select="result" />
																	</b>
																</font>
															</td>
														</xsl:when>
														<!-- <xsl:when test="result = 'FAIL'"> <td width="10%" bgcolor="red" 
															align="center"> <font size="2" face="Arial" color="153E7E"> <b> <xsl:value-of 
															select="result" /> </b> </font> </td> </xsl:when> -->
														<xsl:when test="result = 'FAIL'">
															<td width="10%" bgcolor="red" align="center">
																<font size="2" face="Arial" color="153E7E">
																	<a>
																		<xsl:attribute name="href">
    													<xsl:value-of select="screenshot" />
  													</xsl:attribute>
																		<xsl:value-of select="result" />
																	</a>
																</font>
															</td>
														</xsl:when>

														<xsl:when test="result = 'SKIP'">
															<td width="10%" bgcolor="yellow" align="center">
																<font size="2" face="Arial" color="153E7E">
																	<b>
																		<xsl:value-of select="result" />
																	</b>
																</font>
															</td>
														</xsl:when>
													</xsl:choose>
													<td width="10" bgcolor="#FFFFFF" align="center">
														<font size="0.5" face="Arial">
															<b>
																<xsl:value-of select="startTimeExec" />
															</b>
														</font>
													</td>
													<td width="10" bgcolor="#FFFFFF" align="center">
														<font size="0.5" face="Arial">
															<b>
																<xsl:value-of select="endTimeExec" />
															</b>
														</font>
													</td>
												</tr>
											</xsl:for-each>
										</tbody>
									</table>
								</td>
							</tr>

						</xsl:for-each>
					</tbody>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>