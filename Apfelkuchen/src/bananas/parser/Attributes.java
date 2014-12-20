package bananas.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Attributes {
   private String raw;
   private HashMap<String, String> attr;

   public Attributes(String parser) {
      raw = parser;
      attr = new HashMap<String, String>();
      parse();
   }

   private static final int STATE_KEY = 0;
   private static final int STATE_CONTENT = 1;
   private static final int STATE_WAIT_CONTENT = 2;
   private static final int STATE_WAIT_KEY = 3;

   public void parse() {
      int state = STATE_WAIT_KEY;
      String key = "";
      String content = "";
      for (int i = 0; i < raw.length(); i++) {
         char c = raw.charAt(i);
         switch (state) {
         case STATE_KEY:
            if (c == '=') {
               state = STATE_WAIT_CONTENT;
            } else {
               key += c;
            }
            break;

         case STATE_WAIT_CONTENT:
            // if (c == '') {
            // state = STATE_CONTENT;
            // } else if (c != ' ') {
            // System.err.println("Sooooooo Bad dangling latter in the html");
            // }
            /* Because FUCK you html ^^^^^^^^ would be better */
            if (c != ' ')
               state = STATE_CONTENT;
            break;
         case STATE_CONTENT:
            if (c == '"') {
               state = STATE_WAIT_KEY;
               key = key.trim();
               if (content.contains("\""))
                  content = content.split("\"")[0];
               content = content.trim();
               attr.put(key, content);
               key = "";
               content = "";
            } else {
               content += c;
            }
            break;
         default:
            if (c != ' ') {
               key += c;
               state = STATE_KEY;
            }
            break;
         }
      }
   }
   public String getValue(String key){
      return attr.get(key);
   }
   @Override
   public String toString() {
      String result = "";
      for (Iterator iterator = attr.entrySet().iterator(); iterator.hasNext();) {
         Entry<String, String> en = (Entry<String, String>) iterator.next();
         result += "(" + en.getKey() + "," + en.getValue() + "),";

      }
      return result;
   }
}
