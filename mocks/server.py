import socket
filename = "listado_de_equipos_y_usuarios"
file_object = open(filename,"r")
json = file_object.read()
file_object.close()
headers = '''HTTP/1.1 200 OK
Date: Thu, 20 May 2004 21:12:58 GMT
Connection: close
Server: User Module
Accept-Ranges: bytes
Content-Type: application/json
Content-Length: 475
Last-Modified: Fri, 12 May 2017 03:06:40 GMT
'''
response = headers+"\r\n"+json
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
HOST = "0.0.0.0"
PORT = 2222
sock.bind((HOST, PORT))
sock.listen(1)
while(1):
  conn, addr = sock.accept()
  request= conn.recv(1024)
  print request
  conn.send(response)
  conn.close()
