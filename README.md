# Distributed-System-using-Kelips-Algorithm
Project to provide web services in multiple client server system using kelips for handling lookups and weighted response load balancer. 

The project consist of single client and six servers. Each server contains it's own service directory and load balancer. 
I've used Weighted response time as a load balancer. 
Web services for addition, subtraction, multiplication and division are provided by replicating them in two or more servers. 
Lookup protocol and logical overlay network for servers is based on kelips algorithm.

I've used axis2 framework in eclipse for creating this project. WSDLs are generated after server stubs are created. 
These WSDLs are used in the program by calling their URLs. 
URLs of the same web service present in different servers is identified by their port numbers and IP addresses present in URL. 

