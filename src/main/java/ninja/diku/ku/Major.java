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
    SUNDHED_IT(Roles.SUNDHED_IT, "Sundhed-It"),
    OTHER(Roles.KU, "Andet");



    Roles role;
    String name;
    Major(Roles role, String name) {
        this.role=role;
        this.name=name;
    }

    public Roles getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
