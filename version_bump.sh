#!/bin/bash

# UNIMIB Modules - version_bump.sh
# Usage: bash version_bump.sh [new_version]
# Author: Davide Costantini

if [ $# -ne 1 ]; then
    echo "Usage: bash version_bump.sh [new_version]"
    exit 1
fi
for file in $(find src -name "*.java"); do
    echo "Bumping version in " $file
    sed -i -r 's/@version\s+[0-9]\.[0-9]\.[0-9]/@version 0.1.0/1' $file
done