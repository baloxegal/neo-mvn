package config;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;

import java.time.LocalDate;

public class SAXParserElement {
	
	private static String URLNasa;
	private static String lastElement;
	private static String localURL;
	private static String localKey;
	
	public static String xmlSAXParserElement (String URLNasaXML, LocalDate start, LocalDate end) {
		
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
			parserDoc.parse(URLNasaXML, handler);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URLNasa = localURL.concat("?start_date=").concat(start.toString()).concat("&end_date=").concat(end.toString()).concat("&api_key=").concat(localKey);
		
		return URLNasa;
	}
	
	static DefaultHandler handler = new DefaultHandler() {
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			lastElement = qName;
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			String information = new String(ch, start, length);
			information.replaceAll("\n", "").trim();
			if(lastElement == "endPoint") {
				localURL = information;
				lastElement = null;
			}
			else if(lastElement == "apiKey") {
				localKey = information;
				lastElement = null;
			}
		}
	};
}
