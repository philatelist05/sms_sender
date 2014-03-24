SMS senden via AT-Befehle
==========
### Kurzfassung
Im Rahmen der Lehrveranstaltung _(Mobile) Network Service Applications_ wurde eine Java Ap-
plikation entwickelt welche es ermöglicht, SMS-Nachrichten beliebiger Länge zu versenden. Der
Nachrichtentext wird bei der Übertragung mit dem [GSM 7-Bit Alphabet](http://www.itu.int/rec/T-REC-T.50-199209-I/en) enkodiert.

### Einleitung
Aufbauend der Aufgabenstellung _Entwicklung einer Applikation zum Senden von SMS_ wurde
eine Java-Applikation entwickelt welche es ermöglicht, SMS-Nachrichten beliebiger Länge zu
versenden. Das Programm bedient sich sogenannter [AT-Befehle] (http://portal.etsi.org/action/pu/19991214/19991214.htm) zur Kommunikation mit
dem Mobiltelefon. Die Datenübertragung geschieht über eine serielle Schnittstelle.

### Problemstellung/Zielsetzung
Die Java-Applikation kommuniziert mit dem Handy (Nokia 6212) über die serielle Schnittstelle.
Intern werden SMS-Nachrichten unter Verwendung sogenannter AT-Befehle versendet.

#### AT-Befehle
AT-Befehle werden in 1) _Basic commands_, 2) _Extended commands_, 3) _Parameter type commands_ und 4) _Action type commands_
unterteilt [GSM ETSI. 07.07](http://portal.etsi.org/action/pu/19991214/19991214.htm). Während _Basic commands_ auf jedem Modem
verfügbar sind, kann die Verfügbarkeit bzw. die genaue Syntax der _Extended commands_ mit
nachgestelltem Fragezeichen abgefragt werden. Letztere verfügen zusätzlich noch über den Präfix `AT+`.
Ein erfolgreich ausgeführter Befehl wird vom Modem mit `OK` quittiert, andernfalls schickt
es `ERROR` und bricht die Abarbeitung weiterer Befehle ab.

Tabelle 1 listet alle notwendigen AT-Befehle zum erfolgreichen Versenden von SMS-Nachrichten

AT-Befehl | Beschreibung
------------ | -------------
`ATZ` | Befehl zum Zurücksetzten des Modems.
`AT+CMGF` | Definiert, welches Format für die SMS-Übertragung verwendet wird. (0 für _PDU-Mode_ und 1 für _Text-Mode_)
`AT+CMGS`  | Versendet direkt eine SMS. Die genaue Syntax ist abhängig vom ÜbertragungsMode.
Tabelle 1: Menge aller notwendigen AT-Befehle zum Versenden von SMS-Nachrichten


#### PDU Mode
Eine wichtige Anforderung für die Implementierung war das Versenden der SMS-Nachrichten
im PDU-Mode. Dieser ermöglicht die Übertragung von Binärdaten welche als hexadezimale
Zeichenkette angegeben werden können.

Die zwei wichtigsten Nachrichtentypen im PDU-Mode sind SUBMIT-Nachrichten und DELIVER-
Nachrichten. Abbildung 1 zeigt schematisch den Aufbau einer SMS-SUBMIT-Nachricht im
PDU-Mode (in Byte Blöcken).

Um auch Nachrichten mit einer Länge größer als 160 Zeichen versenden zu können, wurde
ein eigener Nachrichtentyp spezifiziert. Der Unterschied zum herkömmlichen Nachrichtentyp ist
der sogenannte _User Data Header_ welcher am Beginn des Payloads zu finden ist. Abbildung 2
zeight schematisch den Aufbau des User Data Headers.

#### Text Kodierung
Eine weitere Anforderung war die Verwendung des [GSM 7-Bit Alphabets](http://www.itu.int/rec/T-REC-T.50-199209-I/en) für die Textkodierung.
Diese ermöglicht die Übertragung von 128 unterschiedlichen ASCII-Zeichen. Nach der
Umwandlung der Zeichen in Septetten (7-Bit Blöcke) müssen diese noch mit einem geeigneten
Algorithmus in Oktetten (8-Bit Blöcke) gepackt werden, da Daten üblicherweise byteweise
übertragen werden.

Abbildung 3 veranschaulicht grafisch die Konvertierung von 8 Septetten in 7 Oktetten.


Abbildung 3: Umwandlung von 8 Septetten in 7 Oktetten

Falls die Anzahl der zu übertragenden Septetten nicht einem Vielfachen von 8 entspricht,
müssen sogenannte _Paddingbits_ noch am Schluss angefügt werden.


### Methodisches Vorgehen - Fallbeispiel
#### Laufzeitanforderungen
Da Nokia nur Windows Treiber zur seriellen Kommunikation mit dem Handy zur Verfügung
stellt, fiel die Entscheidung auf Windows XP SP3 als primäre Entwicklungs- und Laufzeitumgebung.

Eine weitere Vorraussetzung für eine einwandfreie Funktionsweise ist die Installation des
Nokia 6212 NFC SDKs. Letztere installiert einen COM Port, über jenem die gesamte Kommunikation
mit dem Mobiltelefon statt findet. Wichtig ist, das dieser Port AT-Befehle akzeptiert.
Dazu öffnet man am besten eine Konsole (zum Beispiel _Hyperterminal_) und tippt `AT` gefolgt von
Enter ein. Anschließend sollte dieser Befehl mit `OK` bestätigt werden. Sollte dies nicht der Fall
sein, empfiehlt sich die Neukonfiguration beziehungsweise die Vergabe eines anderen COM-Ports
bei einer Neuinstallation des Modems.

Zur seriellen Kommunikation ist weiters die Installation der _Java Comm API_ notwendig.
Hierzu muss die native Bibliothek _win32com.dll_ in den Order `%JAVA_HOME%/jre/bin` und die
Datei _javax.comm.properties_ in den Order `%JAVA_HOME%/jre/lib` kopiert werden (beide Dateien
wurden von der LVA-Leitung zur Verfügung gestellt).

#### Build Management
Als Buildmanagement Werkzeug wurde Apache Maven [Apache Maven] (http://maven.apache.org) verwendet. Der Befehl `mvn package`
erstellt ein ausführbares jar-Archiv im Order _target_. Anschließend genügt das Ausführen des
Befehls _java -jar sms-sender-1.0-SNAPSHOT-jar-with-dependencies.jar_ um das Programm zu
starten.

#### Konfigurationsdateien
Die primäre Konfigurationsdatei ist _sendsms.properties_. Abbildung 4 zeigt beispielhaft eine
ebensolche Datei.

```
csvfile=sendsms.csv
port=COM4
```

Abbildung 4: Beispiel der Konfigurationsdatei sendsms.properties

Daneben muss noch ein Comma Separated File (CSV) existieren (spezifiziert in _sendsms.properties_).
Abbildung 5 zeigt beispielhaft die Datei _sendsms.csv_.


```
+436507112256,This is a short text!
+436507112256,This is a not so short text!
```

Abbildung 5: Beispiel der Konfigurationsdatei _sendsms.csv_

### Fazit
Essentiell für die korrekte Funktionsweise dieses Programms ist eine serielle Verbindung zum
Mobiltelefon über jene AT-Befehle abgesetzt werden können. Sollte dies nicht möglich sein,
schafft wahrscheinlich ein Blick in den Gerätemanager Abhilfe. Das Handy sollte als Modem im
Gerätemanager gelistet sein. Ist dies nicht der Fall, hilft eine Neuinstallation des Handytreibers.
Weiters sollte der richtige serielle Port angegeben werden. Dieser kann im Konfigurationsdialog
des Modems im Reiter _Advanced_ und einem Klick auf _Advanced Port Settings_ konfiguriert
werden.
