#!/usr/bin/env python3

import re
from datetime import datetime

FIRST_RELEASE_YEAR = 2024
VERSION_CODE = "const val versionCode"
VERSION_NAME = "const val versionName"
file_path = "buildSrc/src/main/java/Config.kt"

# Read the configuration file
try:
    with open(file_path, 'r') as file:
        lines = file.readlines()
except FileNotFoundError:
    print(f"Error: The file {file_path} was not found.")
    exit(1)

# Get the current date and year
current_month = datetime.now().strftime("%m")
current_year = int(datetime.now().strftime("%Y"))

# Calculate the difference in years from the first release
year_difference = current_year - FIRST_RELEASE_YEAR

# Extract the current versionCode and increment it
version_code_pattern = re.compile(r'const val versionCode: Int = (\d+)')
version_code_match = version_code_pattern.search(''.join(lines))
if version_code_match:
    current_version_code = int(version_code_match.group(1))
else:
    print("Error: versionCode not found in the file.")
    exit(1)

new_version_code = current_version_code + 1

# Find the current version prefix and update if necessary
new_prefix = str(year_difference + 1)

# Update lines in the configuration file
updated_lines = []
for line in lines:
    if VERSION_CODE in line:
        updated_lines.append(f"    {VERSION_CODE}: Int = {new_version_code}")
    elif VERSION_NAME in line:
        updated_lines.append(f"    {VERSION_NAME}: String = \"{new_prefix}.{current_month}\"")
    else:
        updated_lines.append(line.rstrip('\n'))

# Write updated lines back to the file
try:
    with open(file_path, 'w') as file:
        file.write('\n'.join(updated_lines))
        file.write('\n')
except IOError:
    print(f"Error: Could not write to the file {file_path}.")
    exit(1)
