@echo off
set JAVA_FX=C:\javafx\javafx-sdk-21\lib
set APP_JAR=todo-app.jar

javaw --module-path "%JAVA_FX%" ^
               --add-modules javafx.controls ^
               -jar "%APP_JAR%"

