package config;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;

import java.time.LocalDate;

public class DOMParser {

	private static String URLNasa;

	public static String xmlDOMParser(String URLNasaXML, LocalDate start, LocalDate end) {

		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(URLNasaXML);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		String localURL = doc.getElementsByTagName("endPoint").item(0).getTextContent().replaceAll("\n", "").trim();
		String localKey = doc.getElementsByTagName("apiKey").item(0).getTextContent().replaceAll("\n", "").trim();

		URLNasa = localURL.concat("?start_date=").concat(start.toString()).concat("&end_date=").concat(end.toString())
				.concat("&api_key=").concat(localKey);

		return URLNasa;
	}
}
