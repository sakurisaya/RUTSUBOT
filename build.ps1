# RUTSUBOT Build Script (PowerShell)

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

# Paths
$projectRoot = $PSScriptRoot
$tomcatHome = $TOMCAT_HOME
$srcDir = Join-Path $projectRoot "src\main\java"
$webappDir = Join-Path $projectRoot "src\main\webapp"
$classesDir = Join-Path $webappDir "WEB-INF\classes"
$libDir = Join-Path $tomcatHome "lib"

Write-Host "=== RUTSUBOT Build ===" -ForegroundColor Cyan
Write-Host "JAVA_HOME : $($env:JAVA_HOME)"
Write-Host "TOMCAT    : $tomcatHome"

if (-not (Test-Path $tomcatHome)) {
    Write-Error "Tomcat not found: $tomcatHome"
    exit 1
}

# Create classes directory
if (-not (Test-Path $classesDir)) {
    New-Item -ItemType Directory -Path $classesDir -Force | Out-Null
}

# Collect libraries
$classpath = @()
$servletJar = Join-Path $libDir "servlet-api.jar"
if (Test-Path $servletJar) { $classpath += $servletJar }
$jspJar = Join-Path $libDir "jsp-api.jar"
if (Test-Path $jspJar) { $classpath += $jspJar }

$projectLibDir = Join-Path $webappDir "WEB-INF\lib"
if (Test-Path $projectLibDir) {
    $projectLibs = Get-ChildItem -Path $projectLibDir -Filter "*.jar" | Select-Object -ExpandProperty FullName
    $classpath += $projectLibs
}

$classpathStr = $classpath -join ";"

# Find all Java files
$javaFiles = Get-ChildItem -Path $srcDir -Filter "*.java" -Recurse | Select-Object -ExpandProperty FullName

if ($javaFiles.Count -eq 0) {
    Write-Host "No Java files found in $srcDir"
    exit 0
}

Write-Host "Compiling $($javaFiles.Count) files..." -ForegroundColor Yellow

& "$($env:JAVA_HOME)\bin\javac.exe" -encoding UTF-8 -d $classesDir -classpath $classpathStr $javaFiles

Write-Host "Build successful!" -ForegroundColor Green