package at.ac.tuwien.mnsa;

import at.ac.tuwien.mnsa.comm.SerialConnection;
import at.ac.tuwien.mnsa.comm.SerialConnection2;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        SerialConnection2 serialConnection = SerialConnection2.open();

        InputStream inputStream = serialConnection.getInputStream();
        OutputStream outputStream = serialConnection.getOutputStream();

        PrintWriter writer = new PrintWriter(outputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        writer.print("ATD43273286790");
        writer.print("\r\n");
        writer.flush();
        String string = reader.readLine();
        System.out.println(string);

        reader.close();
        writer.close();
    }
}
