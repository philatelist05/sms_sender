package at.ac.tuwien.mnsa.sms.comm;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import javax.comm.*;
import java.io.*;

public class SerialConnection implements Closeable {

    private static class SerialConnectionProperties {

        private final int baudrate;
        private final int parity;
        private final int stopbits;
        private final int databits;
        private final String portString;

        SerialConnectionProperties(Configuration configuration) throws ConfigurationException {
            portString = configuration.getString("port", "COM1");
            databits = configuration.getInt("databits", 8);
            stopbits = configuration.getInt("stopbits", 1);
            parity = configuration.getInt("parity", 0);
            baudrate = configuration.getInt("baudrate", 9600);
        }

    }

    private SerialPort port;

    private SerialConnection(SerialConnectionProperties properties) throws SerialConnectionException {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(properties.portString);
            port = (SerialPort) portIdentifier.open(this.getClass().getName(), 1000);
            port.setSerialPortParams(properties.baudrate, properties.databits, properties.stopbits, properties.parity);
            port.enableReceiveTimeout(5000);
            if (!port.isReceiveTimeoutEnabled()) {
                throw new UnsupportedCommOperationException("Unable to set receive timeout");
            }
        } catch (NoSuchPortException ex) {
            throw new SerialConnectionException("Port " + properties.portString + " does not exist", ex);
        } catch (PortInUseException ex) {
            throw new SerialConnectionException("Port " + properties.portString + " is already in use", ex);
        } catch (UnsupportedCommOperationException ex) {
            throw new SerialConnectionException("Can not set connection parameters", ex);
        }
    }

    public static SerialConnection open(Configuration configuration) throws SerialConnectionException {
        try {
            return new SerialConnection(new SerialConnectionProperties(configuration));
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
        port.close();
    }
}
