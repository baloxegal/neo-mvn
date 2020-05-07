package config;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;

public class SAXParserAtributes {
	
	private static String testURLNasa;
	
	public static String xmlSAXParserAtributes(String testURLNasaXML) {
		SAXParserFactory factoryDoc = SAXParserFactory.newInstance();
		SAXParser parserDoc = null;
		try {
			parserDoc = factoryDoc.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		try {
			parserDoc.parse(testURLNasaXML, handler);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testURLNasa;
	}
	static DefaultHandler handler = new DefaultHandler() {

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			testURLNasa = attributes.getValue("URL");
			testURLNasa += attributes.getValue("demoApiKey");
		}
	};
}
