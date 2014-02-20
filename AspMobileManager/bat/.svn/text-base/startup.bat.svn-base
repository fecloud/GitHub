@echo off

REM ########################################################################
REM #                                                                      #
REM #      Network Test Server Startup script file                         #
REM #      COMPONENT:   Execution File                                     #
REM #      DATE:        2012/08/17                                       #
REM #      CREATOR:     yanlong wu                                         #
REM #                                                                      #
REM ########################################################################

set JAVA_OPTS=%JAVA_OPTS% -server -Xms512m -Xmx512m -XX:PermSize=128M -XX:MaxNewSize=256m -XX:MaxPermSize=256m

set ARGS=-c config.ini -l logs

set CLASSPATH=.;target/classes

set FindJarDir=lib
call :FindJar

java -version 2>&1|find "64-Bit" >nul
if %ERRORLEVEL% EQU 0 (
	echo java-64-bit
	set FindJarDir=lib\x86_64
	call :FindJar
) else (
	echo java-32-bit
	set FindJarDir=lib\x86
	call :FindJar
)


:loop
if [%1] == [] goto end
    set ARGS=%ARGS% %1
    shift
    goto loop
:end


@echo off
start javaw -classpath "%CLASSPATH%" com.aspire.sqmp.mobilemanager.MobileManager %ARGS%
@ping -n 3 127.1 >nul

:SetClassPath
set CLASSPATH=%CLASSPATH%;%x%
goto :eof

:FindJar

for /f %%i in ('dir /a-d/b %FindJarDir%\*.jar') do (
        set x=%cd%\%findJarDir%\%%i
        call :SetClassPath
	)
)
goto :eof
