package com.intercognition.mailcheck;

import java.util.Arrays;

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

        parts = Arrays.copyOfRange(parts,0,parts.length-1);
        address = join(parts,"@");

        tld = extractTld(domain);
        if(tld == null) valid = false;
    }

    public EmailAddress emailAddressWithDifferentDomain(String newDomain) {
        return new EmailAddress(address+"@"+newDomain);
    }

    public boolean isValid() {
        return valid;
    }

    public String getTLD() {
        return tld;
    }

    public String getDomain() {
        return domain;
    }

    public String getAddress() {
        return address;
    }

    public boolean hasDomain(String domain) {
        if(this.domain == null || domain == null) return false;
        return this.domain.equals(domain);
    }



    private boolean emailAddressHasAtLeastTwoNonEmptyParts(String[] parts) {
        if(parts.length < 2) {
            return false;
        }
        for(String part : parts) {
            if(part.isEmpty()) {
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
