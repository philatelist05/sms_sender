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
                pdus.addAll(ConcatenatedPdu.build(phoneNumber, text));
            } else {
                pdus.add(new SimplePdu(phoneNumber, text));
            }
        }

        scanner.close();
        return pdus;
    }
}
