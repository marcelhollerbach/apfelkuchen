package bananas.parser;

import java.net.URL;
import java.util.LinkedList;

public class Side {
   private URL url;
   private LinkedList<Tag> tags;
   private String content;

   public Side(URL url, String content) {
      this.content = content;
      this.url = url;
   }

   public void setTags(LinkedList<Tag> tags) {
      this.tags = tags;
   }

   public URL getUrl() {
      return url;
   }

   public boolean isFinished() {
      return (tags != null);
   }

   public LinkedList<Tag> getTags() {
      return tags;
   }

   public String getContent() {
      return content;
   }
}
