package bananas;

import java.util.LinkedList;

import bananas.parser.Parser;
import bananas.parser.Tag;

public class Shell {

   public static void main(String[] args) {

      Connector paulSmith = new Connector("http://google.com/");
      paulSmith.start();
      System.out.println(paulSmith.getContent());
      Parser p2 = new Parser(paulSmith.getContent());
      LinkedList<Tag> l = p2.parseLinks();
      for (Tag tag : l) {
         System.out.println("LINK >" + tag.getAttr());
      }
   }

}
