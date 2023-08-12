package com.names;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class InputToXMLConverter {
    public static void main(String[] args) {
        String input = "{1:F01RBOSGB2RXTCM0000000000}{2:1103CITIG82LXXXXN}{3:{108:WG50KE7P36103133}{121:34684dfe-530b-421f-8568-bb93222d9edb}}{4::20:2008211117242:23B:CRED:23E:CORT:32A:200826AED3625,43:33B:AED3625,43:50A:/AE120354022003002430029RBOSGB2RTCH:53A:NBADAEAAXXX:54A:CITIAEAD:57A:/AE650211000000700012018CITIGB2L:59:/G834CIT118508818486078DINERS CLUB CENTRAL SUBMISSIONS:70:/BENEFRES/GB//FDA:71A:OUR-}";

        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("root");
            document.appendChild(rootElement);

            String[] tokens = input.split("(?<=})(?=\\{)");

            for (String token : tokens) {
                if (token.startsWith("{")) {
                    token = token.substring(1, token.length() - 1); // Remove braces
                    String[] keyValue = token.split(":", 2);
                    String key = keyValue[0];
                    String value = keyValue[1];

                    Element element = document.createElement(key);
                    element.appendChild(document.createTextNode(value));
                    rootElement.appendChild(element);
                } else if (token.startsWith("{3:")) {
                    // Handling special case for nested elements
                    String nestedToken = token.substring(3, token.length() - 1); // Remove {3:} and braces
                    String[] nestedKeyValuePairs = nestedToken.split("\\}\\{");

                    Element nestedElement = document.createElement("3");
                    for (String nestedKeyValuePair : nestedKeyValuePairs) {
                        String[] keyValue = nestedKeyValuePair.split(":", 2);
                        String key = keyValue[0];
                        String value = keyValue[1];

                        Element element = document.createElement(key);
                        element.appendChild(document.createTextNode(value));
                        nestedElement.appendChild(element);
                    }

                    rootElement.appendChild(nestedElement);
                } else if (token.startsWith("{4:")) {
                    // Handling special case for nested elements
                    String nestedToken = token.substring(3, token.length() - 1); // Remove {4:} and braces
                    String[] nestedKeyValuePairs = nestedToken.split(":");

                    Element nestedElement = document.createElement("4");
                    for (int i = 0; i < nestedKeyValuePairs.length; i += 2) {
                        String key = nestedKeyValuePairs[i];
                        String value = nestedKeyValuePairs[i + 1];

                        Element element = document.createElement(key);
                        element.appendChild(document.createTextNode(value));
                        nestedElement.appendChild(element);
                    }

                    rootElement.appendChild(nestedElement);
                }
            }

            // Output XML
            System.out.println(getStringFromDocument(document));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static String getStringFromDocument(Document doc) {
        try {
            javax.xml.transform.TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
            java.io.StringWriter writer = new java.io.StringWriter();
            transformer.transform(new javax.xml.transform.dom.DOMSource(doc), new javax.xml.transform.stream.StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (javax.xml.transform.TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
