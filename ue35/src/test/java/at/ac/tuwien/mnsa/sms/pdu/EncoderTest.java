package at.ac.tuwien.mnsa.sms.pdu;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.bind.DatatypeConverter;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;


public class EncoderTest {

    @Test(dataProvider = "provider")
    public void testEncoding(String actualText, String expectedText) throws Exception {
        byte[] actual = Encoder.encode(actualText);
        byte[] excepted = DatatypeConverter.parseHexBinary(expectedText);
        assertArrayEquals(excepted, actual);
    }

    @DataProvider(name = "provider")
    public Object[][] createPduData() throws Exception {
        return new Object[][]{
                { "hellohello", "e8329bfd4697d9ec37" },
        };
    }
}
