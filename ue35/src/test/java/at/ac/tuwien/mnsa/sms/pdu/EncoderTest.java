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
                { "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis", "986F79B90D4AC3E7F53688FC66BFE5A0799A0E0AB7CB741668FC76CFCB637A995E9783C2E4343C3D4F8FD3EE33A8CC4ED359A079990C22BF41E5747DDE7E9341F4721BFE9683D2EE719A9C26D7DD74509D0E6287C56F791954A683C86FF65B5E06B5C36777181466A7E3F5B0AB4A0795DDE936284C06B5D3EE741B642FBBD3E1360B14AFA7E7" },
        };
    }
}
