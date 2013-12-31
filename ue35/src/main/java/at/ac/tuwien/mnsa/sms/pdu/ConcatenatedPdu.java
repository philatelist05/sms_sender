package at.ac.tuwien.mnsa.sms.pdu;


import org.apache.commons.lang.ArrayUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ConcatenatedPdu extends Pdu {

    private final byte[] encodedPhoneNumber;
    private final int phoneNumberLength;
    private final byte[] payload;

    private static final int MAX_OCTETS = 140 - 7;

    public static Collection<ConcatenatedPdu> build(String phoneNumber, String text) throws EncodingException {
        ArrayList<ConcatenatedPdu> pdus = new ArrayList<ConcatenatedPdu>();
        byte[] encodedText = Encoder.encode(text);
        int numParts = encodedText.length / MAX_OCTETS + (encodedText.length % MAX_OCTETS == 0 ? 0 : 1);

        for (int offset = 0, chunkNo = 1; offset < encodedText.length; offset += MAX_OCTETS, chunkNo++) {
            byte[] payloadChunk = Arrays.copyOfRange(encodedText, offset, Math.min(offset + MAX_OCTETS, encodedText.length));
            byte[] userDataHeader = buildUserDataHeader(chunkNo, numParts);
            pdus.add(new ConcatenatedPdu(phoneNumber, ArrayUtils.addAll(userDataHeader, payloadChunk)));
        }
        return pdus;
    }

    private static byte[] buildUserDataHeader(int chunkNo, int numChunks) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //User Data Header Length
        stream.write(0x06);
        //Information Element Identifier (2 Bytes)
        stream.write(0x08);
        //Information Element Data Length
        stream.write(0x04);
        //Reference Number 1
        stream.write(0x00);
        //Reference Number 2
        stream.write(0x00);
        //total number of parts
        stream.write(numChunks);
        //part number
        stream.write(chunkNo);

        return stream.toByteArray();
    }

    private ConcatenatedPdu(String phoneNumber, byte[] payload) throws EncodingException {
        this.payload = payload;
        encodedPhoneNumber = super.encodePhoneNumber(phoneNumber);
        phoneNumberLength = phoneNumber.length();
    }

    @Override
    public String getHexEncodedValue() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //SMSC information
        stream.write(0x00);
        //Multipart SMS-SUBMIT message
        stream.write(0x41);
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
        stream.write(payload.length * 8 / 7);
        //User Data
        stream.write(payload);

        return DatatypeConverter.printHexBinary(stream.toByteArray());
    }
}
