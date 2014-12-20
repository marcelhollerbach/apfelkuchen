package bananas.db;

import java.util.LinkedList;

public class Database {
   private LinkedList<String> pendingfiles;
   private LinkedList<Server> servers;

   public Database() {
      pendingfiles = new LinkedList<String>();
      pendingfiles.add("http://www.reddit.com");

      servers = new LinkedList<Server>();
   }

   public LinkedList<String> getPendingFiles() {
      return pendingfiles;
   }

   /**
    * 
    * @param dns
    * @return null or the server
    */
   public Server getServer(String dns) {
      for (Server serv : servers) {
         if (serv.getDns().equals(dns)) {
            return serv;
         }
      }
      return null;
   }

   /**
    * Add this server
    * 
    * @param dns
    *           dns
    */
   public void addServer(String dns) {
      servers.add(new Server(this, dns));
   }

   public void addedFile(String file) {
      for (String string : pendingfiles) {
         if (string.equals(file)) {
            pendingfiles.remove(file);
            return;
         }
      }
   }
}
