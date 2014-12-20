package bananas.parser;

import java.util.LinkedList;

import bananas.Job;
import bananas.JobListener;

/**
 * This just parses simple html code It will move tags into TAG object with tag
 * name, attributes, and the tag name
 * 
 * @author buh5m4n
 *
 */
public class HTMLParser extends Job {
   private Side side;
   private String tag, attr, content;

   public static final int STATE_IN_TAG_CONTENT = 0;
   public static final int STATE_IN_ATTR = 1;
   public static final int STATE_IN_TAG_END = 2;
   public static final int STATE_IN_TAG_START = 3;
   public static final int STATE_FAULTY_PART = 4;

   public static final String[] TAGS_WONT_CLOSE = {"br", "meta", "hr", "img",
         "wbr", "area", "img", "input", "option", "link", "source" };
   public static final String[] TAGS_IGNORE = {"script" };
   private LinkedList<Tag> parsedTags;

   public HTMLParser(Side side, JobListener l) {
      super(l);
      this.side = side;
      attrReset();
   }

   private static int seqAppeares(String part, String content) {
      int i = 0;
      int lindex = -1;
      do {
         lindex = content.indexOf(part, lindex + 1);
         i++;
      } while (lindex != -1);
      return i;
   }

   private LinkedList<Tag> parseString(String txt) throws Exception {
      LinkedList<Tag> tags = new LinkedList<Tag>();
      int state = -1;
      int laststable = 0;
      for (int i = 0; i < txt.length(); i++) {
         char c = txt.charAt(i);

         switch (state) {
         case STATE_FAULTY_PART:
            if (c == '>') {
               state = -1;
            } else {

            }
            break;
         case STATE_IN_TAG_START:
            if (c == ' ') {
               state = STATE_IN_ATTR;
            } else if (c == '!' && txt.charAt(i - 1) == '<') {
               state = STATE_FAULTY_PART;
            } else if (c == '>') {
               if (wontClose(tag)) {
                  pushTag(tags);
                  laststable = i;
                  state = -1;
               } else
                  state = STATE_IN_TAG_CONTENT;
            } else if (c == '/') {
               state = STATE_FAULTY_PART;
            } else {
               tag += c;
            }
            break;
         case STATE_IN_ATTR:
            if (c == '>') {
               if (wontClose(tag)) {
                  pushTag(tags);
                  laststable = i;
                  state = -1;
               } else
                  state = STATE_IN_TAG_CONTENT;
            } else {
               attr += c;
            }
            break;
         case STATE_IN_TAG_CONTENT:
            String searched = "</" + tag + ">";
            String start = "<" + tag;
            if (i + searched.length() > txt.length()) {
               System.err.println("We will run out of the string!!");
               System.err.println("At tag {" + tag + "} part: {..."
                     + txt.substring(laststable) + "}");
               System.err.println("We better return, FUCK THIS SITE!");
               content += txt.substring(i);
               pushTag(tags);
               return tags;
            }
            String got = txt.substring(i, i + searched.length());
            if (got.equals(searched)
                  && seqAppeares(start, content) == seqAppeares(searched,
                        content)) {
               pushTag(tags);
               state = -1;
               laststable = i;
               i += searched.length() - 1;
            } else {
               content += c;
            }
            break;
         default:
            if (c == '<') {
               state = STATE_IN_TAG_START;
            } else {

            }
            break;
         }
      }
      return tags;
   }

   private LinkedList<Tag> parse(String txt) throws Exception {

      LinkedList<Tag> result = new LinkedList<Tag>();
      LinkedList<Tag> res = parseString(txt);
      if ((res == null) || res.isEmpty())
         return null;
      for (Tag tag : res) {
         if (ignore(tag.getName()))
            continue;
         LinkedList<Tag> r = parse(tag.getContent());
         result.add(tag);
         if (r != null)
            result.addAll(r);
      }
      return result;
   }

   public void parseLinks() {
      LinkedList<Tag> tags = null;
      LinkedList<Tag> result = new LinkedList<Tag>();
      try {
         tags = parse(side.getContent());
      } catch (Exception e) {
         e.printStackTrace();
      }
      for (Tag tag : tags) {
         if (tag.getName().equals("a"))
            result.add(tag);
      }
      parsedTags = tags;
   }

   private static boolean wontClose(String tag) {
      for (String string : TAGS_WONT_CLOSE) {
         if (string.equals(tag))
            return true;
      }
      return false;
   }

   private static boolean ignore(String tag) {
      for (String string : TAGS_IGNORE) {
         if (string.equals(tag))
            return true;
      }
      return false;
   }

   private void attrReset() {
      tag = "";
      attr = "";
      content = "";
   }

   @Override
   public void run() {
      parseLinks();
      side.setTags(parsedTags);
      isDone(side);
   }

   public LinkedList<Tag> getParsedTags() {
      return parsedTags;
   }

   private void pushTag(LinkedList<Tag> tags) {
      tags.add(new Tag(tag, attr, content));
      attrReset();
   }
}
