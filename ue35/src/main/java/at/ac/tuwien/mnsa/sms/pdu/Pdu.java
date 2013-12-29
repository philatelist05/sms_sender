package at.ac.tuwien.mnsa.sms.pdu;


import java.util.HashMap;
import java.util.Map;

public abstract class Pdu {

    private static Map<Short, Byte> alphabet;

    static {
        alphabet = new HashMap<Short, Byte>();
        alphabet.put((short) 0x0040, (byte) 0x00);
        alphabet.put((short) 0x00A3, (byte) 0x01);
        alphabet.put((short) 0x0024, (byte) 0x02);
        alphabet.put((short) 0x00A5, (byte) 0x03);
        alphabet.put((short) 0x00E8, (byte) 0x04);
        alphabet.put((short) 0x00E9, (byte) 0x05);
        alphabet.put((short) 0x00F9, (byte) 0x06);
        alphabet.put((short) 0x00EC, (byte) 0x07);
        alphabet.put((short) 0x00F2, (byte) 0x08);
        alphabet.put((short) 0x00E7, (byte) 0x09);
        alphabet.put((short) 0x000A, (byte) 0x0A);
        alphabet.put((short) 0x00D8, (byte) 0x0B);
        alphabet.put((short) 0x00F8, (byte) 0x0C);
        alphabet.put((short) 0x000D, (byte) 0x0D);
        alphabet.put((short) 0x00C5, (byte) 0x0E);
        alphabet.put((short) 0x00E5, (byte) 0x0F);
        alphabet.put((short) 0x0394, (byte) 0x10);
        alphabet.put((short) 0x005F, (byte) 0x11);
        alphabet.put((short) 0x03A6, (byte) 0x12);
        alphabet.put((short) 0x0393, (byte) 0x13);
        alphabet.put((short) 0x039B, (byte) 0x14);
        alphabet.put((short) 0x03A9, (byte) 0x15);
        alphabet.put((short) 0x03A0, (byte) 0x16);
        alphabet.put((short) 0x03A8, (byte) 0x17);
        alphabet.put((short) 0x03A3, (byte) 0x18);
        alphabet.put((short) 0x0398, (byte) 0x19);
        alphabet.put((short) 0x039E, (byte) 0x1A);
        alphabet.put((short) 0x00A0, (byte) 0x1B);
        alphabet.put((short) 0x00C6, (byte) 0x1C);
        alphabet.put((short) 0x00E6, (byte) 0x1D);
        alphabet.put((short) 0x00DF, (byte) 0x1E);
        alphabet.put((short) 0x00C9, (byte) 0x1F);
        alphabet.put((short) 0x0020, (byte) 0x20);
        alphabet.put((short) 0x0021, (byte) 0x21);
        alphabet.put((short) 0x0022, (byte) 0x22);
        alphabet.put((short) 0x0023, (byte) 0x23);
        alphabet.put((short) 0x00A4, (byte) 0x24);
        alphabet.put((short) 0x0025, (byte) 0x25);
        alphabet.put((short) 0x0026, (byte) 0x26);
        alphabet.put((short) 0x0027, (byte) 0x27);
        alphabet.put((short) 0x0028, (byte) 0x28);
        alphabet.put((short) 0x0029, (byte) 0x29);
        alphabet.put((short) 0x002A, (byte) 0x2A);
        alphabet.put((short) 0x002B, (byte) 0x2B);
        alphabet.put((short) 0x002C, (byte) 0x2C);
        alphabet.put((short) 0x002D, (byte) 0x2D);
        alphabet.put((short) 0x002E, (byte) 0x2E);
        alphabet.put((short) 0x002F, (byte) 0x2F);
        alphabet.put((short) 0x0030, (byte) 0x30);
        alphabet.put((short) 0x0031, (byte) 0x31);
        alphabet.put((short) 0x0032, (byte) 0x32);
        alphabet.put((short) 0x0033, (byte) 0x33);
        alphabet.put((short) 0x0034, (byte) 0x34);
        alphabet.put((short) 0x0035, (byte) 0x35);
        alphabet.put((short) 0x0036, (byte) 0x36);
        alphabet.put((short) 0x0037, (byte) 0x37);
        alphabet.put((short) 0x0038, (byte) 0x38);
        alphabet.put((short) 0x0039, (byte) 0x39);
        alphabet.put((short) 0x003A, (byte) 0x3A);
        alphabet.put((short) 0x003B, (byte) 0x3B);
        alphabet.put((short) 0x003C, (byte) 0x3C);
        alphabet.put((short) 0x003D, (byte) 0x3D);
        alphabet.put((short) 0x003E, (byte) 0x3E);
        alphabet.put((short) 0x003F, (byte) 0x3F);
        alphabet.put((short) 0x00A1, (byte) 0x40);
        alphabet.put((short) 0x0041, (byte) 0x41);
        alphabet.put((short) 0x0042, (byte) 0x42);
        alphabet.put((short) 0x0043, (byte) 0x43);
        alphabet.put((short) 0x0044, (byte) 0x44);
        alphabet.put((short) 0x0045, (byte) 0x45);
        alphabet.put((short) 0x0046, (byte) 0x46);
        alphabet.put((short) 0x0047, (byte) 0x47);
        alphabet.put((short) 0x0048, (byte) 0x48);
        alphabet.put((short) 0x0049, (byte) 0x49);
        alphabet.put((short) 0x004A, (byte) 0x4A);
        alphabet.put((short) 0x004B, (byte) 0x4B);
        alphabet.put((short) 0x004C, (byte) 0x4C);
        alphabet.put((short) 0x004D, (byte) 0x4D);
        alphabet.put((short) 0x004E, (byte) 0x4E);
        alphabet.put((short) 0x004F, (byte) 0x4F);
        alphabet.put((short) 0x0050, (byte) 0x50);
        alphabet.put((short) 0x0051, (byte) 0x51);
        alphabet.put((short) 0x0052, (byte) 0x52);
        alphabet.put((short) 0x0053, (byte) 0x53);
        alphabet.put((short) 0x0054, (byte) 0x54);
        alphabet.put((short) 0x0055, (byte) 0x55);
        alphabet.put((short) 0x0056, (byte) 0x56);
        alphabet.put((short) 0x0057, (byte) 0x57);
        alphabet.put((short) 0x0058, (byte) 0x58);
        alphabet.put((short) 0x0059, (byte) 0x59);
        alphabet.put((short) 0x005A, (byte) 0x5A);
        alphabet.put((short) 0x00C4, (byte) 0x5B);
        alphabet.put((short) 0x00D6, (byte) 0x5C);
        alphabet.put((short) 0x00D1, (byte) 0x5D);
        alphabet.put((short) 0x00DC, (byte) 0x5E);
        alphabet.put((short) 0x00A7, (byte) 0x5F);
        alphabet.put((short) 0x00BF, (byte) 0x60);
        alphabet.put((short) 0x0061, (byte) 0x61);
        alphabet.put((short) 0x0062, (byte) 0x62);
        alphabet.put((short) 0x0063, (byte) 0x63);
        alphabet.put((short) 0x0064, (byte) 0x64);
        alphabet.put((short) 0x0065, (byte) 0x65);
        alphabet.put((short) 0x0066, (byte) 0x66);
        alphabet.put((short) 0x0067, (byte) 0x67);
        alphabet.put((short) 0x0068, (byte) 0x68);
        alphabet.put((short) 0x0069, (byte) 0x69);
        alphabet.put((short) 0x006A, (byte) 0x6A);
        alphabet.put((short) 0x006B, (byte) 0x6B);
        alphabet.put((short) 0x006C, (byte) 0x6C);
        alphabet.put((short) 0x006D, (byte) 0x6D);
        alphabet.put((short) 0x006E, (byte) 0x6E);
        alphabet.put((short) 0x006F, (byte) 0x6F);
        alphabet.put((short) 0x0070, (byte) 0x70);
        alphabet.put((short) 0x0071, (byte) 0x71);
        alphabet.put((short) 0x0072, (byte) 0x72);
        alphabet.put((short) 0x0073, (byte) 0x73);
        alphabet.put((short) 0x0074, (byte) 0x74);
        alphabet.put((short) 0x0075, (byte) 0x75);
        alphabet.put((short) 0x0076, (byte) 0x76);
        alphabet.put((short) 0x0077, (byte) 0x77);
        alphabet.put((short) 0x0078, (byte) 0x78);
        alphabet.put((short) 0x0079, (byte) 0x79);
        alphabet.put((short) 0x007A, (byte) 0x7A);
        alphabet.put((short) 0x00E4, (byte) 0x7B);
        alphabet.put((short) 0x00F6, (byte) 0x7C);
        alphabet.put((short) 0x00F1, (byte) 0x7D);
        alphabet.put((short) 0x00FC, (byte) 0x7E);
        alphabet.put((short) 0x00E0, (byte) 0x7F);
    }

