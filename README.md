mailcheck-java
==============

This is a fork of the original for the purpose of publishing it to maven-central.

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

	<dependency>
		<groupId>com.github.bordertech.mailcheck</groupId>
		<artifactId>mailcheck-java</artifactId>
		<version> <!-- put latest version here --> </version>
	</dependency>


Usage
-----
I've created it so that it can be easily used programmatically or configured by an IOC container like
Spring.  You can get up and running quickly by doing the following:

	MailCheck mailCheck = new MailCheck(new DefaultConfiguration());
	EmailAddress address = mailCheck.suggest("user@hotmail.cod");

This will return you an EmailAddress object giving you a suggestion.  If a suggestion could not be found, a null
EmailAddress will be returned.


Author
-------

Konrad Bloor (<kb@konradbloor.com>)


Contributors
------------

Quenio dos Santos (<https://github.com/quenio>)


License
-------

Copyright (c) 2012 Konrad Bloor

Licensed under the MIT License.
