package bananas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class Connector extends Job {
    private URL url;
    public String content = "";

    public Connector(String url, JobListener l) {
        super(l);
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getContent() {
        return content;
    }

    public void start() {
        URLConnection connection;
        InputStream in = null;
        try {
            connection = url.openConnection();
            in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in));
            while (reader.ready()) {
                content += reader.readLine();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e2) {
                System.out.println("Bananananas!");
            }
        }
    }

    @Override
    public void run() {
        start();
        isDone(getContent());
    }
}
