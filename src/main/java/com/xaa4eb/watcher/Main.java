package com.xaa4eb.watcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    /*
    * --add-opens java.base/java.lang=ALL-UNNAMED
    * --add-opens java.base/java.util=ALL-UNNAMED
    */

    public static class X {

        private Properties z = new Properties();

    }

    public static void main(String[] args) throws InterruptedException {

        X x = new X();

        List<String> list = new ArrayList<>();
        WatcherAPI.watchFields(x);

        Thread.sleep(2000);

        list.add("Z");
        x.z.put("z", "b");

        Thread.sleep(2000);

        x.z.put("z", "v");
        list.add("B");

        Thread.sleep(2000);
    }
}
