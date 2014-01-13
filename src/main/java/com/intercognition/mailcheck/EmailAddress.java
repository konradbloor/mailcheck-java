package com.intercognition.mailcheck;

public class EmailAddress {

    private String address;
    private String domain;
    private String tld = "";
    private boolean valid = true;

    public EmailAddress(String email) {
        email = email.toLowerCase();

        String[] parts = email.split("@");
        if(!emailAddressHasAtLeastTwoNonEmptyParts(parts)) valid = false;

        domain = parts[parts.length-1];

		final String[] tmp = new String[parts.length-1];
		System.arraycopy(parts, 0, tmp, 0, parts.length-1);
		parts = tmp;

		address = join(parts,"@");

        tld = extractTld(domain);
        if(tld == null) valid = false;
    }

    public EmailAddress emailAddressWithDifferentDomain(String newDomain) {
        return new EmailAddress(address+"@"+newDomain);
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
    public boolean hasDomain(String domain) {
        return !(this.domain == null || domain == null) && this.domain.equals(domain);
    }

    private boolean emailAddressHasAtLeastTwoNonEmptyParts(String[] parts) {
        if(parts.length < 2) {
            return false;
        }
        for(String part : parts) {
            if(part.length() == 0) {
                return false;
            }
        }
        return true;
    }

    private String join(String[] components, String delimeter) {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for(String component: components) {
            if(first)
                first = false;
            else
                builder.append(delimeter);

            builder.append(component);
        }
        return builder.toString();
    }

    private String extractTld(String domain) {
        String[] domainParts = domain.split("\\.");
        if (domainParts.length == 0) return null;
        return extractTld(domainParts, domain);
    }

    private String extractTld(String[] domainParts, String domain) {
        if(domainParts == null || domainParts.length == 0) return null;

        String tld = "";
        if (domainParts.length == 1) {
            //valid because has tld
            tld = domain;
        } else {
            //has both tld and domain
            for (int i = 1; i < domainParts.length; i++) {
                tld += domainParts[i];
                if(i < domainParts.length-1) tld+= ".";
            }
        }
        return tld;
    }

    public String toString() {
        return address+"@"+domain;
    }
}
