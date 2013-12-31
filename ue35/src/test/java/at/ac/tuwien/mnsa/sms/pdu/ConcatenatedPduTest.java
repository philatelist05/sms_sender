package at.ac.tuwien.mnsa.sms.pdu;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.testng.Assert.assertEquals;

public class ConcatenatedPduTest {

    @Test(dataProvider = "PduDataProvider")
    public void testGetHexEncodedValue(Pdu pdu, String expectedResult) throws Exception {
        String actual = pdu.getHexEncodedValue();
        assertEquals(actual, expectedResult);
    }

    @DataProvider(name = "PduDataProvider")
    public Object[][] createPduData() throws Exception {
        Collection<ConcatenatedPdu> concatenatedPdus = ConcatenatedPdu.build("15125551234", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        Object[] objects = concatenatedPdus.toArray();
        assertEquals(objects.length, 3);

        return new Object[][] {
                { objects[0], "0041000B915121551532F40000A0050003000301986F79B90D4AC3E7F53688FC66BFE5A0799A0E0AB7CB741668FC76CFCB637A995E9783C2E4343C3D4F8FD3EE33A8CC4ED359A079990C22BF41E5747DDE7E9341F4721BFE9683D2EE719A9C26D7DD74509D0E6287C56F791954A683C86FF65B5E06B5C36777181466A7E3F5B0AB4A0795DDE936284C06B5D3EE741B642FBBD3E1360B14AFA7E7" },
                { objects[1], "0041000B915121551532F40000A005000300030240EEF79C2EAF9341657C593E4ED3C3F4F4DB0DAAB3D9E1F6F80D6287C56F797A0E72A7E769509D0E0AB3D3F17A1A0E2AE341E53068FC6EB7DFE43768FC76CFCBF17A98EE22D6D37350B84E2F83D2F2BABC0C22BFD96F3928ED06C9CB7079195D7693CBF2341D947683EC6F761D4E0FD3CB207B999DA683CAF37919344EB3D9F53688FC66BFE5" },
                { objects[2], "0041000B915121551532F4000090050003000303CAA0721D64AE9FD3613AC85D67B3C32078589E0ED3EB7257113F2EC3E9E5BA1C344FBBE9A0F7781C2E8FC374D0B80E4F93C3F4301DE47EBB4170F93B4D2EBBE92CD0BCEEA683D26ED0B8CE868741F17A1AF4369BD3E37418442ECFCBF2BA9B0E6ABFD9EC341D1476A7DBA03419549ED341ECB0F82DAFB75D" },
        };
    }
}
