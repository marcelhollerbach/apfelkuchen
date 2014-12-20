package bananas;

import java.util.LinkedList;

import bananas.db.Database;
import bananas.parser.HTMLParser;
import bananas.parser.Side;
import bananas.parser.Tag;

public class StartUp {
   public static void main(String[] args) {
      Database b = new Database();
      final ThreadQueue<Connector> connectorqueue = new ThreadQueue<Connector>();
      final ThreadQueue<HTMLParser> parserqueue = new ThreadQueue<HTMLParser>();
      final JobListener parserFinished = new JobListener() {

         @Override
         public void idDone(Side result) {
            LinkedList<Tag> parsedTags = result.getTags();
            for (Tag tag : parsedTags) {
               if (tag.getName().equals("a")) {
                  String value;
                  if ((value = tag.getAttr().getValue("href")) != null)
                     System.out.println("Link to " + value);
               }
            }
         }
      };
      final JobListener connectorEnded = new JobListener() {

         @Override
         public void idDone(Side result) {
            parserqueue.schedule(new HTMLParser(result, parserFinished));
         }
      };
      for (String string : b.getPendingFiles()) {
         connectorqueue.schedule(new Connector(string, connectorEnded));
      }
   }
}
