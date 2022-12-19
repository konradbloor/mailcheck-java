package com.intercognition.mailcheck;

public class EmailAddress {

	private String address;
	private String domain;
	private String tld;
	private boolean valid = true;

	/**
	 * Honestly this is self-documenting code and doesn't need javadoc.
	 * @param email Can you guess what to pass here?
	 */
	public EmailAddress(final String email) {
		String lcEmail = email.toLowerCase();

		String[] parts = lcEmail.split("@");
		if (!emailAddressHasAtLeastTwoNonEmptyParts(parts)) {
			valid = false;
		}

		domain = parts[parts.length - 1];

		final String[] tmp = new String[parts.length - 1];
		System.arraycopy(parts, 0, tmp, 0, parts.length - 1);
		parts = tmp;

		address = join(parts, "@");

		tld = extractTld(domain);
		if (tld == null) {
			valid = false;
		}
	}

	/**
	 * Create a version of the current EmailAddress with a different domain.
	 * @param newDomain The new domain.
	 * @return An email address.
	 */
	public EmailAddress emailAddressWithDifferentDomain(final String newDomain) {
		return new EmailAddress(address + "@" + newDomain);
	}

	/**
	 * @return true if domain, address, and tld have been successfully extracted
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @return top level domain, i.e. 'com', 'co.uk'
	 */
	public String getTLD() {
		return tld;
	}

	/**
	 * @return domain, i.e. 'hotmail.com'
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @return email address, i.e. 'test@example.com'
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param domain i.e. 'hotmail.com'
	 * @return true if this email address has a valid domain that is the same as the given domain parameter
	 */
	public boolean hasDomain(final String domain) {
		return !(this.domain == null || domain == null) && this.domain.equals(domain);
	}

	private boolean emailAddressHasAtLeastTwoNonEmptyParts(final String[] parts) {
		if (parts.length < 2) {
			return false;
		}
		for (String part : parts) {
			if (part.length() == 0) {
				return false;
			}
		}
		return true;
	}

	private String join(final String[] components, final String delimeter) {
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (String component : components) {
			if (first) {
				first = false;
			} else {
				builder.append(delimeter);
			}
			builder.append(component);
		}
		return builder.toString();
	}

	private String extractTld(final String domain) {
		String[] domainParts = domain.split("\\.");
		if (domainParts.length == 0) {
			return null;
		}
		return extractTld(domainParts, domain);
	}

	private String extractTld(final String[] domainParts, final String domain) {
		if (domainParts == null || domainParts.length == 0) {
			return null;
		}

		String tld = "";
		if (domainParts.length == 1) {
			//valid because has tld
			tld = domain;
		} else {
			//has both tld and domain
			for (int i = 1; i < domainParts.length; i++) {
				tld += domainParts[i];
				if (i < domainParts.length - 1) {
					tld += ".";
				}
			}
		}
		return tld;
	}

	/**
	 * Returns a string representation of this email address.
	 * @return An email address.
	 */
	public String toString() {
		return address + "@" + domain;
	}
}
