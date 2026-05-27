# RUTSUBOT Run Script (PowerShell)

$ErrorActionPreference = "Stop"

# Load environment settings
$envFile = Join-Path $PSScriptRoot "env.ps1"
if (-not (Test-Path $envFile)) {
    Write-Error "env.ps1 not found. Please create env.ps1 and set the paths."
    exit 1
}
. $envFile

# Resolve JAVA_HOME
if ($JAVA_HOME_OVERRIDE -ne "") {
    $env:JAVA_HOME = $JAVA_HOME_OVERRIDE
}
if (-not $env:JAVA_HOME) {
    $temurinPath = Get-ChildItem "C:\Program Files\Eclipse Adoptium" -Filter "jdk-17*" -ErrorAction SilentlyContinue | Select-Object -First 1
    if ($temurinPath) {
        $env:JAVA_HOME = $temurinPath.FullName
    } else {
        Write-Error "JAVA_HOME is not set. Check env.ps1 or environment variables."
        exit 1
    }
}

$tomcatHome = $TOMCAT_HOME
$projectRoot = $PSScriptRoot
$webappDir = Join-Path $projectRoot "src\main\webapp"
$deployDir = Join-Path $tomcatHome "webapps\RUTSUBOT"

Write-Host "=== RUTSUBOT Run ===" -ForegroundColor Cyan
Write-Host "JAVA_HOME : $($env:JAVA_HOME)"
Write-Host "TOMCAT    : $tomcatHome"

if (-not (Test-Path $tomcatHome)) {
    Write-Error "Tomcat not found: $tomcatHome"
    exit 1
}

# 1. Build
Write-Host ""
Write-Host "[1/3] Building..." -ForegroundColor Yellow
& "$PSScriptRoot\build.ps1"

# 2. Deploy to Tomcat
Write-Host ""
Write-Host "[2/3] Deploying to Tomcat..." -ForegroundColor Yellow
if (Test-Path $deployDir) {
    Remove-Item $deployDir -Recurse -Force
}
New-Item -ItemType Directory -Path $deployDir -Force | Out-Null
Copy-Item -Path "$webappDir\*" -Destination $deployDir -Recurse -Force
Write-Host "Deployed to: $deployDir" -ForegroundColor Green

# 3. Start Tomcat
Write-Host ""
Write-Host "[3/3] Starting Tomcat at http://localhost:8080/RUTSUBOT/" -ForegroundColor Yellow
$env:CATALINA_HOME = $tomcatHome
& "$tomcatHome\bin\catalina.bat" run