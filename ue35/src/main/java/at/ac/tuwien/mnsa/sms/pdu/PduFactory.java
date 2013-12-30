package at.ac.tuwien.mnsa.sms.pdu;


import org.apache.commons.configuration.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

public final class PduFactory {

    public static Collection<Pdu> buildPdu(Configuration configuration) throws IOException, EncodingException {
        Collection<Pdu> pdus = new ArrayList<Pdu>();
        String csvFileLocation = configuration.getString("csvfile");
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(csvFileLocation);

        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\r\n");
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
                String chunks[] = splitIntoEqualChunks(text, 160);
                for (int chunkNo = 0; chunkNo < chunks.length; chunkNo++) {
                    pdus.add(new ConcatenatedPdu(phoneNumber, chunks[chunkNo], chunkNo));
                }
            } else {
                pdus.add(new SimplePdu(phoneNumber, text));
            }
        }

        scanner.close();
        return pdus;
    }

    private static String[] splitIntoEqualChunks(String text, int size) {
        String[] ret = new String[(text.length() + size - 1) / size];

        int i = 0;
        for (int start = 0; start < text.length(); start += size) {
            ret[i++] = text.substring(start, Math.min(text.length(), start + size));
        }
        return ret;
    }
}
