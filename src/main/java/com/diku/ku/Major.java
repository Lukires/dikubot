package com.diku.ku;

public enum Major {

    DATALOGI(Roles.KU, "Datalogi"),
    MACHINELEARNING(Roles.KU, "Machine-Learning"),
    DATALOGI_ECONOMICS(Roles.KU, "Datalogi-Økonomi"),
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
