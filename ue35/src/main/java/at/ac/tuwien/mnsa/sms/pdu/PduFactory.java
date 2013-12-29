package at.ac.tuwien.mnsa.sms.pdu;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PduFactory {

    public static Collection<Pdu> buildPdu(String csvFileLocation) throws IOException {
        Collection<Pdu> pdus = new ArrayList<Pdu>();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(csvFileLocation);

        Scanner scanner = new Scanner(inputStream);
        scanner.skip(Pattern.compile("\\s*"));

        while (scanner.hasNext()) {
            String next = scanner.next();
            String[] parts = next.split(",");
            if (parts.length != 2) {
                throw new IllegalStateException("Wrong CSV format. Assuming '<text>,<text>'");
            }

            String phoneNumber = parts[0].replaceAll("[^0-9]", "");
            String text = parts[1].trim();

            if (text.length() > 160) {
                Iterable<String> chunks = splitIntoEqualChunks(text, 160);
                for (String chunk : chunks) {
                    pdus.add(new ConcatenatedPdu(phoneNumber, chunk));
                }
            } else {
                pdus.add(new SimplePdu(phoneNumber, text));
            }
        }

        scanner.close();
        return pdus;
    }

    private static Iterable<String> splitIntoEqualChunks(String text, int size) {
        List<String> ret = new ArrayList<String>((text.length() + size - 1) / size);

        for (int start = 0; start < text.length(); start += size) {
            ret.add(text.substring(start, Math.min(text.length(), start + size)));
        }
        return ret;
    }
}
