package com.intercognition.mailcheck.config;

import com.intercognition.mailcheck.stringdistance.DistanceAlgorithm;

import java.util.Collection;

/**
 * Use to hold your own custom configuration.
 */
public class SimpleConfiguration implements Configuration {

	private int threshold;
	private Collection<String> domains;
	private Collection<String> topLevelDomains;
	private DistanceAlgorithm distanceAlgorithm;

	/**
	 * @param threshold         String distance above which a domain (i.e. 'hotmail.com') may not be considered a match,
	 *                          for example if your threshold is 3, then 'dugmail.com' would be a match but 'hotdogs.com' would
	 *                          not be a match, because dug != hot, distance is 3, and mail != dogs, distance is 4.
	 * @param domains           Unordered collection of possible domains, for example a List with the element "gmail.com"
	 * @param topLevelDomains   Unordered collection of possible top level domains, for example a List with the element
	 *                          "com"
	 * @param distanceAlgorithm A distance algorithm to use, Sift3 is supplied.
	 */
	public SimpleConfiguration(final int threshold,
	                           final Collection<String> domains,
	                           final Collection<String> topLevelDomains,
	                           final DistanceAlgorithm distanceAlgorithm) {
		this.threshold = threshold;
		this.domains = domains;
		this.topLevelDomains = topLevelDomains;
		this.distanceAlgorithm = distanceAlgorithm;
	}

	@SuppressWarnings("checkstyle:MissingJavadocMethod")
	public Collection<String> getDomains() {
		return domains;
	}

	@SuppressWarnings("checkstyle:MissingJavadocMethod")
	public DistanceAlgorithm getDistanceAlgorithm() {
		return distanceAlgorithm;
	}

	@SuppressWarnings("checkstyle:MissingJavadocMethod")
	public int getThreshold() {
		return threshold;
	}

	@SuppressWarnings("checkstyle:MissingJavadocMethod")
	public Collection<String> getTopLevelDomains() {
		return topLevelDomains;
	}
}
