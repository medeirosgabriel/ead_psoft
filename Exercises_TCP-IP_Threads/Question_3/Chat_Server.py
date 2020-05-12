#coding: utf-8

import socket
from threading import Thread

clients_sockets = []

def broadcast(message, connection): 
    for client in clients_sockets:
		if (client != connection):
			client.sendall(message)

class ClientThread(Thread):
	
    def __init__(self,clientAddress,clientSocket):
        Thread.__init__(self)
        self.socket = clientSocket
        self.address = clientAddress
        print "New connection:", clientAddress
        broadcast("User at " + str(self.address) + " connected...\n", self.socket)
        
    def run(self):
		while True:
			data = self.socket.recv(1024)
			if not data or data.decode() == 'BYE\n':
				msg = "User at " + str(self.address) + " disconnected...\n"
				broadcast(msg.encode(), self.socket)
				clients_sockets.remove(self.socket)
				self.socket.close()
				print msg.rstrip()
				break
			else:
				msg = str(self.address) + ": " + data.decode()
				broadcast(msg.encode(), self.socket)
				print msg.rstrip()

LOCALHOST = "127.0.0.1"
PORT = 9090
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
	server.bind((LOCALHOST, PORT))
except:
	server.bind((LOCALHOST, PORT + 1))

print("--------------------------------- Chat started ---------------------------------")
while True:
    server.listen(1)
    clientSocket, clientAddress = server.accept()
    clients_sockets.append(clientSocket)
    newthread = ClientThread(clientAddress, clientSocket)
    newthread.start()
