package serviceDir1;



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

public class ServiceDirectory1 {
	static String service;



	public static String service(int ser,int param1,int param2)
	{
		//checking service received from client
		if(ser==1)
		{
			service = "add";

		}
		else if(ser==2)
		{
			service = "substract";

		}
		else if(ser==3)
		{
			service = "multiply";
		}
		else if (ser==4)
		{
			service = "divide";
		}
		else{
			System.out.println("something went wrong");
		}

		HashMap<String, String> hash = new HashMap<>();

		HashMap<String, String> hashown = new HashMap<>();
		HashMap<String, String> hashall = new HashMap<>();
		HashMap<String, String> hashg0 = new HashMap<>();
		HashMap<String, String> hashg1 = new HashMap<>();
		//map of services available on own server
		hashown.put( "http://10.200.59.148:8080/Server1/services/Substraction?wsdl", "substract");
		hashown.put("http://10.200.59.148:8080/Server1/services/Addition?wsdl","add");
		hashown.put("http://10.200.59.148:8080/Server1/services/ServiceDirectory1?wsdl","sdirectory");

		System.out.println("added");
		//Here we have created map of all services available on each server and we are storing that in each service directory
		hashall.put( "http://10.200.59.148:8080/Server1/services/Substraction?wsdl", "substract");	
		hashall.put("http://10.200.59.148:8080/Server1/services/Addition?wsdl","add");	
		hashall.put("http://10.200.59.148:8080/Server1/services/ServiceDirectory1?wsdl","sdirectory");

		System.out.println("added");

		hashall.put("http://10.200.59.148:8090/Server2/services/ServiceDirectory2?wsdl","sdirectory");
		hashall.put("http://10.200.59.148:8090/Server2/services/Multiplication?wsdl","multiply");
		hashall.put("http://10.200.59.148:8090/Server2/services/Division?wsdl","divide");
		System.out.println("added");

		hashall.put("http://10.200.59.148:8092/Server3/services/ServiceDirectory3?wsdl","sdirectory");
		hashall.put("http://10.200.59.148:8092/Server3/services/Multiplication?wsdl","multiply");
		hashall.put("http://10.200.59.148:8092/Server3/services/Addition?wsdl","add");


		System.out.println("added");	
		hashall.put("http://10.200.59.148:8085/Server4/services/ServiceDirectory4?wsdl","sdirectory");
		hashall.put("http://10.200.59.148:8085/Server4/services/Substraction?wsdl","substract");	
		hashall.put("http://10.200.59.148:8085/Server4/services/Addition?wsdl","add");
		System.out.println("added");

		hashall.put("http://10.200.59.148:8083/Server5/services/Multiplication?wsdl","multiply");
		hashall.put("http://10.200.59.148:8083/Server5/services/Substraction?wsdl","substract");
		hashall.put("http://10.200.59.148:8083/Server5/services/ServiceDirectory5?wsdl","sdirectory");
		System.out.println("added");

		hashall.put("http://10.200.59.148:8087/Server6/services/Addition?wsdl","add");
		hashall.put("http://10.200.59.148:8087/Server6/services/Multiplication?wsdl","multiply");
		hashall.put("http://10.200.59.148:8087/Server6/services/ServiceDirectory6?wsdl","sdirectory");
		System.out.println("added");
		HashMap<Integer,Integer> serverPorts = new HashMap<Integer,Integer>();
		URL url = null;
		URLConnection urlConnection = null;
		double serverCount = 0;


		//for loop to count number of unique severs
		for(Map.Entry<String, String> entry1 : hashall.entrySet()){
			try {
				url = new URL(entry1.getKey());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			int portNumber= url.getPort();

			if(!serverPorts.containsKey(portNumber)){
				serverPorts.put(new Integer(portNumber), 0);
				System.out.println("unique port number:"+portNumber);
				serverCount = serverCount + 1.00;
				System.out.println(serverCount);
			}
		}

		//Deciding number of groups depends upon server (nodes) count 
		int NoOfGroups = (int) Math.sqrt(serverCount);
		int group=0;
		
		//adding services in corresponding groups
		for(Map.Entry<Integer, Integer> entry : serverPorts.entrySet()){

			System.out.println("serverPorts i: "+ entry.getKey());
			serverPorts.put(entry.getKey(), group);
			if(group!= NoOfGroups-1){
				group++;
			}
			else if(group == NoOfGroups-1){
				group=0;
			}
		}

		for(Map.Entry<String, String> entry1 : hashall.entrySet()){
			for(Map.Entry<Integer, Integer> entry : serverPorts.entrySet()){
				if(entry1.getKey().contains(entry.getKey().toString()))
				{
					if(entry.getValue()==0)
					{
						hashg0.put(entry1.getKey(),entry1.getValue());
						System.out.println("adding in group 0"+entry1.getKey());
					}
					else
					{
						hashg1.put(entry1.getKey(),entry1.getValue());
						System.out.println("adding in group 1"+entry1.getKey());
					}
					break;
				}
			}

		}

		int selectgroup=0;
		int sbreak=0;
		//Checking if service is available on either group 0 or 1
		for(Map.Entry<String, String> entry1 : hashown.entrySet()){
			for(Map.Entry<String, String> entry0 : hashg1.entrySet()){
				if(entry1.getKey() == entry0.getKey())
				{
					//if service is available on group 1, breaking the loop
					selectgroup=1;
					sbreak=1;
					break;
				}
			}
			if(sbreak==1)
			{
				break;
			}
		}

		int finalanswer=0;
		if(selectgroup==1)
		{
			
			//if service is available on second group
			int own=0;
			int ownup=0;
			for (Map.Entry<String, String> entry : hashown.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if(service== value)
				{
					//if service is available on own server
					own=1;
					ownup=1;	


					URL urlVal = null;
					URLConnection urlConnectionVal = null;


					try {
						urlVal = new URL(entry.getKey());
						urlConnectionVal = urlVal.openConnection();
						if(urlConnectionVal.getContent() != null) {
							int aws= urlVal.getPort();
							int hashcode= urlConnectionVal.hashCode();
							String fwsdl=entry.getKey();
							finalanswer=1;

							try{
								Service serviceObject=new Service();
								Call call=(Call) serviceObject.createCall();
								call.setTargetEndpointAddress(new URL(fwsdl));
								if(fwsdl.toLowerCase().contains("Server1".toLowerCase())){
									call.setOperationName(new QName("http://Services1",service));
								}
								else if(fwsdl.toLowerCase().contains("Server2".toLowerCase())){
									System.out.println("inside axis2 server 7 final param");
									call.setOperationName(new QName("http://services2",service));
								}
								else if(fwsdl.toLowerCase().contains("Server3".toLowerCase())){
									call.setOperationName(new QName("http://Services3",service));
								}
								else if(fwsdl.toLowerCase().contains("Server4".toLowerCase())){
									call.setOperationName(new QName("http://Services4",service));
								}
								else if(fwsdl.toLowerCase().contains("Server5".toLowerCase())){
									call.setOperationName(new QName("http://Services5",service));
								}
								else if(fwsdl.toLowerCase().contains("Server6".toLowerCase())){
									call.setOperationName(new QName("http://Services6",service));
								}
								else{
									call.setOperationName(new QName("http://Services4",service));
								}
								call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
								call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);

								call.setReturnType(Constants.XSD_INT);

								Object[] params=new Object[2];
								params[0]=param1;
								params[1]=param2;
								System.out.println("Params: " + params[0] +"\t" +  params[1]);
								//invoking service with client parameters
								Integer response=(Integer) call.invoke(params);
								System.out.println("Web Service Response at server 1 :"+response);
								return response.toString();

							}catch (RemoteException e) {
								e.printStackTrace();
							} catch (Exception e) {
								e.printStackTrace();
							}


						} else {
							System.out.println("BAD URL");
							ownup=0;
						}
					} catch (MalformedURLException ex) {
						System.out.println("bad URL");
						ownup=0;
					} catch (IOException ex) {
						System.out.println("Failed opening connection. ");
						ownup=0;
					}
				}
			}

			if(ownup!=1)
			{
				//checking if service is not available on own server but available on group
				HashMap<String, String> hashanswer = new HashMap<>();
				for(Map.Entry<String, String> entry : hashg1.entrySet())
				{
					System.out.println("value coming::"+entry.getValue());
					System.out.println("serv value::"+service);
					if(entry.getValue()==service)
					{
						System.out.println("contains key"+entry.getValue());
						hashanswer.put(entry.getKey(),entry.getValue());
					}
				}	

				

				ArrayList<Long> timevalue = new ArrayList<Long>();
				/*
				if there are more than 1 server which provide same service available on group, we are using load balancer to check which server
				is responding faster and then we are choosing service from that server.
				It will also check service availability and then pick service accordingly
				 */


				for(Map.Entry<String, String> entry : hashanswer.entrySet())
				{

					URL url_for_LB = null;
					URLConnection urlConnection_for_LB = null;

					try {
						url_for_LB = new URL(entry.getKey());
						long startTime = System.currentTimeMillis();
						urlConnection_for_LB = url_for_LB.openConnection();
						if(urlConnection_for_LB.getContent() != null) {
							int aws= url_for_LB.getPort();
							int hashcode= urlConnection_for_LB.hashCode();
							long elapsedTime = System.currentTimeMillis() - startTime;
							System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
							timevalue.add(elapsedTime);

						} else {
							System.out.println("BAD URL");
						}
					} catch (MalformedURLException ex) {
						System.out.println("bad URL");
						timevalue.add((long) 100000.20); //default large values if service is fails
					} catch (IOException ex) {
						System.out.println("Failed opening connection. "); //default large values if service is fails
						timevalue.add((long) 100000.20);
					}
				}
				System.out.println("hashanswer size::"+hashanswer.size());
				if(hashanswer.size()!=0)
				{
					
					long smallest=0;
					if(timevalue!=null)
					{
						smallest = timevalue.get(0);
					}
					int key=0;
					for(long x : timevalue ){
						if (x < smallest) {
							smallest = x;
							key++;
						}

					}

					//Fetching the final server values from answer hashmap corresponding to the smallest time elapsed. 
					HashMap<String, String> hashanswer1 = new HashMap<>();
					int key1=0;
					String finalkey ="";
					System.out.println("key value::"+key);
					String fwsdl ="";
					for (String key_wsdl : hashanswer.keySet()) {
						fwsdl = key_wsdl;
						if(key1== key){
							break;
						}
						key1++;
					}
					finalanswer=1;

					try{
						Service serviceObject=new Service();
						Call call=(Call) serviceObject.createCall();
						call.setTargetEndpointAddress(new URL(fwsdl));
						if(fwsdl.toLowerCase().contains("Server1".toLowerCase())){
							call.setOperationName(new QName("http://Services1",service));
						}
						else if(fwsdl.toLowerCase().contains("Server2".toLowerCase())){
							call.setOperationName(new QName("http://services2",service));
						}
						else if(fwsdl.toLowerCase().contains("Server3".toLowerCase())){
							call.setOperationName(new QName("http://Services3",service));
						}
						else if(fwsdl.toLowerCase().contains("Server4".toLowerCase())){
							call.setOperationName(new QName("http://Services4",service));
						}
						else if(fwsdl.toLowerCase().contains("Server5".toLowerCase())){
							call.setOperationName(new QName("http://Services5",service));

						}
						else if(fwsdl.toLowerCase().contains("Server6".toLowerCase())){
							call.setOperationName(new QName("http://Services6",service));
						}
						else{
							System.out.println("wrong selection");
						}
						call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
						call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);

						call.setReturnType(Constants.XSD_INT);

						Object[] params=new Object[2];
						params[0]=param1;
						params[1]=param2;
						System.out.println("Params: " + params[0] +"\t" +  params[1]);

						Integer response=(Integer) call.invoke(params);
						System.out.println("Web Service Response  :"+response);
						return response.toString();

					}catch (RemoteException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}



				}
			}
			if(finalanswer!=1)
			{
				//if service is not available on same group then proceesing towards giving other node address
				HashMap<String, String> hashanswer = new HashMap<>();
				for(Map.Entry<String, String> entry : hashg0.entrySet())
				{
					if(entry.getValue()=="sdirectory")
					{
						System.out.println("contains key"+entry.getValue());
						hashanswer.put(entry.getKey(),entry.getValue());
					}
				}	

				for (Map.Entry<String, String> entry : hashanswer.entrySet()){

					System.out.println("hashanswerkey::"+entry.getKey() + " ::" + entry.getValue());  


				}

				ArrayList<Long> timevalue = new ArrayList<Long>(10);

				//for the nearest node from other group selection. We are using load balancer to choose nearest neighbor from other group

				for(Map.Entry<String, String> entry : hashanswer.entrySet())
				{

					URL url_for_LB = null;
					URLConnection urlConnection_for_LB = null;

					try {
						url_for_LB = new URL(entry.getKey());
						System.out.println("inside selection of final servers");
						long startTime = System.currentTimeMillis();
						urlConnection_for_LB = url_for_LB.openConnection();
						if(urlConnection_for_LB.getContent() != null) {
							int aws= url_for_LB.getPort();
							int hashcode= urlConnection_for_LB.hashCode();

							System.out.println(":hashcode of service:"+hashcode);
							System.out.println("::"+aws);
							System.out.println("GOOD URL");
							long elapsedTime = System.currentTimeMillis() - startTime;
							System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
							timevalue.add(elapsedTime);

						} else {
							System.out.println("BAD URL");
						}
					} catch (MalformedURLException ex) {
						System.out.println("bad URL");
						timevalue.add((long) 100000.20);
					} catch (IOException ex) {
						System.out.println("Failed opening connection.");
						timevalue.add((long) 100000.20);
					}
				}

				for (Long num : timevalue) { 		      
					System.out.println("timevalue::"+num); 		
				}
				long smallest = timevalue.get(0);

				int key=0;
				for(long x : timevalue ){
					if (x < smallest) {
						smallest = x;
						key++;
					}

				}

				//Fetching the final server values from answer hashmap corresponding to the smallest time elapsed. 
				HashMap<String, String> hashanswer1 = new HashMap<>();
				int key1=0;
				String finalkey ="";
				System.out.println("key value::"+key);
				String wsdl ="";
				for (String key_wsdl : hashanswer.keySet()) {
					wsdl = key_wsdl;
					if(key1== key){
						break;
					}
					key1++;
				}
				//Returning neighbor address
				finalanswer=1;
				
				try{
					System.out.println("fwsdl value for another group service"+wsdl);
					System.out.println("service parameter recieved"+service);
					Service serviceObject=new Service();
					Call call=(Call) serviceObject.createCall();
					call.setTargetEndpointAddress(new URL(wsdl));
					if(wsdl.toLowerCase().contains("Server1".toLowerCase())){
						call.setOperationName(new QName("http://serviceDir1","service"));
					}
					else if(wsdl.toLowerCase().contains("Server2".toLowerCase())){
						System.out.println("inside axis2 server 7 final param");
						call.setOperationName(new QName("http://serviceDir2","service"));
					}
					else if(wsdl.toLowerCase().contains("Server3".toLowerCase())){
						System.out.println("server service name::"+service);
						call.setOperationName(new QName("http://serviceDir3","service"));
					}
					else if(wsdl.toLowerCase().contains("Server4".toLowerCase())){
						call.setOperationName(new QName("http://serviceDir4","service"));
					}
					else if(wsdl.toLowerCase().contains("Server5".toLowerCase())){
						call.setOperationName(new QName("http://serviceDir5","service"));
					}
					else if(wsdl.toLowerCase().contains("Server6".toLowerCase())){
						call.setOperationName(new QName("http://serviceDir6","service"));
					}
					else{
						call.setOperationName(new QName("http://Services4",service));
					}
					call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
					call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);
					call.addParameter("arg2",Constants.XSD_INT, ParameterMode.IN);

					call.setReturnType(Constants.XSD_INT);

					Object[] params=new Object[3];
					params[0]=ser;
					params[1]=param1;
					params[2]=param2;
					System.out.println("Params: " + params[0] +"\t" +  params[1]);

					Integer response=(Integer) call.invoke(params);
					finalanswer=1;
					System.out.println("Web Service Response  :"+response);
					return response.toString();

				}catch (RemoteException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}






		}
		//end of first group
		else
		{
			//if service is available on first group
			int own=0;
			int ownup=0;
			for (Map.Entry<String, String> entry : hashown.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if(service== value)
				{
					//if service is available on own server
					own=1;
					ownup=1;	


					URL urlVal = null;
					URLConnection urlConnectionVal = null;


					try {
						urlVal = new URL(entry.getKey());
						System.out.println("inside selection of final servers");
						System.out.println("URL found...."+urlVal);
						urlConnectionVal = urlVal.openConnection();
						if(urlConnectionVal.getContent() != null) {
							int aws= urlVal.getPort();
							int hashcode= urlConnectionVal.hashCode();

							System.out.println(":hashcode of service:"+hashcode);
							System.out.println("::"+aws);
							System.out.println("GOOD URL");
							String fwsdl=entry.getKey();
							System.out.println("Final Final WSDL found in own server"+fwsdl);
							finalanswer=1;

							try{
								System.out.println("Inside Call block.. ");
								Service serviceObject=new Service();
								Call call=(Call) serviceObject.createCall();
								call.setTargetEndpointAddress(new URL(fwsdl));
								if(fwsdl.toLowerCase().contains("Server1".toLowerCase())){
									System.out.println("server service name::"+service);
									call.setOperationName(new QName("http://Services1",service));
								}
								else if(fwsdl.toLowerCase().contains("Server2".toLowerCase())){
									System.out.println("inside axis2 server 7 final param");
									call.setOperationName(new QName("http://services2",service));
								}
								else if(fwsdl.toLowerCase().contains("Server3".toLowerCase())){
									call.setOperationName(new QName("http://Services3",service));
								}
								else if(fwsdl.toLowerCase().contains("Server4".toLowerCase())){
									call.setOperationName(new QName("http://Services4",service));
								}
								else if(fwsdl.toLowerCase().contains("Server5".toLowerCase())){
									call.setOperationName(new QName("http://Services5",service));
								}
								else if(fwsdl.toLowerCase().contains("Server6".toLowerCase())){
									call.setOperationName(new QName("http://Services6",service));
								}
								else{
									call.setOperationName(new QName("http://Services4",service));
								}
								call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
								call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);

								call.setReturnType(Constants.XSD_INT);

								Object[] params=new Object[2];
								//invoking service with client parameters
								params[0]=param1;
								params[1]=param2;
								System.out.println("Params: " + params[0] +"\t" +  params[1]);

								Integer response=(Integer) call.invoke(params);
								System.out.println("Web Service Response at server 1 :"+response);
								return response.toString();

							}catch (RemoteException e) {
								e.printStackTrace();
							} catch (Exception e) {
								e.printStackTrace();
							}


						} else {
							System.out.println("BAD URL");
							ownup=0;
						}
					} catch (MalformedURLException ex) {
						System.out.println("bad URL");
						ownup=0;
					} catch (IOException ex) {
						System.out.println("Failed opening connection. ");
						ownup=0;
					}
				}
			}

