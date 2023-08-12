package com.names;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwiftConversion {

	public static void main(String[] args) {
		
		String swiftMessage="{1:F01RBOSGB2RXTCM0000000000}{2:1103CITIG82LXXXXN}{3:{108:WG50KE7P36103133}{121:34684dfe-530b-421f-8568-bb93222d9edb}}{4:\n:20:2008211117242\n:23B:CRED\n:23E:CORT\n:32A:200826AED3625,43\n:33B:AED3625,43\n:50A:/AE120354022003002430029RBOSGB2RTCH\n:53A:NBADAEAAXXX\n:54A:CITIAEAD\n:57A:/AE650211000000700012018CITIGB2L\n:59:/G834CIT118508818486078DINERS CLUB CENTRAL SUBMISSIONS\n:70:/BENEFRES/GB//FDA\n:71A:OUR-}";
		
		 String input = "{4::20:20082:11117242:23B:CRED:23a:CORT:32A:200826AED3625,43:33B:AED3625,43:50A:/AE120354022003002430029RBOSGB2RTCH:53A:NBADAEAAXXX:54A:CITIAEAD:57A:/AE650211000000700012018CITIGB2L:59:/G834CIT118508818486078DINERS CLUB CENTRAL SUBMISSIONS:70:/BENEFRES/GB//FDA:71A:OUR-}";

	        // Define the regular expression pattern to match ":any 3 character value:"
	        String regex = ":(\\w{2,3}):([^:]+)";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(input);

	        // Create a Map to store the key-value pairs
	        Map<String, String> keyValueMap = new HashMap<>();

	        // Find and extract the key-value pairs
	        while (matcher.find()) {
	            String key = matcher.group(1);
	            String value = matcher.group(2);
	            keyValueMap.put(key, value);
	        }

	        // Print the key-value pairs in the map
	        for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
	            System.out.println(entry.getKey() + " = " + entry.getValue());
	        }

	}

}
