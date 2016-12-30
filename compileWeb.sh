#!/bin/bash

cd FrontendWeb
echo "Building Angular2 App"
ng build
echo "Copying Web output in project's web folder"
rm -rf ../Backend/src/main/resources/static/*
cp -r dist/* ../Backend/src/main/resources/static/
cd ..