			if(ownup!=1)
			{
				//checking if service is not available on own server but available on group
				HashMap<String, String> hashanswer = new HashMap<>();
				for(Map.Entry<String, String> entry : hashg0.entrySet())
				{
					
					if(entry.getValue()==service)
					{
						hashanswer.put(entry.getKey(),entry.getValue());
					}
				}	

		

				ArrayList<Long> timevalue = new ArrayList<Long>(10);
				for(Map.Entry<String, String> entry : hashanswer.entrySet())
				{
					System.out.println("services available at::"+entry.getKey());
				}


				/*
				if there are more than 1 server which provide same service available on group, we are using load balancer to check which server
				is responding faster and then we are choosing service from that server.
				It will also check service availability and then pick service accordingly
				 */
				for(Map.Entry<String, String> entry : hashanswer.entrySet())
				{

					URL url_for_LB = null;
					URLConnection urlConnection_for_LB = null;
					

					try {
						url_for_LB = new URL(entry.getKey());
						System.out.println("inside selection of final servers");
						System.out.println("URL found...."+url_for_LB);
						long startTime = System.currentTimeMillis();
						urlConnection_for_LB = url_for_LB.openConnection();
						if(urlConnection_for_LB.getContent() != null) {
							int aws= url_for_LB.getPort();
							int hashcode= urlConnection_for_LB.hashCode();

							System.out.println(":hashcode of service:"+hashcode);
							System.out.println("::"+aws);
							System.out.println("GOOD URL");
							long elapsedTime = System.currentTimeMillis() - startTime;
							System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
							timevalue.add(elapsedTime);

						} else {
							System.out.println("BAD URL");
						}
					} catch (MalformedURLException ex) {
						System.out.println("bad URL");
						timevalue.add((long) 100000.20);
					} catch (IOException ex) {
						System.out.println("Failed opening connection. Perhaps WS is not up?....yes yes");
						timevalue.add((long) 100000.20);
					}
				}
				if(hashanswer.size()!=0)
				{	

					for (Long num : timevalue) { 		      
						System.out.println("timevalue::"+num); 		
					}
					long smallest = timevalue.get(0);

					int key=0;
					for(long x : timevalue ){
						if (x < smallest) {
							smallest = x;
							key++;
						}

					}

					HashMap<String, String> hashanswer1 = new HashMap<>();
					int key1=0;
					String finalkey ="";
					String fwsdl ="";
					for (String key_wsdl : hashanswer.keySet()) {
						fwsdl = key_wsdl;
						if(key1== key){
							break;
						}
						key1++;
					}
					
					
					try{
						System.out.println("fwsdl value for group2"+fwsdl);
						Service serviceObject=new Service();
						Call call=(Call) serviceObject.createCall();
						call.setTargetEndpointAddress(new URL(fwsdl));
						if(fwsdl.toLowerCase().contains("Server1".toLowerCase())){
							call.setOperationName(new QName("http://Services1",service));
						}
						else if(fwsdl.toLowerCase().contains("Server2".toLowerCase())){
							System.out.println("inside axis2 server 7 final param");
							call.setOperationName(new QName("http://services2",service));
						}
						else if(fwsdl.toLowerCase().contains("Server3".toLowerCase())){
							System.out.println("server service name::"+service);
							call.setOperationName(new QName("http://Services3",service));
						}
						else if(fwsdl.toLowerCase().contains("Server4".toLowerCase())){
							call.setOperationName(new QName("http://Services4",service));
						}
						else if(fwsdl.toLowerCase().contains("Server5".toLowerCase())){
							call.setOperationName(new QName("http://Services5",service));
						}
						else if(fwsdl.toLowerCase().contains("Server6".toLowerCase())){
							call.setOperationName(new QName("http://Services6",service));
						}
						else{
							call.setOperationName(new QName("http://Services4",service));
						}
						call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
						call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);

						call.setReturnType(Constants.XSD_INT);

						Object[] params=new Object[2];
						params[0]=param1;
						params[1]=param2;
						System.out.println("Params: " + params[0] +"\t" +  params[1]);

						Integer response=(Integer) call.invoke(params);
						finalanswer=1;
						System.out.println("Web Service Response  :"+response);
						return response.toString();

					}catch (RemoteException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}



				}
			}
			if(finalanswer!=1)
			{
				 
				 //if service is not available on same group then proceesing towards giving other node address
				HashMap<String, String> hashanswer = new HashMap<>();
				for(Map.Entry<String, String> entry : hashg1.entrySet())
				{
					if(entry.getValue()=="sdirectory")
					{
						System.out.println("contains key"+entry.getValue());
						hashanswer.put(entry.getKey(),entry.getValue());
					}
				}	

				for (Map.Entry<String, String> entry : hashanswer.entrySet()){

					System.out.println("hashanswerkey::"+entry.getKey() + " ::" + entry.getValue());  


				}

				ArrayList<Long> timevalue = new ArrayList<Long>(10);


				 //for the nearest node from other group selection. We are using load balancer to choose nearest neighbor from other group
				for(Map.Entry<String, String> entry : hashanswer.entrySet())
				{

					URL url_for_LB = null;
					URLConnection urlConnection_for_LB = null;

					try {
						url_for_LB = new URL(entry.getKey());
						System.out.println("inside selection of final servers");
						long startTime = System.currentTimeMillis();
						urlConnection_for_LB = url_for_LB.openConnection();
						if(urlConnection_for_LB.getContent() != null) {
							int aws= url_for_LB.getPort();
							int hashcode= urlConnection_for_LB.hashCode();

							
							long elapsedTime = System.currentTimeMillis() - startTime;
							System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
							timevalue.add(elapsedTime);

						} else {
							System.out.println("BAD URL");
						}
					} catch (MalformedURLException ex) {
						System.out.println("bad URL");
						timevalue.add((long) 100000.20);
					} catch (IOException ex) {
						System.out.println("Failed opening connection. ");
						timevalue.add((long) 100000.20);
					}
				}

				
				long smallest = timevalue.get(0);

				int key=0;
				for(long x : timevalue ){
					if (x < smallest) {
						smallest = x;
						key++;
					}

				}

				//Fetching the final server values from answer hashmap corresponding to the smallest time elapsed. 
				HashMap<String, String> hashanswer1 = new HashMap<>();
				int key1=0;
				String finalkey ="";
				System.out.println("key value::"+key);
				String wsdl ="";
				//Fetching the final server values from answer hashmap corresponding to the smallest time elapsed. 
				for (String key_wsdl : hashanswer.keySet()) {
					wsdl = key_wsdl;
					if(key1== key){
						break;
					}
					key1++;
				}
				 //Returning neighbor address
				finalanswer=1;
				
				try{
					System.out.println("fwsdl value for another group service"+wsdl);
					System.out.println("service parameter recieved"+service);
					Service serviceObject=new Service();
					Call call=(Call) serviceObject.createCall();
					call.setTargetEndpointAddress(new URL(wsdl));
					if(wsdl.toLowerCase().contains("Server1".toLowerCase())){
						call.setOperationName(new QName("http://serviceDir1","service"));
					}
					else if(wsdl.toLowerCase().contains("Server2".toLowerCase())){
						System.out.println("inside axis2 server 7 final param");
						call.setOperationName(new QName("http://serviceDir2","service"));
					}
					else if(wsdl.toLowerCase().contains("Server3".toLowerCase())){
						System.out.println("server service name::"+service);
						call.setOperationName(new QName("http://serviceDir3","service"));
					}
					else if(wsdl.toLowerCase().contains("Server4".toLowerCase())){
						call.setOperationName(new QName("http://serviceDir4","service"));
					}
					else if(wsdl.toLowerCase().contains("Server5".toLowerCase())){
						call.setOperationName(new QName("http://serviceDir5","service"));
					}
					else if(wsdl.toLowerCase().contains("Server6".toLowerCase())){
						call.setOperationName(new QName("http://serviceDir6","service"));
					}
					else{
						call.setOperationName(new QName("http://Services4",service));
					}
					call.addParameter("arg0",Constants.XSD_INT, ParameterMode.IN);			            
					call.addParameter("arg1",Constants.XSD_INT, ParameterMode.IN);
					call.addParameter("arg2",Constants.XSD_INT, ParameterMode.IN);

					call.setReturnType(Constants.XSD_INT);

					Object[] params=new Object[3];
					params[0]=ser;
					params[1]=param1;
					params[2]=param2;
					System.out.println("Params: " + params[0] +"\t" +  params[1]);

					Integer response=(Integer) call.invoke(params);
					finalanswer=1;
					System.out.println("Web Service Response  :"+response);
					return response.toString();

				}catch (RemoteException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}





		}
		//end of second group






		return null;


	}
}


