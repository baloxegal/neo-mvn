package main;

import java.time.LocalDate;
import java.util.Scanner;

import space.NasaDataProvider;

public class CLIApplication {

	public static void main(String[] args) throws Exception {
		
		Scanner in = new Scanner (System.in);
		System.out.println("Enter start/end dates");
		
		String startString = in.next(), endString = in.next();
		in.close();
		
		LocalDate start = LocalDate.parse(startString), end = LocalDate.parse(endString);
		
		if(end.isBefore(start) || start.plusDays(5).isBefore(end))
			end = start.plusDays(5);
		
		new NasaDataProvider().getNeoAsteroids(start, end);
	}

}