package com.autotest.framework.reporting;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

public class SchemaGeneration
{
    public static void createTestScenarioReport() throws Exception{
        JAXBContext jc = JAXBContext.newInstance(ScenarioReport.class);
        jc.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
                StreamResult result = new StreamResult(System.out);
                result.setSystemId(suggestedFileName);
                return result;
            }
        });
    }

    public static void createTestRunReport() throws Exception{
        JAXBContext jc = JAXBContext.newInstance(TestRunReport.class);
        jc.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
                StreamResult result = new StreamResult(System.out);
                result.setSystemId(suggestedFileName);
                return result;
            }
        });
    }
}
