package hw11;

import java.lang.String;
import java.util.ArrayList;
import java.util.Objects;


public class JSONparser {

	public static void parser(String output) {
		int open = 1;
		String nameval = null;
		int i = 0;
		while (output.charAt(i) != ':'){
			nameval.concat(Character.toString(output.charAt(i)));
			i++;
		}
		
		//new ArrayList
		ArrayList<NamedValue> no = new ArrayList<NamedValue>();
		while (open > 0){
			
			
		if (output.charAt(i) == '{') {
			parser(output.substring(1));
		}
		}
	}


	public static void main(String[] args) {

		if (string.charAt(0) != '{') {
			return;
		}
		int curlyCount = 0;
		int bracketCount = 0;
		while () {
			
		}
	}
}

class NamedValue {
    String name;
    Object value;
} // NamedValue