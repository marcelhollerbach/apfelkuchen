package bananas.Server;

public class Server {

    public ServerDictionary dict;
    public String url;

    public Server(String url, ServerDictionary dict) {
        this.dict = dict;
        this.url = url;
    }
}
