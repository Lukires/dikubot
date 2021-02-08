package ninja.diku.ku;

import java.util.HashMap;
import java.util.Map;

public enum Roles {

    DATALOG("Datalog", false), DIKU("DIKU", false), KU("KU", false),
    MACHINETEACHER("Machine Learning",false), CBS_PROGRAMMING("Datalogi-Økonomi", false),
    WEEB("Weeb", true), GAMER("Gamer", true), DND("DND", true), RUS("Rus", true), SUNDHED_IT("sundIT/Mat", false),
    HOLD_1("Hold-1", true), HOLD_2("Hold-2", true), HOLD_3("Hold-3", true),
    HOLD_4("Hold-4", true), HOLD_5("Hold-5", true), HOLD_6("Hold-6", true),
    HOLD_7("Hold-7", true), HOLD_8("Hold-8", true), HOLD_9("Hold-9", true),
    HOLD_10("Hold-10", true), HOLD_11("Hold-11", true), HOLD_12("Hold-12", true),
    HOLD_13("Hold-13", true), HOLD_14("Hold-14", true), HOLD_15("Hold-15", true),
    HOLD_16("Hold-16", true);

    public static final Map<String, Roles> nameIndex = new HashMap<>(Roles.values().length);

    static {
        for (Roles role : Roles.values()) {
            nameIndex.put(role.getRole().toUpperCase(),role);
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
