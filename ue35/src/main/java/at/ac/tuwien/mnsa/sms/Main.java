package at.ac.tuwien.mnsa.sms;

import at.ac.tuwien.mnsa.sms.comm.SerialConnection;
import at.ac.tuwien.mnsa.sms.pdu.Pdu;
import at.ac.tuwien.mnsa.sms.pdu.PduFactory;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws Exception {
        SerialConnection serialConnection = null;

        try {
            Configuration config = getConfig("sendsms.properties");
            serialConnection = SerialConnection.open(config);

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

            Collection<Pdu> pdus = PduFactory.buildPdu(config);
            for (Pdu pdu : pdus) {
                String encodedValue = pdu.getHexEncodedValue();
                int length = (encodedValue.length() - 2) / 2;
                writer.print("AT+CMGS=" + length + "\r");
                writer.flush();
                println(reader);
                writer.print(encodedValue);
                writer.print((char) 26);
                writer.flush();
                println(reader);
            }
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
        } catch (IOException ignored) {
            //This Exception is ignored because
            //an IOException is always thrown in case
            //of a timeout (which is true for EVERY read)
        }
    }

    private static Configuration getConfig(String location) throws IOException{
        try {
            return new  PropertiesConfiguration(location);
        } catch (ConfigurationException e) {
            throw new IOException("Unable to load configuration " + location, e);
        }
    }
}
