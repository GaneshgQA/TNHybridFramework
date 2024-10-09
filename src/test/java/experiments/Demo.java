package experiments;

import java.util.Date;

public class Demo {

	public static void main(String[] args) {
	
		Date date = new Date();
		/*
		 * //System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
		 * String dateText = date.toString(); String dateTextWithoutSpaces =
		 * dateText.replace(" ", "_"); String dateTextWithoutSpacesAndSemiColon =
		 * dateTextWithoutSpaces.replace(":", "_");
		 * System.out.println(dateTextWithoutSpacesAndSemiColon);
		 */
		
		date.toString().replace(" ", "_").replace(":", "_");
		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
		

	}

}
