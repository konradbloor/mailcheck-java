package com.intercognition.mailcheck;

import com.intercognition.mailcheck.config.SimpleConfiguration;
import com.intercognition.mailcheck.stringdistance.Sift3;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MailCheckTest {

	private SimpleConfiguration configuration;
	private MailCheck mailCheck;

	@Before
	public void setUp() {
		configuration = new SimpleConfiguration(3,
				Arrays.asList("yahoo.com", "yahoo.com.tw", "google.com", "hotmail.com", "gmail.com", "emaildomain.com", "comcast.net", "facebook.com", "msn.com", "gmx.com"),
				Arrays.asList("co.uk", "com", "org", "info"),
				new Sift3(5));
		mailCheck = new MailCheck(configuration);
	}

	@Test
	public void testSimpleSuggestion() {
		EmailAddress result = mailCheck.suggest("test@hotmail.co");
		assertEquals("test@hotmail.com", result.toString());
	}

	@Test
	public void testNoSuggestionGivenWhenDomainSeemsCorrect() {
		EmailAddress result = mailCheck.suggest("contact@kicksend.com");
		assertEquals(null, result);
	}

	@Test
	public void testSuggestions() {
		assertCorrection("test@emaildomain.co", "emaildomain.com");
		assertCorrection("test@gmail.con", "gmail.com");

		assertCorrection("test@gnail.con", "gmail.com");
		assertCorrection("test@GNAIL.con", "gmail.com");
		assertCorrection("test@#gmail.com", "gmail.com");
		assertCorrection("test@comcast.com", "comcast.net");
		assertCorrection("test@homail.con", "hotmail.com");
		assertCorrection("test@hotmail.co", "hotmail.com");
		assertCorrection("test@fabecook.com", "facebook.com");
		assertCorrection("test@yajoo.com", "yahoo.com");
		assertCorrection("test@randomsmallcompany.cmo", "randomsmallcompany.com");
	}

	@Test
	public void validOrCompletelyIncompleteDomainsHaveNoSuggestion() {
		assertNull(mailCheck.suggest("test@yahoo.com.tw"));
		assertNull(mailCheck.suggest(""));
		assertNull(mailCheck.suggest("test@"));
		assertNull(mailCheck.suggest("test"));

	}

	@Test
	public void noSuggestionForEmailAddressWithSubdomainAndMisspelledTld() {
		assertNull(mailCheck.suggest("test@mail.randomsmallcompany.cmo"));
	}

	@Test
	public void findClosestStringWithNullCandidateGivesNull() {
		assertNull(mailCheck.findClosestString(null, Arrays.asList("possible"), new Sift3(5), 3));
	}

	@Test
	public void findClosestStringWithEmptyCandidateGivesNull() {
		assertNull(mailCheck.findClosestString("", Arrays.asList("possible"), new Sift3(5), 3));
	}

	@Test
	public void findClosestStringWithNullPossibleMatchesGivesNull() {
		assertNull(mailCheck.findClosestString("candidate", null, new Sift3(5), 3));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void findClosestStringWithEmptyPossibleMatchesGivesNull() {
		assertNull(mailCheck.findClosestString("candidate", Collections.EMPTY_LIST, new Sift3(5), 3));
	}

	private void assertCorrection(String misspelled, String domain) {
		assertEquals(domain, mailCheck.suggest(misspelled).getDomain());
	}
}
