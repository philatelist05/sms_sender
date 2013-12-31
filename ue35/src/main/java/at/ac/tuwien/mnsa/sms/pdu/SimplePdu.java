package at.ac.tuwien.mnsa.sms.pdu;


import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SimplePdu extends Pdu {

    private final byte[] encodedText;
    private final int textLength;
    private final byte[] encodedPhoneNumber;
    private final int phoneNumberLength;

    public SimplePdu(String phoneNumber, String text) throws EncodingException {
        textLength = text.length();
        encodedText = Encoder.encode(text);
        encodedPhoneNumber = super.encodePhoneNumber(phoneNumber);
        phoneNumberLength = phoneNumber.length();
    }

    @Override
    public String getHexEncodedValue() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //SMSC information
        stream.write(0x00);
        //SMS-SUBMIT message
        stream.write(0x01);
        //TP-Message-Reference
        stream.write(0x00);
        //Address Length
        stream.write(phoneNumberLength);
        //Type of Address (International)
        stream.write(0x91);
        //Phone number
        stream.write(encodedPhoneNumber);
        //Protocol identifier
        stream.write(0x00);
        //Encoding scheme (7-Bit)
        stream.write(0x00);
        //TP-User-Data-Length(number of septets)
        stream.write(textLength);
        //TP-User-Data
        stream.write(encodedText);

        return DatatypeConverter.printHexBinary(stream.toByteArray());
    }
}
