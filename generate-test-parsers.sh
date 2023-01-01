#!/usr/bin/env sh

java -jar antlr-4.11.1-complete.jar -package atr -no-listener -Dlanguage=Java ./grammars/*.g4

mv ./grammars/*.java ./src/test/java/atr

rm ./grammars/*.interp
rm ./grammars/*.tokens