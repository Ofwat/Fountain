echo off
set FOUNTAIN_INSTALLATION=""
set FOUNTAIN_SERVICE=""
set JAVA_JDK=""
set TOMCAT_DIR="" 

if "" == %FOUNTAIN_INSTALLATION% (
	echo FOUNTAIN_INSTALLATION is not set. Please edit this batch file to assign FOUNTAIN_INSTALLATION.
	goto end
) 
echo Fountain installed in %FOUNTAIN_INSTALLATION%

if "" == %FOUNTAIN_SERVICE% (
	echo FOUNTAIN_SERVICE is not set. Please edit this batch file to assign FOUNTAIN_SERVICE. 
	goto end
) 
echo Service name is %FOUNTAIN_SERVICE%

if "" == %JAVA_JDK% (
	echo JAVA_JDK is not set. Please edit this batch file to assign JAVA_JDK. 
	goto end
) 

echo Java JDK is in %JAVA_JDK%
if "" == %TOMCAT_DIR% (
	echo TOMCAT_DIR is not set. Please edit this batch file to assign TOMCAT_DIR. 
	goto end
) 
echo Tomcat dir is %TOMCAT_DIR%

set PR_DISPLAYNAME=%FOUNTAIN_SERVICE%
set PR_DESCRIPTION=Fountain running in Apache Tomcat Server - http://tomcat.apache.org/
set PR_INSTALL=%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\bin\tomcat7.exe
set PR_LOGPATH=%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\logs
set PR_CLASSPATH=%FOUNTAIN_INSTALLATION%;%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\bin\bootstrap.jar;%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\bin\tomcat-juli.jar
set PR_JVM=%JAVA_JDK%\jre\bin\server\jvm.dll
%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\bin\tomcat7.exe //IS//%FOUNTAIN_SERVICE% --StartClass org.apache.catalina.startup.Bootstrap --StopClass org.apache.catalina.startup.Bootstrap --StartParams start --StopParams stop --JavaHome="%JAVA_JDK%"
set PR_DISPLAYNAME=
set PR_DESCRIPTION=
set PR_INSTALL=
set PR_LOGPATH=
set PR_CLASSPATH=
set PR_JVM=
%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\bin\tomcat7.exe //US//%FOUNTAIN_SERVICE% --JvmOptions "-Dcatalina.base=%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%;-Dcatalina.home=%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%;-Djava.endorsed.dirs=%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\endorsed" --StartMode jvm --StopMode jvm
set PR_LOGPATH=%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\logs
set PR_STDOUTPUT=auto
set PR_STDERROR=auto
%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\bin\tomcat7.exe //US//%FOUNTAIN_SERVICE% ++JvmOptions "-Djava.io.tmpdir=%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\temp;-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager;-Djava.util.logging.config.file=%FOUNTAIN_INSTALLATION%\%TOMCAT_DIR%\conf\logging.properties" --JvmMs 128 --JvmMx 1024
 

:end
