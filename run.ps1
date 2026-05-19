# RUTSUBOT Run Script (PowerShell)

$ErrorActionPreference = "Stop"

# Paths
$projectRoot = Get-Location
$webappDir = Join-Path $projectRoot "src\main\webapp"
$tomcatHome = "C:\poritec\tools\apache-tomcat-10.1.54"
$deployDir = Join-Path $tomcatHome "webapps\RUTSUBOT"

# 1. Build if necessary
Write-Host "Checking build..."
& .\build.ps1

# 2. Deploy to Tomcat
Write-Host "Deploying to Tomcat..."
if (Test-Path $deployDir) {
    Remove-Item $deployDir -Recurse -Force
}
New-Item -ItemType Directory -Path $deployDir -Force
Copy-Item -Path "$webappDir\*" -Destination $deployDir -Recurse -Force

# 3. Start Tomcat
Write-Host "Starting Tomcat at http://localhost:8080/RUTSUBOT/"
$env:CATALINA_HOME = $tomcatHome
$env:JAVA_HOME = $env:JAVA_HOME # Ensure JAVA_HOME is set

# Run catalina.bat in a new window or current terminal
# Using 'run' to see logs in current terminal
& "$tomcatHome\bin\catalina.bat" run
