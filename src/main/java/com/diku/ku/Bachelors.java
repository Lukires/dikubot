package com.diku.ku;

public enum Bachelors {

    DATALOGI(Roles.DATALOG, "Datalogi"),
    MACHINELEARNING(Roles.DIKU, "Machine Learning"),
    DATALOGI_ECONOMICS(Roles.DIKU, "Datalogi-Økonomi"),
    OTHER(Roles.KU, "Andet");



    Roles role;
    String name;
    Bachelors(Roles role, String name) {
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
