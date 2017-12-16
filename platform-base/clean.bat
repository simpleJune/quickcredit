rd /s /q .settings
del .project
call mvn eclipse:clean
call mvn clean
pause;