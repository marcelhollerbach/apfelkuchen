package bananas.db;

import java.util.LinkedList;

public class Database {
   public Database() {

   }

   public LinkedList<String> getPendingFiles() {
      LinkedList<String> res = new LinkedList<String>();
      res.add("http://www.google.de");
      return res;
   }

   /**
    * 
    * @param dns
    * @return null or the server
    */
   public Server getServer(String dns) {
      return null;
   }

   /**
    * Add this server
    * @param dns dns
    */
   public void addServer(String dns) {

   }

}
