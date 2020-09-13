#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import argparse
import os
import re
import shutil
import sys
import tempfile
import urllib
from itertools import islice
from pathlib import Path

from bs4 import BeautifulSoup
import requests


################################################################################################
# todo :

################################################################################################


################################################################################################
# Constants
#
################################################################################################
BLOG_MASTER_BRANCH_README_URL = 'https://raw.githubusercontent.com/kenshin579/advenoh.pe.kr/master/README.md'
LOCAL_README_FILE = '../README.md'

################################################################################################
# Functions
#
################################################################################################

def save_file(url, download_folder):
    download_filename = 'blog_README.md'
    mem = urllib.request.urlopen(url).read()
    full_path = os.path.join(download_folder, download_filename)
    with open(full_path, mode="wb") as tmp_dir:
        tmp_dir.write(mem)
    print("saved file")

    return full_path

def update_readme():
    tmp_dir = tempfile.TemporaryDirectory(dir="/tmp")
    print('tmp dir', tmp_dir.name)
    remote_file = save_file(BLOG_MASTER_BRANCH_README_URL, tmp_dir.name)
    print('remote_file', remote_file)
    remote_file_size = Path(remote_file).stat().st_size
    local_file_size = Path(LOCAL_README_FILE).stat().st_size
    if remote_file_size > local_file_size:
        shutil.copy(remote_file, LOCAL_README_FILE)

################################################################################################
# Main function
#
################################################################################################

def main():
    parser = argparse.ArgumentParser(description="Maintenance script for tutorials-java")

    parser.add_argument("-u", "--update", action='store_true',
                        help="Update README file from blog branch")

    if len(sys.argv[1:]) == 0:
        parser.print_help()
        parser.exit()

    args = parser.parse_args()

    print('args', args)

    if args.update:
        update_readme()

if __name__ == "__main__":
    sys.exit(main())
