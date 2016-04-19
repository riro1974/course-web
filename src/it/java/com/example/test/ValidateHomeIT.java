package com.example.test;
import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ValidateHomeIT {
	@Test
	public void testHomePageIT() throws Exception {
	    try (final WebClient webClient = new WebClient()) {
	        final HtmlPage page = webClient.getPage("http://localhost:8080/course-web/courseDisplay.jsp");
	        Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
	
//	        final String pageAsXml = page.asXml();
//	        Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
	
//	        final String pageAsText = page.asText();
//	        Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
	    }
	}
}