package com.intercognition.mailcheck;

import java.util.Collection;

import com.intercognition.mailcheck.config.Configuration;
import com.intercognition.mailcheck.stringdistance.DistanceAlgorithm;

/**
 * Usage:
 * <p>
 * First construct with a configuration.  A default configuration is supplied to get you started
 * quickly:
 * <p>
 * MailCheck mailCheck = new MailCheck(new DefaultConfiguration());
 * <p>
 * Then use the MailCheck object to give you suggestions:
 * <p>
 * EmailAddress suggestion = mailCheck.suggest("test@hitmail.com");
 * <p>
 * If the returned object is null
 */
public class MailCheck {

	private Configuration configuration;

	/**
	 * Creates a new MailCheck instance with the given configuration.
	 * @param configuration The desired configuration.
	 */
	public MailCheck(final Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * @param email The user entered email address that you want to check for suggestions with
	 * @return null, if no suggestions, or an EmailAddress object containing the suggestion
	 */
	public EmailAddress suggest(final String email) {

		EmailAddress emailParts = new EmailAddress(email);
		if (!emailParts.isValid()) {
			return null;
		}

		String closestDomain = this.findClosestString(emailParts.getDomain(), configuration.getDomains(), configuration.getDistanceAlgorithm(), configuration.getThreshold());

		if (closestDomain != null) {
			//we have a suggestion
			if (!emailParts.hasDomain(closestDomain)) {
				//if we have a suggestion different to the actual domain, return it
				return emailParts.emailAddressWithDifferentDomain(closestDomain);
			}
		} else {
			//we don't have a suggestion, check tld
			String closestTopLevelDomain = this.findClosestString(emailParts.getTLD(), configuration.getTopLevelDomains(), configuration.getDistanceAlgorithm(), configuration.getThreshold());
			if (emailParts.getDomain() != null && closestTopLevelDomain != null && !closestTopLevelDomain.equals(emailParts.getTLD())) {
				//return suggestion based off tld
				String domain = emailParts.getDomain();
				closestDomain = domain.substring(0, domain.lastIndexOf(emailParts.getTLD())) + closestTopLevelDomain;
				return emailParts.emailAddressWithDifferentDomain(closestDomain);
			}
		}

		//exact match, no match, or invalid email so no suggestion
		return null;
	}

	protected String findClosestString(final String candidate,
	                                   final Collection<String> possibleMatches,
	                                   final DistanceAlgorithm distanceAlgorithm,
	                                   final int threshold) {
		float stringDistance;
		float smallestMatchDistance = 99;
		String closestMatch = null;

		if (candidate == null || candidate.length() == 0 || possibleMatches == null || possibleMatches.isEmpty()) {
			return null;
		}

		for (String closeDomain : possibleMatches) {
			if (candidate.equals(closestMatch)) {
				return candidate;
			}
			stringDistance = distanceAlgorithm.getDistance(candidate, closeDomain);
			if (stringDistance < smallestMatchDistance) {
				smallestMatchDistance = stringDistance;
				closestMatch = closeDomain;
			}
		}

		if (smallestMatchDistance <= threshold && closestMatch != null) {
			return closestMatch;
		}
		return null;

	}

}
