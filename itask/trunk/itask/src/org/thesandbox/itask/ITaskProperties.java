package org.thesandbox.itask;

import org.jdesktop.application.ResourceMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * The Properties container for the iTask application.
 * This class is a singleton.
 *
 * Created by IntelliJ IDEA.
 *
 * @author jrxrs
 * @date 05-Dec-2009
 */

public class ITaskProperties {
    private static ITaskProperties cInstance = null;

    public static ITaskProperties getInstance() {
        if(cInstance == null) {
            cInstance = new ITaskProperties();
        }
        return cInstance;
    }

    private final Properties userProperties;
    private ResourceMap resourceMap;
    private File pFile;
    private final String propsFileName = "iTaskSettings.xml";
    public static final String REP_PATH    = "repository";
    public static final String NOT_SET     = "null";

    private ITaskProperties() {
        userProperties = new Properties();
        resourceMap = ITaskApp.getApplication().getContext().getResourceMap(this.getClass());
        loadUserProperties();
    }

    public String get(String prop) {
        return userProperties.getProperty(prop);
    }

    public String get(String prop, String def) {
        return userProperties.getProperty(prop, def);
    }

    /**
     * Set a property and if instant is true then ensure the change is persisted
     * by writing the properties to file immediately.
     * @param key       The key to store the value under
     * @param value     The value to store
     * @param instant   true if the properties should be written to file
     *                  false if the properties should not be written to file
     */
    public void set(String key, String value, boolean instant) {
        userProperties.setProperty(key, value);
        if(instant) {
            try {
                writeProps(pFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dump() {
        userProperties.list(System.out);
    }

    private void writeProps(File f) throws Exception {
        FileOutputStream fos = new FileOutputStream(f);
        String userName = System.getProperty("user.name");
        userProperties.storeToXML(fos, resourceMap.getString("Application.title")
                + " Settings for " + userName);
        fos.close();
    }

    private File newDefaultProps(File propsFile) throws Exception {
        userProperties.put(REP_PATH, NOT_SET);
        writeProps(propsFile);
        return propsFile;
    }

    private File rinse(File props) throws Exception {
        if(props.exists() && props.isFile())
            return props;
        else if(props.createNewFile())
            return newDefaultProps(props);
        else return null;
    }

    private File propsExist() throws Exception {
        File homeDir = new File(System.getProperty("user.home"));
        if(homeDir.exists() && homeDir.isDirectory()) {
            File appHome = new File(homeDir, ".".concat(resourceMap.getString("Application.title")));
            File props = new File(appHome, propsFileName);
            if(appHome.exists() && appHome.isDirectory()) {
                return rinse(props);
            } else {
                if(appHome.mkdir() && props.createNewFile())
                    return newDefaultProps(props);
                else return null;
            }
        } else { //Could not find user.home - try current directory
            String curDir = System.getProperty("user.dir");
            File props = new File(curDir, propsFileName);
            return rinse(props);
        }
    }

    private void loadUserProperties() {
        try {
            pFile = propsExist();
            if(pFile != null) {
                System.out.println("props file found at " + pFile.getPath());
                FileInputStream fis = new FileInputStream(pFile);
                userProperties.loadFromXML(fis);
                fis.close();
                dump();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
