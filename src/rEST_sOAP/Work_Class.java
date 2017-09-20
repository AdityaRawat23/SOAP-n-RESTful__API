package rEST_sOAP;
import rEST_sOAP.Dummy_Http;
import rEST_sOAP.SOAPClientSAAJ;
public class Work_Class {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*String url="https://hooks.slack.com/services/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";

		String url2="http://202.54.219.46:8012/api/OMS/GetOrgChart/Parm?modifiedafter=11%20Jul%202017%2000%3A00%3A00";

		String json="{\"text\": \"This is a line of text in a channel.\\nAnd this is another line of text.\"}";

		Dummy_Http obj2=new Dummy_Http();

		String str=obj2.doGet(url2);
		System.out.println(str);
		
		System.out.println("CALLING DO POST METHOD OF DUMMY_HTTP CLASS\n");
		int sts=obj2.doPost(url,json);
		System.out.println(sts);

		
		*/
		
		System.out.println("CALLING THE SOAP_CALL METHOD OF THE SOAPClientSAAJ CLASS\n\n");
		String soapEndpointUrl = "http://www.webservicex.net/ConvertSpeed.asmx?WSDL";
		String soapAction = "http://www.webserviceX.NET/ConvertSpeed";

		String myNamespace = "myNamespace";
		String myNamespaceURI = "http://www.webserviceX.NET";
		SOAPClientSAAJ.soap_call(soapEndpointUrl,soapAction,myNamespace,myNamespaceURI);
		//SOAPClientSAAJ.soap_call(soapEndpointUrl,soapAction,myNamespace,myNamespaceURI);
	}

}