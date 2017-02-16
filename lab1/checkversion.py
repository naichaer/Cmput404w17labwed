#!/usr/bin/env python

import requests

print requests.__version__

response = requests.get("https://raw.githubusercontent.com/naichaer/cmput404w17labwed/master/lab1wednesday/checkversion.py")

print response.text
print response.status_code

