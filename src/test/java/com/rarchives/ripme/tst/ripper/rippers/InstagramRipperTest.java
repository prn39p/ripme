package com.rarchives.ripme.tst.ripper.rippers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rarchives.ripme.ripper.rippers.InstagramRipper;

public class InstagramRipperTest extends RippersTest {
    
    public void testInstagramGID() throws IOException {
        Map<URL, String> testURLs = new HashMap<URL, String>();
        testURLs.put(new URL("http://instagram.com/Test_User"), "Test_User");
        testURLs.put(new URL("http://instagram.com/_test_user_"), "_test_user_");
        testURLs.put(new URL("http://instagram.com/-test-user-"), "-test-user-");
        for (URL url : testURLs.keySet()) {
            InstagramRipper ripper = new InstagramRipper(url);
            assertEquals(testURLs.get(url), ripper.getGID(ripper.getURL()));
            deleteDir(ripper.getWorkingDir());
        }
    }

    public void testInstagramAlbums() throws IOException {
        if (!DOWNLOAD_CONTENT) {
            return;
        }
        List<URL> contentURLs = new ArrayList<URL>();
        contentURLs.add(new URL("http://instagram.com/feelgoodincc#"));
        for (URL url : contentURLs) {
            try {
                InstagramRipper ripper = new InstagramRipper(url);
                ripper.rip();
                assert(ripper.getWorkingDir().listFiles().length > 1);
                deleteDir(ripper.getWorkingDir());
            } catch (Exception e) {
                fail("Error while ripping URL " + url + ": " + e.getMessage());
            }
        }
    }

}