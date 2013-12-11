package edu.grinnell.csc207.huann.hw11;

import java.lang.String;
import java.util.ArrayList;

public class JSONparser {
	public static ArrayList<NamedValue> parser(String output) {
		String rest = output;
		int open = 1;
		String valName = "";
		String valValue = "";
		Object newval;
		int i = 1;

		if (output.charAt(i) == '\"') {
			i++;
			while (output.charAt(i) != '\"') {
				valName.concat(Character.toString(output.charAt(i)));
				i++;
			}
		}
		i++; // Move past the quotation mark
		rest = rest.substring(++i);

		// new ArrayList
		ArrayList<NamedValue> newList = new ArrayList<NamedValue>(1);
		NamedValue val = new NamedValue();
		val.name = valName;

		while (open > 0) {
			if (output.charAt(i) == ' ') {
				i++;
				
			} else if (output.charAt(i) == ':') {
				i++;
				if (output.charAt(i) == '\"') {
					i++; // move past the opening quotation mark
					while (output.charAt(i) != '\"') {
						valValue.concat(Character.toString(output.charAt(i)));
						i++;
					} // while
					i++; // Move past the closing quotation mark
					rest = rest.substring(i);
					val.value = valValue;
					newList.add(0, val);
				}
				else if (output.charAt(i) == '{') {
					newval = parser(rest);
					val.value = newval;
				}
				else if (output.charAt(i) == '[') {
					i++;
					int samelevel = 0;
					int commacount = 0;
					while (output.charAt(i) != ']' && samelevel == 0){
						if ((output.charAt(i) == '{') || (output.charAt(i) == '['))
							samelevel++;
						else if ((output.charAt(i) == '}') || (output.charAt(i) == ']'))
							samelevel--;
						else if (output.charAt(i) == ',') {
							commacount++;
						}
						i++;
					}
					Object[] newarray = new Object[commacount + 1];
					for (int j = 0; j < commacount; j++) {
						//will break if we have empty array
						newarray[j] = parser(output.substring(i+1, output.indexOf('}', i)));
					}
				}
				else {
					
				} // else it's a number
			} else if (output.charAt(i) == '{') {
				newval = parser(rest.substring(1));
				val.value = newval;

			} else if (output.charAt(i) == ',') {

			} else if (output.charAt(i) == '}') {
				open = 0;
			}

		}
		return newList;
	} // parser

	public static void main(String[] args) {
		String string = "{ \"sam\":\"rebelsky\" }";
		if (string.charAt(0) != '{') {
			return;
		} // if
		System.out.println("original: " + string);
		parser(string);
		System.out.println(string);
	} // main
} // JSONparser

class NamedValue {
	String name;
	Object value;
} // NamedValue