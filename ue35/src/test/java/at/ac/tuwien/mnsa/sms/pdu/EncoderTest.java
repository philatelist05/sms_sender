package at.ac.tuwien.mnsa.sms.pdu;

import org.testng.annotations.Test;

import javax.xml.bind.DatatypeConverter;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;


public class EncoderTest {

    @Test
    public void testEncoding() throws Exception {
        byte[] actual = Encoder.encode("hellohello");
        byte[] excepted = DatatypeConverter.parseHexBinary("e8329bfd4697d9ec37");
        assertArrayEquals(excepted, actual);
    }
}
