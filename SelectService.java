package clientService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;



import java.net.MalformedURLException; //Exceptions in case of invalid URL
import java.net.URL; //Uniform Resource Locator-Reference to service or object
import java.net.URLConnection; //communications link between the application and a URL
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Map;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis2.AxisFault;


public class SelectService {
	static String servicestring;

	public static void main(String[] args) {
		// Selecting web service with parameters
		String service2="service";
		Scanner reader = new Scanner(System.in);
		System.out.println("select service from below::\n1)Addition\n2)Substraction\n3)Multiplication\n4)division");
		int service  = reader.nextInt();
		System.out.println(service);
		System.out.println("\nEnter first parameter");
		int param1  = reader.nextInt();
		System.out.println("\nEnter second parameter");
		int param2  = reader.nextInt();
		
		


		int rep=3;


		do
		{
			int c = randomgenrator();
			if(c==1)
			{
				String fwsdl="" ;
				String urlVal1 = "http://10.200.59.148:8080/Server1/services/ServiceDirectory1?wsdl";
				URL url = null;
				URLConnection urlConnectionVal = null;
				String service1 = "add";
				System.out.println("server selected::1");
				
				try {
					url= new URL(urlVal1);
					urlConnectionVal = url.openConnection();
					if(urlConnectionVal.getContent() != null) {
						int aws= url.getPort();
						int hashcode= urlConnectionVal.hashCode();

						try{
							for(int z=0;z<100;z++){
							Service serviceObject=new Service();
							Call call=(Call) serviceObject.createCall();
							call.setTargetEndpointAddress(new URL(urlVal1));
							if(urlVal1.toLowerCase().contains("Server1".toLowerCase())){
								System.out.println("inside axis2 server 1 final param");
								call.setOperationName(new QName("http://serviceDir1",service2));
							}
							else{
								System.out.println("something went wrong in server selection");
							}
							call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
							call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);
							call.addParameter("arg2",Constants.XSD_INT, ParameterMode.IN);

							call.setReturnType(Constants.XSD_STRING);

							Object[] params=new Object[3];
							params[0]=service;
							params[1]=param1;
							params[2]=param2;
							
							//invoking service with client parameters
							String response=(String) call.invoke(params);
							System.out.println("Web Service Response at server 1 :"+response);
							}
							rep=4;
						

						}catch (RemoteException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}


					} else {
						System.out.println("BAD URL");
					
					}
				} catch (MalformedURLException ex) {
					System.out.println("bad URL");

				} catch (IOException ex) {
					System.out.println("Failed opening connection. ");
					rep=3;
				}
			}
			else if(c==2)
			{
				String fwsdl="" ;
				String urlVal1 = "http://10.200.59.148:8090/Server2/services/ServiceDirectory2?wsdl";
				URL url = null;
				URLConnection urlConnectionVal = null;
				String service1 = "add";
				System.out.println("server selected::2");
				try {
					url= new URL(urlVal1);
					urlConnectionVal = url.openConnection();
					if(urlConnectionVal.getContent() != null) {
						int aws= url.getPort();
						int hashcode= urlConnectionVal.hashCode();

						try{
							for(int z=0;z<100;z++){
							Service serviceObject=new Service();
							Call call=(Call) serviceObject.createCall();
							call.setTargetEndpointAddress(new URL(urlVal1));
							if(urlVal1.toLowerCase().contains("Server2".toLowerCase())){
								System.out.println("inside axis2 server 2 final param");
								call.setOperationName(new QName("http://serviceDir2",service2));
							}
							else{
								System.out.println("something went wrong in server selection");
							}
							call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
							call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);
							call.addParameter("arg2",Constants.XSD_INT, ParameterMode.IN);

							call.setReturnType(Constants.XSD_STRING);

							Object[] params=new Object[3];
							params[0]=service;
							params[1]=param1;
							params[2]=param2;
							
						
							//invoking service with client parameters
							String response=(String) call.invoke(params);
							System.out.println("Web Service Response at server 2 :"+response);
							}
							rep=4;
						

						}catch (RemoteException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}


					} else {
						System.out.println("BAD URL");
					
					}
				} catch (MalformedURLException ex) {
					System.out.println("bad URL");

				} catch (IOException ex) {
					System.out.println("Failed opening connection. ");
					rep=3;
				}
			}

