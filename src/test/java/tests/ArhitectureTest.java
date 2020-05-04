package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArhitectureTest {

	@Test
	void checkAppClassesAndMethodsMain() {
		try {
			Class.forName("main.CLIApplication").getMethod("main", String[].class);
			Class.forName("main.GUIApplication").getMethod("main", String[].class);
		} catch (ClassNotFoundException e) {
			fail("The application doesn't have both classes GUI and CLI");
		} catch (NoSuchMethodException e) {
			fail("The application doesn't have \"main()\" methods");
		} catch (SecurityException e) {
			
		}
	}
	
	@Test
	void checkSpacePackageClasses() {
		try {
			Class.forName("space.Asteroid");
			Class.forName("space.NasaDataProvider");
		} catch (ClassNotFoundException e) {
			fail("The application doesn't have both classes Asteroid and NasaDataProvider");
		} 
	}
}
