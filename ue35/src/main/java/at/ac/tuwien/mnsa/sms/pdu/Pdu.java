package at.ac.tuwien.mnsa.sms.pdu;


import java.io.IOException;

public interface Pdu {
    public String getHexEncodedValue() throws IOException;
}
