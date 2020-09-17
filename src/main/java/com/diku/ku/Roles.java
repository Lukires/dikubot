package com.diku.ku;

import java.util.HashMap;
import java.util.Map;

public enum Roles {

    DATALOG("Datalog", false), DIKU("DIKU", false), KU("KU", false),
    MACHINETEACHER("Machine Teachers",false), CBS_PROGRAMMING("CBS-Programming", false),
    WEEB("Weeb", true), GAMER("Gamer", true), DND("DND", true);

    public static final Map<String, Roles> nameIndex = new HashMap<>(Roles.values().length);

    static {
        for (Roles role : Roles.values()) {
            System.out.println(role.getRole());
            nameIndex.put(role.getRole(),role);
        }
    }

    public static boolean containsRoleName(String name) {
        return nameIndex.containsKey(name.toUpperCase());
    }

    public static Roles getRoleByRoleName(String name) {
        return nameIndex.get(name.toUpperCase());
    }

    private final String role;
    private final boolean selectable;
    Roles(String role, Boolean selectable) {
        this.role=role;
        this.selectable=selectable;
    }

    public String getRole() {
        return role;
    }

    public boolean isSelectable() {
        return selectable;
    }
}
