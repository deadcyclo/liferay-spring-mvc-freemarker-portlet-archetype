liferay-spring-mvc-freemarker-portlet-archetype
===============================================
This is a maven archetype for creating Liferay Spring MVC Freemarker portlets. Since this type of portlet is not 
officially supported by Liferay, no archetype currently exists. This archetype amends that. Furthermore this is a 
much more complete archetype than the archetypes currently supplied by Liferay. This archetype generates a runnable
portlet including a lot of examples of best practices and the best way of creating a Liferay Spring MVC Freemarker 
portlet. These best practices are influenced by both Liferays best practices, but also the best practices of
several projects I am currently working on.

This is the initial version of the archetype. Improvements will be added whenever possible. If you have suggestions, 
ideas, or better practices I would be very happy to hear them, or even better receive a pull request.

Requirements
============

To be able to use this archetype you need to download and install Liferay Portal EE Maven, if you are using the enterprise
edition. If you are using the community edition, maven should be able to find this itself. Note currently I've been using 
SP5 version, since when I started working on this project, no artifacs were published for later service packs.

Installation
============

1. Clone the repository
2. Run the command mvn install

Usage
=====

1. In your Liferay maven project run mvn archetype:generate -DarchetypeCatalog=Local -Dfilter=com.liferay.maven.archetypes:
2. Select the number beside liferay-portlet-spring-mvc-freemarker-archetype
3. Continue as if you are using any of the archetypes provided by Liferay
