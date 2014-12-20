package bananas.db;

public class Server {

   private Database b;
   private String dns;

   public Server(Database b, String dns) {
      this.b = b;
      setDns(dns);
   }

   public String getDns() {
      return dns;
   }

   public void setDns(String dns) {
      this.dns = dns;
   }

   public ServerFile getFile(String file) {
      return null;
   }

   public void addFile(String file) {
      b.addedFile(file);
   }
}
