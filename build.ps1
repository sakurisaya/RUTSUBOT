# RUTSUBOT Build Script (PowerShell)

$ErrorActionPreference = "Stop"

# Paths
$projectRoot = Get-Location
$srcDir = Join-Path $projectRoot "src\main\java"
$webappDir = Join-Path $projectRoot "src\main\webapp"
$classesDir = Join-Path $webappDir "WEB-INF\classes"
$tomcatHome = "C:\poritec\tools\apache-tomcat-10.1.54"
$libDir = Join-Path $tomcatHome "lib"

# Check dependencies
if (-not (Test-Path $tomcatHome)) {
    Write-Error "Tomcat not found at $tomcatHome"
}

# Create classes directory
if (-not (Test-Path $classesDir)) {
    New-Item -ItemType Directory -Path $classesDir -Force
}

# Collect libraries
$classpath = @()
$classpath += Join-Path $libDir "servlet-api.jar"
$classpath += Join-Path $libDir "jsp-api.jar"

# Include project-specific libs
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

Write-Host "Compiling $($javaFiles.Count) files..."

# Compile
& javac -encoding UTF-8 -d $classesDir -classpath $classpathStr $javaFiles

Write-Host "Build successful! Classes are in $classesDir"
