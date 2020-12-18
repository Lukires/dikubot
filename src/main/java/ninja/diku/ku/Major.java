package ninja.diku.ku;

public enum Major {

    DATALOGI_RUS(Roles.DATALOG, "1år-Datalogi"),
    DATALOGI(Roles.DIKU, "Alle-Datalogi"),
    MACHINELEARNING_RUS(Roles.MACHINETEACHER, "1år-Machine-Learning"),
    MACHINELEARNING(Roles.DIKU, "Alle-Machine-Learning"),
    DATALOGI_ECONOMICS_RUS(Roles.CBS_PROGRAMMING, "1år-Datalogi-Økonomi"),
    DATALOGI_ECONOMICS(Roles.DIKU, "Alle-Datalogi-Økonomi"),
    MATEMATIK(Roles.KU, "Matematik"),
    ECONOMICS(Roles.KU, "Økonomi"),
    FYSIK(Roles.KU, "Fysik"),
    OTHER(Roles.KU, "Andet");



    Roles role;
    String name;
    /**
     * Majors are used to assign people the corrent roles on the discord server.
     * @param role The role
     * @param name Then name of the role
     * @see Roles
     */
    Major(Roles role, String name) {
        this.role=role;
        this.name=name;
    }

    /**
     * Returns the Role
     * @return Roles
     * @see Roles
     */
    public Roles getRole() {
        return role;
    }

    /**
     * Returns the name
     * @return String name
     * @see Roles
     */
    public String getName() {
        return name;
    }
}
