

# New Java Features Outline #

The class below illustrates some of the new Java 7 features:

See [here](https://code.google.com/p/thesandbox/w/edit.do#What%27s_new_in_Java_7?) for an overview of these featrues.

```
package thesandbox;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * New Java 7 features.
 */
public class Java7Features
{
    public static int stringsInSwitch(String in) {
        switch (in) {
            case "One" : return 1;
            case "Two" : return 2;
            case "Three" : return 3;
            case "Four" : return 4;
            case "Five" : return 5;
            case "Six" : return 6;
            case "Seven" : return 7;
            case "Eight" : return 8;
            case "Nine" : return 9;
            case "Ten" : return 10;
            default: return 0;
        }
    }

    public static int getBinaryLiteral() {
        return 0b101;
    }

    public static int getUSLiteral() {
        return 1_000_000;
    }

    public static void multipleCatch() {
        BufferedWriter bw = null;
        try {
            URI uri = new URI("xyz:abc");
            File file = new File(uri);
            if (file.createNewFile()) {
                FileWriter fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                bw.write("this is a new string");
                bw.newLine();
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    public static void tryWithResources() {
        try {
            URL url = new URL("");
            File file = new File("/users/apple/test.txt");
            try (OutputStream out = new FileOutputStream(file); InputStream is = url.openStream() ) {
                byte[] buf = new byte[4096];
                int len;
                while ((len = is.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (MalformedURLException murle) {
            murle.printStackTrace();
        }
    }

    public static Map<String, List<URL>> diamondSyntax() {
        return new HashMap<>();
    }

    @SafeVarargs
    public static <T> Collection<T> doSomething(T... entries) {
        return new ArrayList<>();
    }
}

```

# Singleton Patterns #

Also see [here](http://code.google.com/p/thesandbox/wiki/Resources#Singleton_Pattern)

## The Preferred Approach - The `enum` Singleton ##

```
// Enum singleton - the preferred approach
public enum Elvis { 
    INSTANCE;

    public void leaveTheBuilding() { ... }
}
```

## The `static` Holder Approach ##

The benefit of this approach is that you achieve lazy initialisation prior to the class becoming available, this is all taken care of by the class loader.

```
public class Singleton {
    private static class SingletonHolder {
        static final Singleton INSTANCE = makeSingleton();
    }

    private Singleton() { /* perform any required init */ }

    private Singleton makeSingleton() {
        return new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

## Double Checked Locking (DCL) ##

**Note:** The use of volatile in this example is absolutely crucial as it requires all threads to read the variable from system memory rather than from a local thread cache they might have stored it in.

```
public class Singleton {
    private static volatile Singleton INSTANCE;

    private Singleton() { /* perform any required init */ }

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }

        return INSTANCE;
    }
}
```

## Map Message to XML ##

```
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class MessageParserTestCase {

    @Test
    public void test() {
        final String message = "|SOM=|SOH=|a=1|b=2|c=3|EOH=|SOB=|d=4|e=5|f=6|EOB=|EOM=|";
        final Pattern pattern = Pattern.compile("\\|SOM=\\|SOH=\\|(.*)\\|EOH=\\|SOB=\\|(.*)\\|EOB=\\|EOM=\\|");
        final Matcher matcher = pattern.matcher(message);
        matcher.find();
        final Map<String, String> header = parse(matcher.group(1));
        Assert.assertTrue(header.size() == 3);
        Assert.assertTrue(header.get("a").equals("1"));
        Assert.assertTrue(header.get("b").equals("2"));
        Assert.assertTrue(header.get("c").equals("3"));
        final Map<String, String> body = parse(matcher.group(2));
        Assert.assertTrue(body.size() == 3);
        Assert.assertTrue(body.get("d").equals("4"));
        Assert.assertTrue(body.get("e").equals("5"));
        Assert.assertTrue(body.get("f").equals("6"));
    }

    private Map<String, String> parse(String group) {
        final String[] headers = group.split("\\|");
        final Map<String, String> map = new HashMap<String, String>(headers.length);
        for (String header : headers) {
            final String[] pair = header.split("=");
            map.put(pair[0], pair[1]);
        }
        return map;
    }

    private final String message = "|SOH=|VERS=1.00|MTYP=POSTTRD|DATE=20130212|TIME=10:30:51|DLR=NOM|USER=nomeugv2|PGRP=EUGV|TRDDT=20130212|EOH=|SPTM=1|SOH=|VERS=1.00|MTYP=TRDCONF|DATE=20130212|TIME=10:30:51|TSTMSG=YES|DLR=NOM|USER=nomeugv|PGRP=EUGV|TRDTYP=USTSWAP|TSRC=TW|TRDDT=20130212|TNUM=15|EOH=|SWSPRD=-1.2380|TRDPRICES=YES|SWPTYP=FACEAMT|NETDIFF=-14787.670|SSCNF=|PGRP=EUGV|TNUM=15|CUST=nomcust2|CUSTNAME=Nomura Customer2|UCNTRY=UK|CINFO=007|COMPANY=Customer Nomura|COACR=886137|ODDMARK=NO|LOCC=LDN|LOCD=LDN|CLRCD=UNKNOWN|STYPE=REGDBR|WI=NO|ISIN=DE0001135234|CPN=3.750|AUCDT=20030702|MATDT=20130704|ROPN=1|ISMN=120|OTR=0|DTDDT=20030704|ISSDT=20030704|FCDT=20040704|ANNCDT=20030624|CNTRY=DE|CPNFQ=1|DYCTBAS=ACT/ACT|RVAL=100.0|DECPLCS=3|DECRND=0.0010|QID=TBSI01DE0001135234|SDESC=DBR  3.750 04/07/13|QTYP=PRICEDEC|PRICE=101.420|YIELD=0.02020|BYIELD=0.02050|MYIELD=0.02020|YTYP=MME|QNTY=1000000|TRANS=SELL|STLDT=20130215|GTRDDT=20130212|GTRDTM=10:30:48|SECACCR=2.3219178082190|TOTACCR=23219.180|PRIN=1014200.0|NET=1037419.180|CURR=EUR|FXRATE=0.0|NREQ=1|MTKT=1|CUSTPRC=NO|TRADER=comerfom-Auto|COMPQT=-164.30|COMPSZ=0.0|COMPSPRD=-1.6430|CMPB=101.410|CMPA=101.4290|CMPM=101.420|COMPMV=0.0|VIEWERACK=NO|ESCNF=|SBCNF=|PGRP=EUGV|TNUM=16|CUST=nomcust2|CUSTNAME=Nomura Customer2|UCNTRY=UK|CINFO=007|COMPANY=Customer Nomura|COACR=886137|ODDMARK=NO|LOCC=LDN|LOCD=LDN|CLRCD=UNKNOWN|STYPE=REGIRISH|WI=NO|ISIN=IE0031256328|CPN=5.0|AUCDT=20020131|MATDT=20130418|ROPN=1|ISMN=132|OTR=0|DTDDT=20020131|ISSDT=20020131|FCDT=20020418|ANNCDT=20020131|CNTRY=IE|CPNFQ=1|DYCTBAS=ACT/ACT|RVAL=100.0|DECPLCS=3|DECRND=0.0010|QID=TBSI01IE0031256328|SDESC=IRISH  5.000 18/04/13|QTYP=PRICEDEC|PRICE=101.070|YIELD=-1.21780|BYIELD=-1.23470|MYIELD=-1.21780|YTYP=MME|QNTY=1000000|TRANS=BUY|STLDT=20130215|GTRDDT=20130212|GTRDTM=10:30:48|SECACCR=4.1506849315070|TOTACCR=41506.850|PRIN=1010700.0|NET=1052206.850|CURR=EUR|FXRATE=0.0|NREQ=1|MTKT=1|CUSTPRC=NO|TRADER=comerfom-Auto|COMPQT=-164.30|COMPSZ=0.0|COMPSPRD=-1.6430|CMPB=100.5250|CMPA=101.1390|CMPM=100.8320|COMPMV=0.0|VIEWERACK=NO|EBCNF=|EOM=|EPTM=1|EOM=|";

    private final Map<String, String> metadata = new HashMap<String, String>(); {
        metadata.put("SOM", "EOM");
        metadata.put("SOH", "EOH");
        metadata.put("SPTM", "EPTM");
        metadata.put("SSCNF", "ESCNF");
        metadata.put("SBCNF", "EBCNF");
    }
    
    @Test
    public void test2() throws XMLStreamException {
        final StringTokenizer tokenizer = new StringTokenizer(message, "|");
        final XMLOutputFactory factory = XMLOutputFactory.newInstance();
        final XMLStreamWriter writer = factory.createXMLStreamWriter(System.out);
        
        int indent = 0;
        
        writer.writeStartDocument();
        System.out.println();
        writer.writeStartElement("SOM");
        writer.writeCharacters("");
        while (tokenizer.hasMoreTokens()) {
            final String token = tokenizer.nextToken();
            final String[] pair = token.split("=");
            final String key = pair[0];
            final String val = pair.length > 1 ? pair[1] : "";

            if (metadata.containsKey(key)) {
                // push...
                writeIndent(indent++);
                writer.writeStartElement(key);
                writer.writeCharacters(val);
                writer.flush();
                System.out.println();
            } else if (metadata.containsValue(key)) {
                // pop...
                writeIndent(--indent);
                writer.writeEndElement();
                writer.flush();
                System.out.println();
            } else {
                writeIndent(indent);
                writer.writeStartElement(key);
                writer.writeCharacters(val.toCharArray(), 0, val.length());
                writer.writeEndElement();
                writer.flush();
                System.out.println();
            }
        }
        writer.writeEndDocument();
    }

    private void writeIndent(int indent) {
        System.out.print(StringUtils.repeat("  ", indent));
        System.out.flush();
    }
}
```

## Writing Comma Separated Lists the Easy Way ##

**Note**: With Java 8 this is even easier provided the list contains Strings (See Java 8 section).

```
public static String listToString(List<?> list) {
    final StringBuilder sb = new StringBuilder();
    String sep = "";
    for (Object object : list) {
        sb.append(sep).append(object.toString());
        sep = ",";
    }

    return sb.toString();
}
```

## New Java 8 Concepts ##

See here for an overview of these features.

```
public static String join(Collection collection, String delimiter){
    return collection.stream()
            .map(Object::toString) // <- Method Reference
            .collect(Collectors.joining(delimiter));
}
```
