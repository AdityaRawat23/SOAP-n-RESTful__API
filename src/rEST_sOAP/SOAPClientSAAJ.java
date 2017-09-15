package rEST_sOAP;
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
			System.out.println();

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
			if(strings.length>0)
			{
				String[] SOAPParameter=strings[0].split(",");
				soapBodyElem = soapBody.addChildElement(soapAction.substring(soapAction.lastIndexOf("/")+1), myNamespace);
				soapBodyElem1 = soapBodyElem.addChildElement(SOAPParameter[0], myNamespace);
				soapBodyElem1.addTextNode(SOAPParameter[1]);
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
		SOAPMessage soapMessage = messageFactory.createMessage();

		createSoapEnvelope(soapMessage,myNamespace,myNamespaceURI,soapAction,"USCity,New York");

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