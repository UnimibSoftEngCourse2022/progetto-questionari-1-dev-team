# UNIMIB Modules - version_bump.sh
# Usage: .\version_bump.ps1 [new_version]
# Author: Davide Costantini

$new_version = $args[0]

if ($null -eq $new_version) {
    Write-Output "Usage: .\version_bump.ps1 [new_version]"
    exit
}
else {
    Get-ChildItem -Path src -Filter *.java -Recurse | ForEach-Object {
    	Write-Output "Bumping version in $_"
        (get-content $_.FullName) -replace "@version\s+[0-9]\.[0-9]\.[0-9]","@version $new_version" | Out-File -encoding ASCII $_.FullName
    }
}