			else if(c==3)
			{
				String fwsdl="" ;
				String urlVal1 = "http://10.200.59.148:8092/Server3/services/ServiceDirectory3?wsdl";
				URL url = null;
				URLConnection urlConnectionVal = null;
				String service1 = "add";
				System.out.println("server selected::3");
				try {
					url= new URL(urlVal1);
					urlConnectionVal = url.openConnection();
					if(urlConnectionVal.getContent() != null) {
						int aws= url.getPort();
						int hashcode= urlConnectionVal.hashCode();

						try{
							for(int z=0;z<100;z++){
							Service serviceObject=new Service();
							Call call=(Call) serviceObject.createCall();
							call.setTargetEndpointAddress(new URL(urlVal1));
							if(urlVal1.toLowerCase().contains("Server3".toLowerCase())){
								System.out.println("inside axis2 server 3 final param");
								call.setOperationName(new QName("http://serviceDir3",service2));
							}
							else{
								System.out.println("something went wrong in server selection");
							}
							call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
							call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);
							call.addParameter("arg2",Constants.XSD_INT, ParameterMode.IN);

							call.setReturnType(Constants.XSD_STRING);

							Object[] params=new Object[3];
							params[0]=service;
							params[1]=param1;
							params[2]=param2;
							
							
							//invoking service with client parameters
							String response=(String) call.invoke(params);
							System.out.println("Web Service Response at server 3 :"+response);
							}
							rep=4;
						

						}catch (RemoteException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}


					} else {
						System.out.println("BAD URL");
					
					}
				} catch (MalformedURLException ex) {
					System.out.println("bad URL");

				} catch (IOException ex) {
					System.out.println("Failed opening connection. ");
					rep=3;
				}
			}
			else if(c==4)
			{
				String fwsdl="" ;
				String urlVal1 = "http://10.200.59.148:8085/Server4/services/ServiceDirectory4?wsdl";
				URL url = null;
				URLConnection urlConnectionVal = null;
				String service1 = "add";
				System.out.println("server selected::4");
				try {
					url= new URL(urlVal1);
					urlConnectionVal = url.openConnection();
					if(urlConnectionVal.getContent() != null) {
						int aws= url.getPort();
						int hashcode= urlConnectionVal.hashCode();

						try{
							for(int z=0;z<100;z++){
							Service serviceObject=new Service();
							Call call=(Call) serviceObject.createCall();
							call.setTargetEndpointAddress(new URL(urlVal1));
							if(urlVal1.toLowerCase().contains("Server4".toLowerCase())){
								System.out.println("inside axis2 server 4 final param");
								call.setOperationName(new QName("http://serviceDir4",service2));
							}
							else{
								System.out.println("something went wrong in server selection");
							}
							call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
							call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);
							call.addParameter("arg2",Constants.XSD_INT, ParameterMode.IN);

							call.setReturnType(Constants.XSD_STRING);

							Object[] params=new Object[3];
							params[0]=service;
							params[1]=param1;
							params[2]=param2;
							
						
							//invoking service with client parameters
							String response=(String) call.invoke(params);
							System.out.println("Web Service Response at server 4 :"+response);
							}
							rep=4;
						

						}catch (RemoteException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}


					} else {
						System.out.println("BAD URL");
					
					}
				} catch (MalformedURLException ex) {
					System.out.println("bad URL");

				} catch (IOException ex) {
					System.out.println("Failed opening connection. ");
					rep=3;
				}
			}
			else if(c==5){
				String fwsdl="" ;
				String urlVal1 = "http://10.200.59.148:8083/Server5/services/ServiceDirectory5?wsdl";
				URL url = null;
				URLConnection urlConnectionVal = null;
				String service1 = "add";
				System.out.println("server selected::5");
				try {
					url= new URL(urlVal1);
					urlConnectionVal = url.openConnection();
					if(urlConnectionVal.getContent() != null) {
						int aws= url.getPort();
						int hashcode= urlConnectionVal.hashCode();

						try{
							for(int z=0;z<100;z++){
							Service serviceObject=new Service();
							Call call=(Call) serviceObject.createCall();
							call.setTargetEndpointAddress(new URL(urlVal1));
							if(urlVal1.toLowerCase().contains("Server5".toLowerCase())){
								System.out.println("inside axis2 server 5 final param");
								call.setOperationName(new QName("http://serviceDir5",service2));
							}
							else{
								System.out.println("something went wrong in server selection");
							}
							call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
							call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);
							call.addParameter("arg2",Constants.XSD_INT, ParameterMode.IN);

							call.setReturnType(Constants.XSD_STRING);

							Object[] params=new Object[3];
							params[0]=service;
							params[1]=param1;
							params[2]=param2;
							
							
							//invoking service with client parameters
							String response=(String) call.invoke(params);
							System.out.println("Web Service Response at server 5 :"+response);
							}
							rep=4;
						

						}catch (RemoteException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}


					} else {
						System.out.println("BAD URL");
					
					}
				} catch (MalformedURLException ex) {
					System.out.println("bad URL");

				} catch (IOException ex) {
					System.out.println("Failed opening connection. ");
					rep=3;
				}
			}
			else if(c==6){
				String fwsdl="" ;
				String urlVal1 = "http://10.200.59.148:8087/Server6/services/ServiceDirectory6?wsdl";
				URL url = null;
				URLConnection urlConnectionVal = null;
				String service1 = "add";
				System.out.println("server selected::6");
				try {
					url= new URL(urlVal1);
					urlConnectionVal = url.openConnection();
					if(urlConnectionVal.getContent() != null) {
						int aws= url.getPort();
						int hashcode= urlConnectionVal.hashCode();

						try{
							for(int z=0;z<100;z++){
							Service serviceObject=new Service();
							Call call=(Call) serviceObject.createCall();
							call.setTargetEndpointAddress(new URL(urlVal1));
							if(urlVal1.toLowerCase().contains("Server6".toLowerCase())){
								System.out.println("inside axis2 server 6 final param");
								call.setOperationName(new QName("http://serviceDir6",service2));
							}
							else{
								System.out.println("something went wrong in server selection");
							}
							call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
							call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);
							call.addParameter("arg2",Constants.XSD_INT, ParameterMode.IN);

							call.setReturnType(Constants.XSD_STRING);

							Object[] params=new Object[3];
							params[0]=service;
							params[1]=param1;
							params[2]=param2;
							
							
							//invoking service with client parameters
							String response=(String) call.invoke(params);
							System.out.println("Web Service Response at server 6 :"+response);
							}
							rep=4;
						

						}catch (RemoteException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}


					} else {
						System.out.println("BAD URL");
					
					}
				} catch (MalformedURLException ex) {
					System.out.println("bad URL");

				} catch (IOException ex) {
					System.out.println("Failed opening connection. ");
					rep=3;
				}
			}
			else
			{
				rep=3;
			}
		}while(rep==3);


	}
	// random generator function to choose random server while connecting client. We have used this to achieve fault tolerance
	public static int randomgenrator()
	{
		int repeat=2;
		int z;
		do
		{
			int[] myArray = {1,3,4,5,6}; //you can add here number of any particular to stop request going to that server
			Random rand = new Random(); 
			z= rand.nextInt((6 - 1) + 1) + 1;
			if(contains(myArray, z))			
			{
				repeat=2;
			}
			else
			{
				repeat=3;
			}

		}while(repeat==2);

		return z;

	}
	public static boolean contains(int[] arr, int item) {
		int index = Arrays.binarySearch(arr, item);
		return index >= 0;
	}





}
