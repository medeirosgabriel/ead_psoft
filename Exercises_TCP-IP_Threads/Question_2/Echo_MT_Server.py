import socket
from threading import Thread

LOCALHOST = "127.0.0.1"
PORT = 9090
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
	server.bind((LOCALHOST, PORT))
except:
	server.bind((LOCALHOST, PORT + 1))
	
class ClientThread(Thread):
	
    def __init__(self,clientAddress,clientSocket):
        Thread.__init__(self)
        self.socket = clientSocket
        self.address = clientAddress
        print "New connection:", clientAddress
        
    def run(self):
		while True:
			data = self.socket.recv(1024)
			if not data or data.decode() == 'FIM\n':
				print str(self.address) + " disconnected..."
				self.socket.close()
				break
			else:
				print str(self.address) + ": " + data.decode().rstrip()
				response = '> ' + data.decode()
				self.socket.sendall(response)
		

print("--------------------------- Echo Server Started ---------------------------")
while True:
	server.listen(1)
	clientSocket, clientAddress = server.accept()
	newthread = ClientThread(clientAddress, clientSocket)
	newthread.start()
