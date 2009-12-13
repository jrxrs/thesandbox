package org.thesandbox.itask;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static Logger logger = Logger.getLogger(IPlayerDownload.class.getCanonicalName());

    private String uniqueId;
    private File folder;
    private String title;
    private boolean complete;
    private ImageIcon icon;
    private String url;
    private boolean stale;

    public IPlayerDownload(String uniqueId, File folder, String title,
                           boolean complete, ImageIcon icon, String url,
                           boolean stale) {
        this.uniqueId = uniqueId;
        this.folder = folder;
        this.title = title;
        this.complete = complete;
        this.icon = icon;
        this.url = url;
        this.stale = stale;
    }

    /* Getters & Setters */
    public String getUniqueId() {
        return uniqueId;
    }

    public File getFolder() {
        return folder;
    }

    public String getTitle() {
        return title;
    }

    public boolean isComplete() {
        return complete;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getUrl() {
        return url;
    }

    public boolean isStale() {
        return stale;
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
        boolean complete = true, stale = false;
        ImageIcon pic = null;

        if(f.isDirectory()) {
            File[] files = f.listFiles();
            Date d = new Date();
            long now = d.getTime();
            for(File curF : files) {
                if(plName.equals(curF.getName()))
                    f2p = curF;
                if(curF.getName().startsWith(uniqueId) &&
                        curF.getName().endsWith(".part")) {
                    complete = false;
                    try {
                        stale = now - curF.lastModified() >
                                1000 * Long.parseLong(ITaskProperties.getInstance()
                                        .get(ITaskProperties.STALE_PERIOD));
                    } catch(NumberFormatException nfe) {
                        logger.log(Level.WARNING, "Cannot determine stale for "
                                + curF.getName(), nfe);
                    }
                }
                if(curF.getName().endsWith(".jpg"))
                    pic = new ImageIcon(curF.getAbsolutePath());
            }
        }

        /* Parse the pl_ */
        IPlayerPlaylist ipp = new IPlayerPlaylist(f.getName(), "");
        if(f2p != null) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                factory.setNamespaceAware(false);

                XMLReader xmlReader = factory.newSAXParser().getXMLReader();

                IPlayerPlayListParser parser = new IPlayerPlayListParser();
                xmlReader.setContentHandler(parser);
                xmlReader.parse(f2p.getAbsolutePath());
                ipp = parser.getPlaylist();
            } catch(ParserConfigurationException pce) {
                logger.log(Level.WARNING, "Piccolo JAXP Config Problem", pce);
            } catch(SAXException saxe) {
                logger.log(Level.WARNING, "SAX Playlist parsing error", saxe);
            } catch(IOException ioe) {
                logger.log(Level.WARNING, "IO Error", ioe);
            }
        } else {
            System.out.println("Could not find: " + plName);
        }

        return new IPlayerDownload(uniqueId, f, ipp.title, complete, pic,
                ipp.url, stale);
    }

    private static class IPlayerPlaylist
    {
        public final String title;
        public final String url;

        public IPlayerPlaylist(String title, String url) {
            this.title = title;
            this.url = url;
        }
    }

    static class IPlayerPlayListParser implements ContentHandler
    {
        // XML bits
        private static final String PLAY_LIST       = "playlist";
        private static final String LINK            = "link";
        private static final String LINK_ATTR_REL   = "rel";
        private static final String LINK_ATTR_HREF  = "href";
        private static final String LINK_REL_ALT    = "alternate";
        private static final String TITLE           = "title";
        private static final String RELATED_LINK    = "relatedLink";

        private Locator locator;
        private Stack<StringBuffer> stack;
        private String title, url;

        private boolean stackee;

        public IPlayerPlayListParser() {
            locator = null;
            stack = new Stack<StringBuffer>();
            title = url = "";
            stackee = false;
        }
        
        public IPlayerPlaylist getPlaylist() {
            return new IPlayerPlaylist(title, url);
        }

        public void setDocumentLocator(Locator locator) {
            this.locator = locator;
        }

        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            stackee = false;

            if(PLAY_LIST.equals(qName)) {
                stack.push(new StringBuffer(PLAY_LIST));
            } else if(LINK.equals(qName)) {
                processLink(atts);
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

        public void processLink(Attributes atts) {
            if(RELATED_LINK.equals(stack.peek().toString()))
                return;
            if(LINK_REL_ALT.equals(atts.getValue(LINK_ATTR_REL)))
                url = atts.getValue(LINK_ATTR_HREF);
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
