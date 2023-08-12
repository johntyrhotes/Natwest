package com.names;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConvertInputToXML {

    public static void main(String[] args) {
        String input = "{1:F01RBOSGB2RXTCM0000000000}{2:1103CITIG82LXXXXN}{3:{108:WG50KE7P36103133}{121:34684dfe-530b-421f-8568-bb93222d9edb}}{4::20:2008211117242:23B:CRED:23E:CORT:32A:200826AED3625,43:33B:AED3625,43:50A:/AE120354022003002430029RBOSGB2RTCH:53A:NBADAEAAXXX:54A:CITIAEAD:57A:/AE:650211000000700012018CITIGB2L:59:/G834CIT118508818486078DINERS CLUB CENTRAL SUBMISSIONS:70:/BENEFRES/GB//FDA:71A:OUR-}";

        String xmlOutput = convertToXml(input);
        System.out.println(xmlOutput);
    }

    public static String convertToXml(String input) {
        StringBuilder xmlBuilder = new StringBuilder("<root>\n");

        String[] tags = input.split("(?<=\\}\\})");

        for (String tag : tags) {
            String[] parts = tag.split(":", 2);
            if (parts.length == 2) {
                String tagName = parts[0].substring(1); // Removing the leading '{'
                String tagValue = parts[1].replaceAll("[{}]", ""); // Removing the '{' and '}' from the value

                xmlBuilder.append("  <").append(tagName).append(">").append(tagValue).append("</").append(tagName).append(">\n");
            } else if (parts.length == 1) {
                xmlBuilder.append("  <").append(parts[0].substring(1)).append("/>\n"); // Empty tag
            }
        }

        xmlBuilder.append("</root>");

        return xmlBuilder.toString();
    }
}
