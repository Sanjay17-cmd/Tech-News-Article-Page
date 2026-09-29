package com.technews.util;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class LoggedInUsers {
    private static final Set<String> users = Collections.synchronizedSet(new LinkedHashSet<>());

    public static void add(String name) { users.add(name); }
    public static void remove(String name) { users.remove(name); }
    public static int count() { return users.size(); }
    public static String namesList() { return String.join(", ", users); }
}
