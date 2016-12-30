#!/bin/bash

cd FrontendWeb
echo "Building Angular2 App"
ng build
echo "Copying Web output in project's web folder"
cp -r dist/* ../Backend/web
cd ..
