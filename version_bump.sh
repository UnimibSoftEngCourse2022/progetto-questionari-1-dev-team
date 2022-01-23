#!/bin/bash

# UNIMIB Modules - version_bump.sh
# Usage: bash version_bump.sh [new_version]
# Author: Davide Costantini

POM_LOCATION="pom.xml"
PACKAGE_JSON_LOCATION="src/main/resources/static/package.json"

if [ $# -ne 1 ]; then
    echo "Usage: bash version_bump.sh [new_version]"
    exit 1
fi

echo "Bumping version in $POM_LOCATION"
sed -i -r "s/<version>[0-9]\.[0-9]\.[0-9]-SNAPSHOT<\/version>/<version>$1-SNAPSHOT<\/version>/1" $POM_LOCATION
echo "Bumping version in $PACKAGE_JSON_LOCATION"
sed -i -r "s/\"version\":\s\"[0-9]\.[0-9]\.[0-9]\"/\"version\": \"$1\"/1" $PACKAGE_JSON_LOCATION
for file in $(find src -name "*.java"); do
    echo "Bumping version in $file"
    sed -i -r "s/@version\s+[0-9]\.[0-9]\.[0-9]/@version $1/1" "$file"
done
