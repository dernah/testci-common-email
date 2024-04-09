package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {

	private static final String[] TEST_EMAILS = {"nader@gmail.com", "a.b@c.org", "something@somewhere.here.bd"};
	
	private EmailConcrete email;
	
	@Before
	public void setUpEmailTest() throws Exception{
		email = new EmailConcrete();
	}
	
	@After
	public void tearDownEmailTest() throws Exception{
		
	}
	@Test
	public void testAddBcc() throws Exception{
		email.addBcc(TEST_EMAILS); // add bccs
		
		assertEquals(3, email.getBccAddresses().size()); 
	}
	
	@Test
	public void testAddCc() throws Exception{
		email.addCc("Test@email.com"); // add one cc since we are testing one email
		
		assertEquals(1, email.getCcAddresses().size());
	}
	
	@Test
	public void testAddHeader() throws Exception{
		String name = "Header Name";
		String value = "Header Value";
		email.addHeader(name,  value); // test header with name and value
		
		Map<String, String> headers = email.getHeaders();
		
		assertEquals(value, headers.get(name));
		
		/*String firstName = "First Header";
		String firstValue = "First Value";
		email.addHeader(firstName, firstValue);
		
		Map<String, String> newHeaders = new HashMap<String, String>();
		String newName = "New Header";
		String newValue = "New Value";
		newHeaders.put(newName, newValue);
		email.setHeaders(newHeaders);
		
		Map<String, String> headers = email.getHeaders();
		assertEquals(newValue, headers.get(newName));
		*/
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAddHeaderWithEmptyName() {
	    email.addHeader("", "Value"); // Header when name is empty
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddHeaderWithNullName() {
	    email.addHeader(null, "Value"); // Header when name is null
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddHeaderWithEmptyValue() {
	    email.addHeader("name", ""); // Header when value is empty
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddHeaderWithNullValue() {
	    email.addHeader("name", null); // Header when value is null
	}
	
	@Test
	public void testAddReply() throws Exception{
		String testEmail = "hello@there.com";
		email.addReplyTo(testEmail);
		
		assertEquals(testEmail, email.getReplyToAddresses().get(0).getAddress()); //Test add reply with only email
	}
	@Test
	public void testAddReplyWithName() throws Exception{
		String replyEmail = "helloagain@there.com";
		String testName = "nadersTest";
		email.addReplyTo(replyEmail, testName); // Test add reply with email and name

		assertEquals(replyEmail, email.getReplyToAddresses().get(0).getAddress());
		assertEquals(testName, email.getReplyToAddresses().get(0).getPersonal());
	}
	
	@Test
	public void testBuildMimeMessage() throws Exception {
	    // setup of each variable
	    email.setHostName("host.example.com");
	    email.setSmtpPort(222);
	    email.setFrom("sender@example.com");
	    email.addTo("recipient@example.com");
	    email.setSubject("Example Subject");
	    email.setMsg("Example Message");
	    email.setAuthentication("user", "password");
	    
	    // Build the message
	    email.buildMimeMessage();
	    
	    assertEquals("Example Subject", email.getSubject());
	    assertNotNull("Expected non-null", email.getMimeMessage()); // Make comparisons


		// I still need to fix this function to cover all JUnit test cases.
	    
	}
	
	@Test
	public void testGetHostName() throws Exception{
		String expectedHostName = "something.here.com";
		email.setHostName(expectedHostName);
		assertEquals(expectedHostName, email.getHostName()); // testing getting host names
	}   
	@Test
	public void testGetHostNameWithNull() throws Exception{
		assertNull("Host name is null", email.getHostName()); //testing null host names
	}
	
	@Test
	public void testGetMailSession() throws Exception{
		// using example variables
		email.setHostName("smtp.example.com");
		email.setSmtpPort(123);
		email.setAuthentication("user@authentication.com", "empty");
		email.setSSLOnConnect(true);
		
		Session session = email.getMailSession(); // setting the session
		
		assertEquals(session, email.getMailSession());
		assertNotNull("Session is not null", session);
		
	}
	
	@Test
	public void testGetSentDate() throws Exception{
		LocalDate localDate = LocalDate.of(2024, 3, 25);
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); // establish a date
		email.setSentDate(date);
		
		assertEquals(date, email.getSentDate()); // compare the date to the functionality.
	}
	
	@Test
	public void testGetSocketConnectionTimeout() throws Exception{
		email.setSocketConnectionTimeout(123);
		
		//int socketTimeout = email.getSocketConnectionTimeout();
		
		assertEquals(123, email.getSocketConnectionTimeout());
		
	}
	@Test 
	public void testSetFrom() throws Exception{
		String testEmail = "nader@gmail.com";
		
		email.setFrom(testEmail);

		assertEquals(testEmail, email.getFromAddress().getAddress()); //testing email get comparing to the email assigned
	}
}