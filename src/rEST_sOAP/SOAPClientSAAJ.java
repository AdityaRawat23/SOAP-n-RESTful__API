package rEST_sOAP;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.soap.*;

public class SOAPClientSAAJ {

	public static void soap_call(String soapEndpointUrl,String soapAction,String myNamespace,String myNamespaceURI) {

		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// Send SOAP Message to SOAP Server
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, myNamespace, myNamespaceURI), soapEndpointUrl);

			// Print the SOAP Response
			System.out.println("Response SOAP Message:");
			soapResponse.writeTo(System.out);
			FileOutputStream fOut = new FileOutputStream("SoapMessage.xml");
			soapResponse.writeTo(fOut);

			System.out.println();
			System.out.println("SOAP message created");


			soapConnection.close();
		} catch (Exception e) {
			System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
			e.printStackTrace();
		}
	}

	private static void createSoapEnvelope(SOAPMessage soapMessage,String myNamespace,String myNamespaceURI,String soapAction,String...strings ) throws SOAPException 
	{
		try 
		{
			SOAPPart soapPart = soapMessage.getSOAPPart();
			// SOAP Envelope
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
			// SOAP Body
			SOAPBody soapBody = envelope.getBody();
			SOAPElement soapBodyElem;
			SOAPElement soapBodyElem1;
			/*if(strings.length>0)
			{
				String[] SOAPParameter=strings[0].split(",");
				String[] SOAPParameter1=strings[1].split(",");
				soapBodyElem = soapBody.addChildElement(soapAction.substring(soapAction.lastIndexOf("/")+1), myNamespace);
				soapBodyElem1 = soapBodyElem.addChildElement(SOAPParameter[0], myNamespace);
				SOAPElement soapBodyElem2 = soapBodyElem.addChildElement(SOAPParameter1[0],myNamespace);
				soapBodyElem2.addTextNode(SOAPParameter1[1]);
				soapBodyElem1.addTextNode(SOAPParameter[1]);
			}*/


			for(int i=0;i<strings.length;i++)
			{
				String[] SOAPParameter=strings[i].split(",");
				soapBodyElem = soapBody.addChildElement(soapAction.substring(soapAction.lastIndexOf("/")+1), myNamespace);
				soapBodyElem = soapBodyElem.addChildElement(SOAPParameter[0], myNamespace);
				soapBodyElem.addTextNode(SOAPParameter[1]);
			}

			//			SOAPElement soapBodyElem = soapBody.addChildElement("GetInfoByCity", myNamespace);
			//			SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("USCity", myNamespace);
			//			soapBodyElem1.addTextNode("New York");

		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	private static SOAPMessage createSOAPRequest(String soapAction,String myNamespace,String myNamespaceURI) throws Exception {

		MessageFactory messageFactory = MessageFactory.newInstance();
		byte[] encoded = Files.readAllBytes(Paths.get("C:\\Users\\raditya\\Desktop\\Webservices_adi\\ConvertSpeed.xml"));
		InputStream bStream = new ByteArrayInputStream(encoded);
		SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(null, bStream);
		SOAPBody body = soapMessage.getSOAPBody();
		//soapMessage.writeTo(System.out);
		//createSoapEnvelope(soapMessage,myNamespace,myNamespaceURI,soapAction,"CityName,NEW YORK","CountryName,USA");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", soapAction);

		soapMessage.saveChanges();

		/* Print the request message, just for debugging purposes */
		System.out.println("Request SOAP Message:");
		soapMessage.writeTo(System.out);
		System.out.println("\n");

		return soapMessage;
	}

}