mailcheck-java
==============

This is a fork of the original for the purpose of publishing it to maven-central.

[![Build Status](https://github.com/BorderTech/mailcheck-java/actions/workflows/github-actions-build.yml/badge.svg)](https://github.com/BorderTech/mailcheck-java/actions/workflows/github-actions-build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=bordertech-mailcheck-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=bordertech-mailcheck-java)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=bordertech-mailcheck-java&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=bordertech-mailcheck-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=bordertech-mailcheck-java&metric=coverage)](https://sonarcloud.io/dashboard?id=bordertech-mailcheck-java)
[![javadoc](https://javadoc.io/badge2/com.github.bordertech.mailcheck/mailcheck-java/javadoc.svg)](https://javadoc.io/doc/com.github.bordertech.mailcheck/mailcheck-java)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.bordertech.mailcheck/mailcheck-java.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.bordertech.mailcheck%22%20AND%20a:%22mailcheck-java%22)

Introduction
------------
I noticed there was a useful project by Derrick Ko of Kicksend at <https://github.com/Kicksend/mailcheck>,
and thought it would be useful for GWT projects or for JSF projects to have java code to accomplish the same
thing.  It looks like through empirical testing the use of mailcheck has increased the quality of their data.

So all I've done is a simple port from Javascript to Java, leaving out the jquery integration.  I've moved stuff
around to fit the style of Java and my own style, and put in JUnit tests that should (at time of writing) cover
the same scenarios as are tested for in the Kicksend version of mailcheck.

There's no reason why JSR 303 or Spring validation couldn't be added to this project to lessen the need for
glue code, but I haven't put this in yet because an email address that has a suggestion isn't 'invalid' necessarily.


Installation
------------

Assuming maven, in your pom.xml:

```xml
<dependency>
    <groupId>com.github.bordertech.mailcheck</groupId>
    <artifactId>mailcheck-java</artifactId>
    <version> <!-- put latest version here --> </version>
</dependency>
```

Usage
-----
I've created it so that it can be easily used programmatically or configured by an IOC container like
Spring.  You can get up and running quickly by doing the following:

```java
MailCheck mailCheck = new MailCheck(new DefaultConfiguration());
EmailAddress address = mailCheck.suggest("user@hotmail.cod");
```

This will return you an EmailAddress object giving you a suggestion.  If a suggestion could not be found, a null
EmailAddress will be returned.


Author
-------

[Konrad Bloor](<kb@konradbloor.com>)


Contributors
------------

[Quenio dos Santos](<https://github.com/quenio>)


License
-------

Copyright (c) 2012 Konrad Bloor

Licensed under the MIT License.
