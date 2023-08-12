package com.names;

import java.util.LinkedHashMap;
import java.util.Map;

public class InputParser {

    public static void main(String[] args) {
        String input = "{1:F01RBOSGB2RXTCM0000000000}{2:1103CITIG82LXXXXN}{3:{108: WG50KE7P36103133}{121:34684dfe-530b-421f-8568-bb93222d9edb}}{4::20:2008211117242:23B:CRED:23E:CORT:32A:200826AED3625,43:33B:AED3625,43:50A:/AE120354022003002430029RBOSGB2RTCH:53A:NBADAEAAXXX:54A:CITIAEAD\n" +
                ":57A: /AE650211000000700012018CITIGB2L:59:/G834CIT118508818486078DINERS CLUB CENTRAL SUBMISSIONS:70:/BENEFRES/GB//FDA:71A: OUR-}";

        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        parseInputToLinkedHashMap(input, resultMap);

        // Convert the resultMap to JSON-like format
        String output = formatMapToJSON(resultMap, 1);
        System.out.println(output);
    }

    public static void parseInputToLinkedHashMap(String input, LinkedHashMap<String, Object> resultMap) {
        int startIndex = 0;
        while (startIndex < input.length()) {
            int openingBraceIndex = input.indexOf('{', startIndex);
            if (openingBraceIndex == -1) {
                break;
            }

            int closingBraceIndex = findMatchingClosingBrace(input, openingBraceIndex);
            if (closingBraceIndex == -1) {
                break;
            }

            String keyValueSection = input.substring(openingBraceIndex + 1, closingBraceIndex);
            startIndex = closingBraceIndex + 1;

            int colonIndex = keyValueSection.indexOf(':');
            if (colonIndex != -1) {
                String key = keyValueSection.substring(0, colonIndex).trim();
                String value = keyValueSection.substring(colonIndex + 1).trim();
                if (value.startsWith("{") && value.endsWith("}")) {
                    LinkedHashMap<String, Object> nestedMap = new LinkedHashMap<>();
                    parseInputToLinkedHashMap(value, nestedMap);
                    resultMap.put(key, nestedMap);
                } else if (value.startsWith(":")) {
                    // Handling the "4" key's value
                    String[] subValues = value.substring(1).split(":");
                    for (int i = 0; i < subValues.length; i += 2) {
                        String subKey = subValues[i].trim();
                        String subValue = subValues[i + 1].trim();
                        resultMap.put(subKey, subValue.replace("\n", "\\n"));
                    }
                } else {
                    resultMap.put(key, value.replace("\n", "\\n"));
                }
            }
        }
    }

    private static int findMatchingClosingBrace(String input, int openingBraceIndex) {
        int count = 0;
        for (int i = openingBraceIndex; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '{') {
                count++;
            } else if (c == '}') {
                count--;
                if (count == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static String formatMapToJSON(Map<String, Object> map, int indentation) {
        StringBuilder json = new StringBuilder();
        String indent = "  ".repeat(indentation);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            json.append(indent).append("\"").append(key).append("\": ");
            if (value instanceof Map) {
                json.append("{\n");
                json.append(formatMapToJSON((Map<String, Object>) value, indentation + 1));
                json.append(indent).append("}");
            } else {
                json.append("\"").append(value).append("\"");
            }
            json.append(",\n");
        }

        return json.substring(0, json.length() - 2); // Remove the last comma and newline
    }
}
