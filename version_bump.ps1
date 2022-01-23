# UNIMIB Modules - version_bump.sh
# Usage: .\version_bump.ps1 [new_version]
# Author: Davide Costantini

$NEW_VERSION = $args[0]
$POM_LOCATION = "pom.xml"
$PACKAGE_JSON_LOCATION = "src/main/resources/static/package.json"

if ($null -eq $NEW_VERSION) {
    Write-Output "Usage: .\version_bump.ps1 [new_version]"
    exit
}

Write-Output "Bumping version in $POM_LOCATION"
(get-content $POM_LOCATION) -replace "<version>[0-9]\.[0-9]\.[0-9]-SNAPSHOT<\/version>","<version>$NEW_VERSION</version>" | Out-File -encoding ASCII $POM_LOCATION
Write-Output "Bumping version in $PACKAGE_JSON_LOCATION"
(get-content $PACKAGE_JSON_LOCATION) -replace "`"version`":\s`"[0-9]\.[0-9]\.[0-9]`"","`"version`": `"$NEW_VERSION`"" | Out-File -encoding ASCII $PACKAGE_JSON_LOCATION
Get-ChildItem -Path src -Filter *.java -Recurse | ForEach-Object {
    Write-Output "Bumping version in $_"
    (get-content $_.FullName) -replace "@version\s+[0-9]\.[0-9]\.[0-9]","@version $NEW_VERSION" | Out-File -encoding ASCII $_.FullName
}
