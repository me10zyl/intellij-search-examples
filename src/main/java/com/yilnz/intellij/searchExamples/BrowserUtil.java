package com.yilnz.intellij.searchExamples;

import java.io.IOException;

public class BrowserUtil {

    public static void openURL(String url) {

        String os = System.getProperty("os.name");
        // Linux
        if (os.indexOf("Linux") != -1) {
            try {
                String[] browsers = { "chrome", "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++) {
                    if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0) {
                        browser = browsers[count];
                    }
                }
                if (browser != null) {
                    Runtime.getRuntime().exec(new String[] { browser, url });
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            // Windows
        } else {
            String cmd = "rundll32 url.dll,FileProtocolHandler " + url;
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
