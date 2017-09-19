package rEST_sOAP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Dummy_Http {
	
	//http GET function for request and response JSON
	
	public String doGet(String url) throws Exception, Exception
	{
		System.out.println("CALLING HTTP DO GET METHOD DUMMY_HTTP CLASS");
		JSONParser parser=new JSONParser();
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		request.addHeader("content-type", "application/json");
		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer re=new StringBuffer();

		String line = "";
		while ((line = rd.readLine()) != null) {
			re.append(line);
		}

		JSONArray dummy1=(JSONArray)parser.parse(re.toString());
		//Complete response JSON string
		//System.out.println(dummy1);
		System.out.println("");
		JSONObject dummy2=(JSONObject)dummy1.get(1);
		//first Object of the JSONArray indexed at 1
		//System.out.println(dummy2);
		System.out.println("");
		String dummy3=(String)dummy2.get("VOIP");
		//Value of VOIP for the 2nd JSONObject in  the JSONArray 
		int status=response.getStatusLine().getStatusCode();
		
		return dummy3;
		//System.out.println("Value of VOIP for the 2nd JSONObject in  the JSONArray \n"+dummy3);
		//System.out.println("");

	}

	@SuppressWarnings({ "deprecation", "resource" })
	public int doPost(String url,String json) throws Exception, Exception
	{

		//"https://hooks.slack.com/services/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"
		//\"text\": \"This is a line of text in a channel.\\nAnd this is another line of text.\"

		HttpClient client = new DefaultHttpClient();

		HttpPost post = new HttpPost(url);

		StringEntity entity = new StringEntity(json);
		post.setEntity(entity);
		HttpResponse response = client.execute(post);

		int status=response.getStatusLine().getStatusCode();
		return status;

	}

	public Object doPut(String url,String json) throws ClientProtocolException, IOException
	{
		HttpClient client = new DefaultHttpClient();

		/*String url="https://hooks.slack.com/services/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";*/
		HttpPut request = new HttpPut(url);
		StringEntity params =new StringEntity(url,"UTF-8");
		params.setContentType("application/json");
		request.addHeader("content-type", "application/json");
		request.addHeader("Accept", "*");
		request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		request.addHeader("Accept-Language", "en-US,en;q=0.8");
		request.setEntity(params);

		StringEntity entity = new StringEntity(json);
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
		int responseCode = response.getStatusLine().getStatusCode();
		return responseCode;

	}

	public String doDelete(String url) throws Exception, Exception
	{
		HttpClient httpClient = new DefaultHttpClient();
		HttpDelete deleteRequest = new HttpDelete(url);
		deleteRequest.setHeader("Accept", "application/json");

		HttpResponse response = httpClient.execute(deleteRequest);
		String status = response.getStatusLine().toString();
		
		return status;

	}

}