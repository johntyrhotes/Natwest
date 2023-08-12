package com.names;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwiftToXmlConverter {

    public static String convertSwiftToXml(String swiftInput) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<root>\n");
        
        String regex = "\\{([0-9A-Z]+):(.*?)(?=(\\{[0-9A-Z]+:|}))}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(swiftInput);

        while (matcher.find()) {
            String tag = matcher.group(1);
            String value = matcher.group(2);
            xmlBuilder.append("\t<").append(tag).append(">").append(value).append("</").append(tag).append(">\n");
        }

        xmlBuilder.append("</root>");
        return xmlBuilder.toString();
    }

    public static void main(String[] args) {
        String swiftInput = "{1:F01RBOSGB2RXTCM0000000000}{2:1103CITIG82LXXXXN}{3:{108:WG50KE7P36103133}{121:34684dfe-530b-421f-8568-bb93222d9edb}}{4::20:2008211117242:23B:CRED:23E:CORT:32A:200826AED3625,43:33B:AED3625,43:50A:/AE120354022003002430029RBOSGB2RTCH:53A:NBADAEAAXXX:54A:CITIAEAD:57A:/AE650211000000700012018CITIGB2L:59:/G834CIT118508818486078DINERS CLUB CENTRAL SUBMISSIONS:70:/BENEFRES/GB//FDA:71A:OUR-}";
        String xmlOutput = convertSwiftToXml(swiftInput);
        System.out.println(xmlOutput);
        System.out.println(xmlOutput);
    }
}
