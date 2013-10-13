#!/usr/bin/env python

import os
import shutil

DIR = "/Volumes/Elements/FLAC/"

def removeDots(filename, isDir):
    old = filename

    filename = filename.replace('_',' ')
    filename = filename.replace('-',' ')
    filename = filename.replace('.','')
    filename_list = list(filename)

    if not isDir:
        filename_list.insert(len(filename)-3,'.')
    filename = "".join(filename_list)
    #print DIR + filename
    #print DIR + old
    #os.path.join(DIR, old)
    shutil.move(DIR + old, DIR + filename)
    #os.rename(DIR + old, filename)

def addToName(filename, string):
    old = filename
    # print DIR + filename + string
    if (old[-9:] != '.DS_Store'):
        shutil.move(DIR + old, DIR + filename + string)
        


if __name__ == "__main__":
    for filename in os.listdir(DIR):
        if os.path.isdir(filename):
            print 'isDir'
        else:
            addToName(filename, ' [FLAC]')

