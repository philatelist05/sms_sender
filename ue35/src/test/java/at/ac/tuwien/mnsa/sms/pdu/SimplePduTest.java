package at.ac.tuwien.mnsa.sms.pdu;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class SimplePduTest {

    @Test(dataProvider = "PduDataProvider")
    public void testGetHexEncodedValue(Pdu pdu, String expectedResult) throws Exception {
        String actual = pdu.getHexEncodedValue();
        assertEquals(actual, expectedResult);
    }

    @DataProvider(name = "PduDataProvider")
    public Object[][] createPduData() throws Exception {
        return new Object[][] {
                { new SimplePdu("46708251358", "hellohello"), "0001000B916407281553F800000AE8329BFD4697D9EC37" },
                { new SimplePdu("15125551234", "Howdy y'all!"), "0001000B915121551532F400000CC8F79D9C07E54F61363B04" },
                { new SimplePdu("01621234567", "Hallo Welt"), "0001000B911026214365F700000AC8309BFD065DCB6C3A" },
                { new SimplePdu("49123456789", "Konvertiert Textnachrichten ins PDU Format"), "0001000B919421436587F900002ACBB7DB5E96D3D365391D442DE3E9EEF0182D4F8FD1F4B21B9476CF41506215647CCBDB613A" },
                { new SimplePdu("919867752239", "uYxKQU1I/wIEAgEQAR7y+JT0LQpdAQCWAAABd/yiXwKuAwvY5OeCPsa70o0USlMLC0aCrlPnsUxEj0d2xKNaBY9bj0p3hLCfFnNgtjXyyNxEYKrA1pD7LKuwVhlS21qInQvLPyIWfHI58atCA+JOHVs24QAAAAAA"), "0001000C911989765722930000A0F52C7E19ADC692AF7BB2183C17A341E92DBF525261CC289C1C8C0EAFC16050487EE5D3D8FBB21EBCDBB3B56779089D876FB037AC3A653799435878286743DDF32ABEA8869165F8A5332CCCE6C46A187C86660ECD46B7F34C5763F37927BE985DCA833138F1C65CD6EF56347B2A8BC593EEA89D09CD27AF6664B2860BD387C195F289B4CE65B46830180C0683" },
        };
    }
}
