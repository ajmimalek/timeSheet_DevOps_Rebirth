@echo off
echo "--------------------------------------------------------------------------"
echo "------------------------- CI STARTUP SCRIPT ------------------------------"
echo "--------------------------------------------------------------------------"

echo "Startup SonarQube Server"
echo "------------------------"
START CMD /C "cd C:\products\sonarqube-6.7.7\bin\windows-x86-64 & CALL StartSonar.bat"
echo "Sonar may be up on http://localhost:9000/"

echo "Startup Nexus Repository Manager"
echo "--------------------------------"
START CMD /C "cd C:\products\nexus-3.31.1-01\bin & nexus.exe /run"
echo "Nexus may be up on http://localhost:8081/"

echo "-------------------------------- END -------------------------------------"