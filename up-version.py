#!/usr/bin/env python3
import re
import sys
import subprocess

if len(sys.argv) != 2:
    print(f'Usage: {sys.argv[0]} X.Y.Z')
    sys.exit(42)

files = {
    './pom.xml': r'(?<=<version>).*?(?=</version>)',
    './README.md': r'(?<=<version>).*?(?=</version>)',
    './deploy.sh': r'(?<=-DnewVersion=)\S+'
}

for filename, pattern in files.items():

    with open(filename, 'r') as file :
        text = file.read()

    new = re.sub(pattern, sys.argv[1], text, 1)

    with open(filename, 'w') as file:
        file.write(new)

subprocess.check_output(['bash','-c', f"git tag v{sys.argv[1]}"])
