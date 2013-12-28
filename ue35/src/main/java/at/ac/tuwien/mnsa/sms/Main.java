package at.ac.tuwien.mnsa.sms;

import at.ac.tuwien.mnsa.sms.comm.SerialConnection;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        SerialConnection serialConnection = null;

        try {
            serialConnection = SerialConnection.open();

            InputStream inputStream = serialConnection.getInputStream();
            OutputStream outputStream = serialConnection.getOutputStream();

            PrintWriter writer = new PrintWriter(outputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            writer.print("ATZ\r");
            writer.flush();
            println(reader);

            writer.print("AT+CMGF=0\r");
            writer.flush();
            println(reader);

            writer.print("AT+CSMS=0\r");
            writer.flush();
            println(reader);

            writer.print("AT+CMGS=23\r");
            writer.flush();
            println(reader);
            writer.print("0011000C913466944789400000AA0AE8329BFD4697D9EC37");
            writer.print((char) 26);
            writer.flush();
            println(reader);

        } finally {
            if (serialConnection != null) {
                serialConnection.close();
            }
        }
    }

    private static void println(BufferedReader reader) {
        try {
            char ch;
            while ((ch = (char) reader.read()) != -1) {
                System.out.print(ch);
            }
            System.out.println();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

}
