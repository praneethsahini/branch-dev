import os
import sys, getopt
import json
import time 
import re
import csv


def executeCurl(cid, message, server='http://127.0.0.1:8081'):
	# print(cid +" -> "+ message+"\n")
	jsonstr = ''.join(("{"," \"cid\": \"",cid,"\", \"message\": \"",(message),"\" ","}"))
	# print("jsStr: ",jsonstr,"\n")
	command = ''.join(("curl --location --request POST '",str(server),"/branch/message'  --header 'Content-Type: application/json' --data-raw '",str(jsonstr),"'"))
	print(''.join((command,"\n\n\n\n")))
	time.sleep(0.5)
	stream = os.popen(command)
	print(stream.read()+"\n")


def main(argv):
   file_name = ''

   try:
      opts, args = getopt.getopt(argv,"f:",["file="])
   except getopt.GetoptError:
      print 'fireCurlFromFile.py -f <file>'
      sys.exit(2)
   for opt, arg in opts:
      if opt == '-h':
         print 'fireCurlFromFile.py -f <file>'
         sys.exit()
      elif opt in ("-f", "--file"):
         file_name = arg
   print ('FILE is "', file_name)


   first = True
   for line in open(file_name, 'r').readlines():
      if not first:
      	 words = line.split(",")
      	 executeCurl(words[0], words[2].replace('\"', '').replace("\n", "").replace("\r", ""))
      else:
       	 first = False

if __name__ == "__main__":
   main(sys.argv[1:])

