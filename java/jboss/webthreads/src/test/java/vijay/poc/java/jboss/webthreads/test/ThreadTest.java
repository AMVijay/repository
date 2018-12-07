package vijay.poc.java.jboss.webthreads.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ThreadTest implements Runnable{

	int count;
	
	public ThreadTest() {
		for (int i = 0; i < 500; i++) {
			Thread thread = new Thread(this);
			thread.start();
			count = (i+1);
		}
	}
	
	public void run() {
		try {
			System.out.println("Thread Started");
			Thread.sleep(3000);
			String responseString = "";
			String outputString = "";
			
			URL url = new URL("http://localhost:8080/webthreads/testServlet");
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;
			
			// Set the appropriate HTTP parameters.
			httpConn.setRequestMethod("GET");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			OutputStream out = httpConn.getOutputStream();
			// Write the content of the request to the outputstream of the HTTP Connection.
			out.close();
			// Ready with sending the request.

			// Read the response.
			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
			BufferedReader in = new BufferedReader(isr);

			// Write the SOAP message response to a String.
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			System.out.println("Thread Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		new ThreadTest();
	}
}
