package page.pieters.cenv;

import java.util.HashMap;

public class JParse {

    public static HashMap<String, String> parseFlatJson(String jsonString) throws CenvJParseException {

        try {
            HashMap<String, String> map = new HashMap<>();

            if (jsonString == null || !jsonString.contains("{"))
                return map;

            boolean inQuotes = false;
            String src = jsonString.substring(jsonString.indexOf('{') + 1, jsonString.lastIndexOf('}')).trim();
            StringBuilder sb = new StringBuilder();
            String currentKey = null;

            for (int i = 0; i < src.length(); i++) {

                char character = src.charAt(i);
                if (character == '"') {
                    inQuotes = !inQuotes;
                } else if (!inQuotes && character == ':') {
                    currentKey = sb.toString().trim().replace("^\"|\"$", "");
                    sb.setLength(0);
                } else if (!inQuotes && character == ',') {
                    if (currentKey != null) {
                        map.put(currentKey, sb.toString().trim().replace("^\"|\"$", ""));
                    }
                    currentKey = null;
                    sb.setLength(0);
                } else {
                    sb.append(character);
                }
            }

            if (currentKey != null) {
                map.put(currentKey, sb.toString().trim().replace("^\"|\"$", ""));
            }

            return map;
        } catch (Exception e) {
            throw new CenvJParseException("Unable to parse flat JSON", e);
        }
    }
}
