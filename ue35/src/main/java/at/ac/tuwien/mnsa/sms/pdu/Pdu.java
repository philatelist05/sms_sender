package at.ac.tuwien.mnsa.sms.pdu;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class Pdu {
    public abstract String getHexEncodedValue() throws IOException;

    protected byte[] encodePhoneNumber(String number) {
        ByteArrayOutputStream ret = new ByteArrayOutputStream();
        for (int i = 0; i < number.length(); i += 2) {
            int value;
            if (i + 1 < number.length()) {
                value = 16 * intValue(number.charAt(i + 1)) + intValue(number.charAt(i));
            } else {
                value = 16 * 15 + intValue(number.charAt(i));
            }
            ret.write(value);
        }
        return ret.toByteArray();
    }

    private int intValue(char c) {
        return c - '0';
    }
}
