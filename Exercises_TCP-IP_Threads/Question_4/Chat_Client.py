#coding: utf-8

import socket
from threading import Thread

class DataReceiver (Thread):

    def __init__(self, clientSocket):
        self.socket = clientSocket
        Thread.__init__(self)

    def run(self):
        while self.run:
			data = self.socket.recv(1024)
			if not data: break
			msg = data.decode()
			print msg

    def stop(self):
        self.run = False

LOCALHOST = "127.0.0.1"
PORT = 8090
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
	client.connect((LOCALHOST, PORT))
except:
	client.connect((LOCALHOST, PORT + 1))

dataReceiver = DataReceiver(client)
dataReceiver.start()

while True:
	out_data = raw_input()
	client.sendall(out_data.encode())
	if out_data == 'BYE':
		dataReceiver.stop()
		break
