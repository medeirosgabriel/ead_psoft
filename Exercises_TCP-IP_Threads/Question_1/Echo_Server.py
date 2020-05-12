import socket

LOCALHOST = "127.0.0.1"
PORT = 9090
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
	server.bind((LOCALHOST, PORT))
except:
	server.bind((LOCALHOST, PORT + 1))

print("--------------------------- Echo Server Started ---------------------------")
client = False
while True:
	if not client:
		server.listen(1)
		clientSocket, clientAddress = server.accept()
		print 'New Connection: ' + str(clientAddress)
		client = True
		while True:
			data = clientSocket.recv(1024)
			if not data or data.decode() == 'FIM\n':
				print str(clientAddress) + " disconnected"
				clientSocket.close()
				client = False
				break
			else:
				print str(clientAddress) + ": " + data.decode().rstrip()
				clientSocket.sendall("> " + data)
				
