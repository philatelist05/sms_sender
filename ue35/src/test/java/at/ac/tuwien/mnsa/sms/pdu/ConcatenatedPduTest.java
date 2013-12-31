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
                { objects[0], "0041000B915121551532F40000A006080400000301CCB7BCDC06A5E1F37A1B447EB3DF72D03C4D0785DB653A0B347EBBE7E531BD4CAFCB4161721A9E9EA7C769F7195466A7E92CD0BC4C0691DFA072BA3E6FBFC9207AB90D7FCB4169F7384D4E93EB6E3AA84E07B1C3E2B7BC0C2AD341E437FB2D2F83DAE1B33B0C0AB3D3F17AD855A583CAEE741B142683DA6977BA0DB297DDE9709B058AD7D3" },
                { objects[1], "0041000B915121551532F40000A0060804000003027390FB3DA7CBEB6450195F968FD3F4303DFD7683EA6C76B83D7E83D861F15B9E9E83DCE9791A54A783C2EC74BC9E8683CA7850390C1ABFDBED37F90D1ABFDDF372BC1EA6BB40C47A7A0E0AD7E965505A5E979741E437FB2D07A5DD2079192E2FA3CB6E72599EA683D26E90FDCDAEC3E9617A19642FB3D37450793E2F83C66936BBDE0691DF" },
                { objects[2], "0041000B915121551532F400009506080400000303ECB7BC0C2AD741E6FA391DA683DC75363B0C8287E5E930BD2E77818AF871194E2FD7E5A079DA4D07BDC7E370791CA683C675789A1CA687E920F7DB0D82CBDF6972D94D6781E675371D947683C675363C0C8AD7D3A0B7D99C1EA7C32072795E96D7DD7450FBCD66A7E9A0B03BDD06A5C9A0F29C0E6287C56F79BDED02" },
        };
    }
}
