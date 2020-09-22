package com.diku.ku;

public enum Major {

    DATALOGI(Roles.DATALOG, "Datalogi"),
    MACHINELEARNING(Roles.MACHINETEACHER, "Machine-Learning"),
    DATALOGI_ECONOMICS(Roles.CBS_PROGRAMMING, "Datalogi-Økonomi"),
    MATEMATIK(Roles.KU, "Matematik"),
    ECONOMICS(Roles.KU, "Økonomi"),
    FYSIK(Roles.KU, "Fysik"),
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
