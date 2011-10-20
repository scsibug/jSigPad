package com.gregheartsfield.jsigpad;

import java.io.*;
import java.nio.charset.Charset;
import java.awt.Color;

public class App {
    public static void main( String[] args ) {
        // Open test json file
        try {
            String json = readFile("greg.json","UTF-8");
            Signature sig = new Signature(json, 395, 200);
            sig.setPenColor(Color.BLUE);
            sig.saveToFile("test.png");
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public static String readFile(String file, String csName) throws IOException {

        Charset cs = Charset.forName(csName);
        // No real need to close the BufferedReader/InputStreamReader
        // as they're only wrapping the stream
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(file);
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return "";
    }
}
