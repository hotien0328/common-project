package com.vo;

import java.net.URL;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:
 * <p>
 * Description:
 * <p>
 * Copyright: Copyright (c) 2008
 *
 * @author haoxz11
 * @version $Id: Config.java 89112 2015-06-13 08:45:20Z zhjy $
 * @created Mar 7, 2008 11:20:37 AM
 */
public class Config {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    private static final String DEFAULT_CONF_FILE = "/baseconfig.properties";

    private static PropertiesConfiguration config = null;
    private static String projectName = null;
    private static String projectRootPath = null;
    private static String projectHost = null;
    private static String projectPort = null;
    private static String projectUrl = null;

    static {
        URL url = Config.class.getResource(getConfigFile());
        String absolutePath = "";
        if (url != null) {
            absolutePath = url.getFile();
            try {
                config = new PropertiesConfiguration(url);
            } catch (Exception e) {
                logger.warn("Config Error:{}", e.getMessage());
            }
        } 
        if (config == null) {
            config = new PropertiesConfiguration();
            logger.warn("Config Nofind classpath:/baseconfig.properties!");
        } else {
            projectName = config.getString("sys.name");
            if (projectName != null) {
                String tpath = new StringBuilder().append("/").append(projectName).append("/").toString();
                int i = absolutePath.indexOf(tpath);
                if (i >= 0) {
                    projectRootPath = absolutePath.substring(0, i + 1) + projectName;
                    logger.info("ProjectRootPath: {}", projectRootPath);
                } else {
                    logger.warn("Config projectName:{},path:{},tpath:{}", projectName, absolutePath, tpath);
                }
            }
            projectHost = config.getString("sys.host");
            projectPort = config.getString("sys.port");
            projectUrl = projectHost;
            if (projectHost != null && projectPort != null && !projectPort.equals("80")) {
                projectUrl = "http://" + projectHost + ":" + projectPort;
            }
        }
    }

    public static String getProjectName() {
        return projectName;
    }

    public static String getProjectHost() {
        return projectHost;
    }

    public static String getProjectPort() {
        return projectPort;
    }

    public static String getProjectUrl() {
        return projectUrl;
    }

    public static String getRootPath() {
        return projectRootPath;
    }

    public static String getSysPath(String type) {
        String tmp = config.getString("sys.path." + type);
        if (tmp != null) {
            return projectRootPath + tmp;
        }
        return null;
    }

    protected static String getConfigFile() {
        return DEFAULT_CONF_FILE;
    }

    public static String getString(String key, String defaultValue) {
        return config.getString(key, defaultValue);
    }

    public static String getString(String key) {
        return config.getString(key, null);
    }

    public static String getStringList(String key) {
        StringBuilder tmp = new StringBuilder();
        int i = 0;
        for (String o : config.getStringArray(key)) {
            String s = String.valueOf(o);
            if (i > 0) {
                tmp.append(",");
            }
            tmp.append(s);
            i++;
        }
        return tmp.toString();
    }

    public static int getInt(String key, int defaultValue) {
        return config.getInt(key, defaultValue);
    }

    public static int getInt(String key) {
        return config.getInt(key, 0);
    }

    public static long getLong(String key, long defaultValue) {
        return config.getLong(key, defaultValue);
    }

    public static long getLong(String key) {
        return config.getLong(key, 0);
    }

    public static String[] getStringArray(String key) {
        return config.getStringArray(key);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return config.getBoolean(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return config.getBoolean(key, false);
    }

    public static float getFloat(String key, float defaultValue) {
        return config.getFloat(key, defaultValue);
    }

    public static float getFloat(String key) {
        return config.getFloat(key, 0);
    }

    public static void main(String[] args) {
        logger.debug("test");
    }

}
