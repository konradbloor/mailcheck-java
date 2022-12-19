package com.intercognition.mailcheck;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EmailAddressTest {

	//basic cases

	@Test
	public void correctlySplitsNormalDomainIntoComponents() {
		EmailAddress emailAddress = new EmailAddress("test@example.com");
		assertSplitCorrect(emailAddress, "test", "example.com", "com");
	}

	@Test
	public void correctlySplitsDomainWithTwoTldPartsIntoComponents() {
		EmailAddress emailAddress = new EmailAddress("test@example.co.uk");
		assertSplitCorrect(emailAddress, "test", "example.co.uk", "co.uk");
	}

	@Test
	public void correctlySplitsDomainWithSubdomainIntoComponents() {
		EmailAddress emailAddress = new EmailAddress("test@mail.randomsmallcompany.co.uk");
		assertSplitCorrect(emailAddress, "test", "mail.randomsmallcompany.co.uk", "randomsmallcompany.co.uk");
	}

	//rfc compliant cases

	@Test
	public void splitDealsWithQuotes() {
		EmailAddress emailAddress = new EmailAddress("\"foo@bar\"@example.com");
		assertSplitCorrect(emailAddress, "\"foo@bar\"", "example.com", "com");
	}

	@Test
	public void splitDealsWithNumbers() {
		EmailAddress emailAddress = new EmailAddress("containsnumbers1234567890@example.com");
		assertSplitCorrect(emailAddress, "containsnumbers1234567890", "example.com", "com");
	}

	@Test
	public void splitDealsWithSymbolPlusSign() {
		EmailAddress emailAddress = new EmailAddress("contains+symbol@example.com");
		assertSplitCorrect(emailAddress, "contains+symbol", "example.com", "com");
	}

	@Test
	public void splitDealsWithSymbolMinusSign() {
		EmailAddress emailAddress = new EmailAddress("contains-symbol@example.com");
		assertSplitCorrect(emailAddress, "contains-symbol", "example.com", "com");
	}

	@Test
	public void splitDealsWithPeriodSymbolInAddressAndDomain() {
		EmailAddress emailAddress = new EmailAddress("contains.symbol@domain.contains.symbol");
		assertSplitCorrect(emailAddress, "contains.symbol", "domain.contains.symbol", "contains.symbol");
	}

	@Test
	public void splitDealsWithSpacesAndInvertedCommas() {
		EmailAddress emailAddress = new EmailAddress("\"contains.and\\ symbols\"@example.com");
		assertSplitCorrect(emailAddress, "\"contains.and\\ symbols\"", "example.com", "com");
	}

	@Test
	public void splitDealsWithPeriodAtSymbolAndInvertedCommas() {
		EmailAddress emailAddress = new EmailAddress("\"contains.and.@.symbols.com\"@example.com");
		assertSplitCorrect(emailAddress, "\"contains.and.@.symbols.com\"", "example.com", "com");
	}

	@Test
	public void splitDealsWithAllTheSymbols() {
		EmailAddress emailAddress = new EmailAddress("\"()<>[]:;@,\\\\\\\"!#$%&\\'*+-/=?^_`{}|\\ \\ \\ \\ \\ ~\\ \\ \\ \\ \\ \\ \\ ?\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ ^_`{}|~.a\"@allthesymbols.com");
		assertSplitCorrect(emailAddress,
				"\"()<>[]:;@,\\\\\\\"!#$%&\\'*+-/=?^_`{}|\\ \\ \\ \\ \\ ~\\ \\ \\ \\ \\ \\ \\ ?\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ ^_`{}|~.a\"",
				"allthesymbols.com",
				"com");
	}

	@Test
	public void splitDealsWithJustTld() {
		EmailAddress emailAddress = new EmailAddress("postbox@com");
		assertSplitCorrect(emailAddress,
				"postbox",
				"com",
				"com");
	}

	//non-rfc compliant cases

	@Test
	public void withoutUserEmailAddressIsInvalid() {
		assertFalse(new EmailAddress("example.com").isValid());
	}

	@Test
	public void withoutUserEmailAddressWithSubdomainIsInvalid() {
		assertFalse(new EmailAddress("abc.example.com").isValid());
	}

	@Test
	public void withoutUserEmailAddressBeginningWithAtSymbolIsInvalid() {
		assertFalse(new EmailAddress("@example.com").isValid());
	}

	@Test
	public void withoutDomainUserThenAtSymbolIsInvalid() {
		assertFalse(new EmailAddress("test@").isValid());
	}

	private void assertSplitCorrect(EmailAddress split, String address, String domain, String tld) {
		assertEquals(address, split.getAddress());
		assertEquals(domain, split.getDomain());
		assertEquals(tld, split.getTLD());
	}
}
