package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import space.SAXParserAtributes;

//import java.net.HttpURLConnection;
import java.net.URL;

import java.net.MalformedURLException;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONObject;

class NasaProviderTest {

	//private final static String NEO_TEST_URL = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2020-04-01&end_date=2020-04-01&api_key=DEMO_KEY";
	private final static String testURLNasaXML = "src/main/resources/test.xml";	
	@Test
	void checkConnection() {
		
		String testURLNasa = SAXParserAtributes.xmlSAXParserAtributes(testURLNasaXML);
		
		//Variant 1 - check connection
		
//		try {
//			//HttpURLConnection connection = (HttpURLConnection) new URL (NEO_TEST_URL).openConnection();
//			HttpURLConnection connection = (HttpURLConnection) new URL (testURLNasa).openConnection();
//			connection.connect();
//		} catch (MalformedURLException e) {
//			fail("URL specification error");
//		} catch (IOException e) {
//			fail("No internet connection or invalid URL");
//		}
		
		//Variant 2 - check connection and data
		
		URL nasa = null;
		try {
			//nasa = new URL(NEO_TEST_URL);
			nasa = new URL(testURLNasa);
		} catch (MalformedURLException e) {
			fail("URL specification error");
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(nasa.openStream()));
		} catch (IOException e) {
			fail("No internet connection or invalid URL");
		}

		String inputLine;
		String stringData = "";
		try {
			while ((inputLine = in.readLine()) != null) {
				//System.out.println(inputLine.replaceAll(",", ",\n"));
				stringData += inputLine;
			}
		} catch (IOException e) {
			fail("BufferedReader error on method readline()");
		}
		try {
			in.close();
		} catch (IOException e) {
			fail("BufferedReader error on method close()");
		}

		JSONObject data = new JSONObject(stringData);

		if(data.getJSONObject("near_earth_objects") == null)
			fail("No data error");

		//System.out.println("Test success - find " + data.getInt("element_count") + " elements");
	}
}