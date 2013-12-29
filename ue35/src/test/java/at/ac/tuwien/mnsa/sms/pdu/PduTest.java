package at.ac.tuwien.mnsa.sms.pdu;

import org.testng.annotations.Test;

import javax.xml.bind.DatatypeConverter;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;


public class PduTest {
    @Test
    public void testPackBytes() throws Exception {
        byte[] bytes = Pdu.packBytes(new byte[]{0x68, 0x65, 0x6c, 0x6c, 0x6f, 0x68, 0x65, 0x6c, 0x6c, 0x6f});
        assertArrayEquals(bytes, DatatypeConverter.parseHexBinary("e8329bfd4697d9ec37"));
    }

    @Test
    public void testEncode() throws Exception {
        byte[] actual = Pdu.encode("hellohello");
        byte[] excepted = {0x68, 0x65, 0x6c, 0x6c, 0x6f, 0x68, 0x65, 0x6c, 0x6c, 0x6f};
        assertArrayEquals(excepted, actual);
    }
}
