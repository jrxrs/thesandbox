package org.thesandbox.itask;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import org.xml.sax.*;
import javax.xml.parsers.*;

/**
 * A class for iPlayer Downloads.
 *
 * Created by IntelliJ IDEA.
 *
 * @author jrxrs
 * @date 07-Dec-2009
 */
public class IPlayerDownload
{
    private String uniqueId;
    private File folder;
    private String title;
    private boolean complete;
    private ImageIcon icon;

    public IPlayerDownload(String uniqueId, File folder, String title,
                           boolean complete, ImageIcon icon) {
        this.uniqueId = uniqueId;
        this.folder = folder;
        this.title = title;
        this.complete = complete;
        this.icon = icon;
    }

    /* Getters & Setters */
    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public File getFolder() {
        return folder;
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    /**
     * Factory method to parse an iPlayer Download directory and return whatever
     * is found.
     * @param f The file (directory) to create an IPlayerDownload from.
     * @return  The IPlayerDownload specified by file f
     */
    public static IPlayerDownload parse(File f) {
        File f2p = null;
        String[] split = f.getName().split("_");
        String uniqueId = split[0];
        String plName = "pl_" + uniqueId;
        boolean complete = true;
        ImageIcon pic = null;

        if(f.isDirectory()) {
            File[] files = f.listFiles();
            for(File curF : files) {
                if(plName.equals(curF.getName()))
                    f2p = curF;
                if(curF.getName().startsWith(uniqueId) &&
                        curF.getName().endsWith(".part"))
                    complete = false;
                if(curF.getName().endsWith(".jpg"))
                    pic = new ImageIcon(curF.getAbsolutePath());
            }
        }

        /* Parse the pl_ */
        String title = f.getName();
        if(f2p != null) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                factory.setNamespaceAware(false);

                XMLReader xmlReader = factory.newSAXParser().getXMLReader();

                IPlayerPlayListParser parser = new IPlayerPlayListParser();
                xmlReader.setContentHandler(parser);
                xmlReader.parse(f2p.getAbsolutePath());
                title = parser.getTitle();
            } catch(ParserConfigurationException pce) {

            } catch(SAXException saxe) {

            } catch(IOException ioe) {

            }
        } else {
            System.out.println("Could not find: " + plName);
        }

        return new IPlayerDownload(uniqueId, f, title, complete, pic);
    }

    static class IPlayerPlayListParser implements ContentHandler
    {
        // XML bits
        private static final String PLAY_LIST       = "playlist";
        private static final String TITLE           = "title";
        private static final String RELATED_LINK    = "relatedLink";

        private Locator locator;
        private Stack<StringBuffer> stack;
        private String title;

        private boolean stackee;

        public IPlayerPlayListParser() {
            locator = null;
            stack = new Stack<StringBuffer>();
            title = "";
            stackee = false;
        }
        
        public String getTitle() {
            return title;
        }

        public void setDocumentLocator(Locator locator) {
            this.locator = locator;
        }

        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            stackee = false;

            if(PLAY_LIST.equals(qName)) {
                stack.push(new StringBuffer(PLAY_LIST));
            } else if(TITLE.equals(qName)) {
                if(RELATED_LINK.equals(stack.peek().toString())) {
                    stack.push(new StringBuffer(TITLE));
                } else {
                    stack.push(new StringBuffer());
                    stackee = true;
                }
            } else if(RELATED_LINK.equals(qName)) {
                stack.push(new StringBuffer(RELATED_LINK));
            }
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            stackee = false;

            if(PLAY_LIST.equals(qName)) {
                stack.pop();
            } else if(TITLE.equals(qName)) {
                if(TITLE.equals(stack.peek().toString())) {
                    stack.pop();
                } else {
                    title = stack.pop().toString();
                }
            } else if(RELATED_LINK.equals(qName)) {
                stack.pop();
            }
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            // if stackee is false, then data is not content of desired element
            if(stackee) {
                stack.peek().append(ch, start, length);
            } else {
                // discard these chars, they're not part of a desired element
            }
        }

        public void startDocument() throws SAXException { }

        public void endDocument() throws SAXException { }

        public void startPrefixMapping(String prefix, String uri) throws SAXException { }

        public void endPrefixMapping(String prefix) throws SAXException { }

        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException { }

        public void processingInstruction(String target, String data) throws SAXException { }

        public void skippedEntity(String name) throws SAXException { }
    }
}
