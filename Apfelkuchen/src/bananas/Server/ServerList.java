package bananas.Server;

import java.util.ArrayList;

public class ServerList {

    public ArrayList<Server> server;

    public ServerList() {

    }

    public ServerList(Server item) {
        server = new ArrayList<Server>();
        server.add(item);
    }

}
