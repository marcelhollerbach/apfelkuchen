package bananas.parser;

public class Tag {
   private String name;
   private Attributes attr;
   private String content;

   public Tag(String name, String attr, String content) {
      super();
      this.name = name;
      this.attr = new Attributes(attr);
      this.content = content;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Attributes getAttr() {
      return attr;
   }

   public void setAttr(String attr) {
      this.attr = new Attributes(attr);
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   @Override
   public String toString() {
      return "[" + name + "," + attr + "," + content + "]";
   }
}
