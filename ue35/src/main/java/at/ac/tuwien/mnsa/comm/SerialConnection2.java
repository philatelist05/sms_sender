package at.ac.tuwien.mnsa.comm;


import gnu.io.NRSerialPort;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;

public class SerialConnection2 implements Closeable {

    private static class SerialConnectionProperties {

        private final int baudrate;

        private final int parity;
        private final int stopbits;
        private final int databits;
        private final String portString;
        SerialConnectionProperties() throws ConfigurationException {
            PropertiesConfiguration configuration
                    = new PropertiesConfiguration("sendsms.properties");
            portString = configuration.getString("port", "COM1");
            databits = configuration.getInt("databits", 8);
            stopbits = configuration.getInt("stopbits", 1);
            parity = configuration.getInt("parity", 0);
            baudrate = configuration.getInt("baudrate", 9600);
        }

    }

    private final NRSerialPort port;

    private SerialConnection2(SerialConnectionProperties properties) throws SerialConnectionException {
        port = new NRSerialPort(properties.portString, properties.baudrate);
        port.connect();
    }

    public static SerialConnection2 open() throws SerialConnectionException {
        try {
            return new SerialConnection2(new SerialConnectionProperties());
        } catch (ConfigurationException e) {
            throw new SerialConnectionException("Failed to load properties ", e);
        }
    }

    public InputStream getInputStream() throws IOException {
        return new BufferedInputStream(port.getInputStream());
    }

    public OutputStream getOutputStream() throws IOException {
        return new BufferedOutputStream(port.getOutputStream());
    }

    public void close() throws IOException {
        port.disconnect();
    }
}
