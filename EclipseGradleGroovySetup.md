Guide to installing Fountain in Eclipse with Groovy/Gradle/JRebel plugins. 
==========================================================================

Set up environment
------------------

Assuming you have a standard DEV environment e.g JDK > 1.6 and associated tools:

Make sure that your hosts file is configured to use IPv4 for localhost; comment out `::1` lines and make sure `localhost	 127.0.0.1` is present.

Make sure you have an installation of the Fountain MySql DB and associated tools. 

Remove current version of Eclipse and install the latest version of eclipse [Juno](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/junosr1)

Download the latest version of Groovy (current 2.0.5) and install to D: drive or somewhere usefull e.g d:\groovy\groovy2.0.5
Add the var `GROOVY_HOME` to the environment variables and add `%GROOVY_HOME%\bin` to the path. 

When Eclipse is sucessfully installed install the following plugins and associated dependencies from the Eclipse marketplace (Help->Eclipse Marketplace)

* Groovy-Eclipse for Juno
* Gradle Integration for Eclipse
* <s>JRebel for Eclipse</s> This plugin should be installed after the project has been set up in Eclipse below to avoid any issues with generation of JRebel config. 

Make sure that eclipse restarts fine, then check out the correct branch from SVN, e.g. [svn://resdev01/reservoir/Fountain/branches/sprint_geb](svn://resdev01/reservoir/Fountain/branches/sprint_geb)

Import project to Eclipse
-------------------------

Import the project as a Gradle project; File->Import->Select Gradle Project.

On the import dialog:

* Select the checked out Fountain project and click on the 'build model' button. 
* Make sure 'Enable Dependency Management' and 'Enable DSL support' are *unchecked*.
* Click 'Finish'. Eclipse should download all the project dependencies (This may take a while!).
* Exit Eclipse

Modify Project config to deal with probs in the Gradle Plugin
-------------------------------------------------------------

The following are manual interventions that you must make to set up the project to work with the Eclipse environment.

* Copy the jar files from /lib/waffle to tomcatInstallationDirectory/lib e.g. `d:\tomcat\apache-tomcat-7.035\lib`.

* Modify the file `projectDir/.settings/org.eclipse.wst.common.component` so that the initial lines are as follows (overwriting any existing config that matches):
	* `	<wb-module deploy-name="Fountain">
		<property name="context-root" value="Fountain"/>
		<wb-resource deploy-path="/WEB-INF/classes" source-path="/src/main/java"/>
        <wb-resource deploy-path="/WEB-INF/classes" source-path="/src/main/groovy"/>
        <wb-resource deploy-path="/" source-path="/web"/>
        <wb-resource deploy-path="/WEB-INF/classes" source-path="/environment/local"/>  `

* Restart Eclipse, open the project properties and perform the following actions:
	* Stop validation or Eclipse will grind to a halt trying to validate all the static JSPs and other content in the project:
		* validation->suspend all validators(checkbox)
	* Remove `src/main` and `src/test` from the Java build path in the Source tab of the 'Java Build Path Menu.'
	* Refresh the project.
	* Add the /environment/local folder to the Deployment Assembly with a deploy path of 'WEB-INF/classes'.
	* Right click on proj and select Gradle->Enable Dependency Management (Wait for proj to refresh)
	* Right click on proj and select Gradle->Enable DSL support (Wait for proj to refresh)
	* Right click on proj and select Gradle->Refresh Dependencies (Wait for proj to refresh)

* Create a server in WTP the usual way and add the newly imported project to it. 

* Modify the WTP deploy path (if the project name is not Fountain) so that the URLs resolve correctly. 
	* Double click on server to bring up config, select 'modules' tab and modify path to make sure it is `/Fountain`, save, restart server if required. 

Add the JRebel plugin
---------------------

[JRebel](http://zeroturnaround.com/software/jrebel/download/#headline)

Follow the guide [here](http://zeroturnaround.com/software/jrebel/eclipse-jrebel-tutorial/) to install the JRebel plugin and configure. 

We currently do not have a commercial license(TBA) so you will have to get a social license key by providing a Twitter or Facebook account and agreeing to the T+C. 

JRebel will build a correct config and place it in src/main/groovy/rebel.xml TODO - Refactor to somewhere more sensible. 

Gradle functionality
--------------------

[Gradle](http://www.gradle.org/)

Gradle is a build and dependency management tool which combines flexibility of Ant with the dependency management and conventions of Maven.Gradle is configured via the file build.gradle which uses a DSL and various plugins to aid the process.

Gradle integrates with Jenkins via a plugin allowing us to execute Gradle tasks similar to Ant tasks. 

Gradle provides tasks out of the box and allows us to define our own, to see all the tasks available in the project run 'gradle tasks' from a cmd prompt. 

Other useful Gradle tasks:

`gradle --help`
`gradle projectReport` - Generate a report of the project dependencies.
`gradle war` - Build a war file of the project after compilation. 
`gradle test` - Run all the tests in the project. 
`gradle functionalTests` - Run the functional tests in the project.
`gradle unitTests` - Run the unit tests in the project.
`gradle integrationTests` - Run the integration tests in the project. 

***

Integrating project with Sublime2/Tomcat/JRebel standalone. 
===========================================================

TBA - What about debuggging!? 










