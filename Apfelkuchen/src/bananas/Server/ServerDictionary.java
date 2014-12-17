package bananas.Server;

import java.util.ArrayList;

public class ServerDictionary {

    public ArrayList<File> list;

    public ServerDictionary(File file) {
        this.list = new ArrayList<File>();
        list.add(file);
    }

    public ServerDictionary() {
        this.list = new ArrayList<File>();
    }

    public void add(File file) {
        this.list.add(file);
    }

}
