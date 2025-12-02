package com.fleapo.conduit.core;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Properties;

public class OptionsManager {

    private final Properties prop;
    private final boolean incognito;
    private final boolean highlight;

    public OptionsManager(Properties prop) {
        this.prop = prop;
        this.incognito = Boolean.parseBoolean(prop.getProperty("incognito"));
        this.highlight = Boolean.parseBoolean(prop.getProperty("highlight"));
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        if (incognito) {
            options.addArguments("--incognito");
        }

        return options;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        if (incognito) {
            options.addArguments("--private");
        }

        return options;
    }

    public EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        if (incognito) {
            options.addArguments("--inprivate");
        }

        return options;
    }

    public boolean isHighlightEnabled() {
        return highlight;
    }
}