    public static byte[] encode(String text) throws EncodingException {
        byte[] encoded = new byte[text.length()];
        for (int i = 0; i < encoded.length; i++) {
            char character = text.charAt(i);
            if (!alphabet.containsKey((short)character)) {
                throw new EncodingException("There is no mapping for character " + String.valueOf(character));
            }
            encoded[i] = alphabet.get((short)character);
        }
        return encoded;
    }

    public static byte[] packBytes(byte[] unpackedBytes) {
        int shiftedLength = unpackedBytes.length - (unpackedBytes.length / 8);
        int shiftOffset = 0;
        int shiftIndex = 0;
        int moveOffset = 7;
        int moveIndex = 1;
        int packIndex = 0;
        byte[] packedBytes = new byte[shiftedLength];
        byte[] shiftedBytes = new byte[shiftedLength];

        for (byte b : unpackedBytes) {
            if (shiftOffset == 7) {
                shiftOffset = 0;
            } else {
                shiftedBytes[shiftIndex++] = (byte) (b >> shiftOffset++);
            }

            if (moveOffset == 0) {
                moveOffset = 7;
            } else {
                if (moveIndex != unpackedBytes.length) {
                    //pack it
                    int extractedByte = unpackedBytes[moveIndex] << moveOffset--;
                    extractedByte = extractedByte & 0xff;
                    int movedBitsByte = extractedByte | shiftedBytes[packIndex];

                    packedBytes[packIndex] = (byte) movedBitsByte;

                    packIndex++;
                } else {
                    //if there is only one byte left then take that
                    packedBytes[packIndex] = shiftedBytes[packIndex];
                }
            }
            moveIndex++;
        }

        return packedBytes;
    }

}
