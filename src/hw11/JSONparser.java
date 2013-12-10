package hw11;

import java.lang.String;
import java.util.ArrayList;
import java.util.Objects;

public class JSONparser {

	public static ArrayList<NamedValue> parser(String output) {
		String rest = output;
		int open = 1;
		String nameval = "";
		String stringval = "";
		Object newval;
		int i = 0;
		if (output.charAt(i) == '"') {
			i++;
			while (output.charAt(i) != '"') { 
			nameval.concat(Character.toString(output.charAt(i)));
			i++;
			}
		}
		i++; // Move past the quotation mark
		rest = rest.substring(i);
		// new ArrayList
		ArrayList<NamedValue> newList = new ArrayList<NamedValue>(1);
		NamedValue val = new NamedValue();
		val.name = nameval;
		
		while (open > 0) {
			if (output.charAt(i) == '{') {
				newval = parser(rest.substring(1));
				val.value = newval;
			} else if (output.charAt(i) == '"'){
				i++; // move past the opening quotation mark
				while (output.charAt(i) != '"') {
					stringval.concat(Character.toString(output.charAt(i)));
					i++;
				} 
				i++; // Move past the closing quotation mark
				rest = rest.substring(i);
				val.value = stringval;
				newList.add(0, val);
			}  
			
			if (output.charAt(i) == ',') {
			} else if (output.charAt(i) == '[') {
				
			} else if (output.charAt(i) == '}') {
				open = 0;
			}

		}
		return newList;
	}

	public static void main(String[] args) {
		String string = "{ \"sam\":\"rebelsky\" }";
		if (string.charAt(0) != '{') {
			return;
		}

		parser(string);
	}
}

class NamedValue {
	String name;
	Object value;
} // NamedValue