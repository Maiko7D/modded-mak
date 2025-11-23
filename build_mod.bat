@echo off
REM Build script for homesweethome mod

REM Navigate to the project root (optional if already in it)
cd /d "%~dp0"

REM Stop any running Gradle daemons
echo Stopping any Gradle daemons...
gradlew.bat --stop

REM Clean the build folder
echo Cleaning project...
gradlew.bat clean

REM Build the mod
echo Building mod...
gradlew.bat build

REM Check if build/libs folder exists
if exist build\libs (
    echo Build complete! Opening build/libs folder...
    start "" build\libs
) else (
    echo Build failed or build/libs not found.
)

pause
