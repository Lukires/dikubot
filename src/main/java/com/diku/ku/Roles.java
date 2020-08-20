package com.diku.ku;

public enum Roles {

    DATALOG("Datalog"), DIKU("DIKU"), KU("KU"), MACHINETEACHER("MachineTeacher"), CBS_PROGRAMMING("CBS-Programming");

    private String role;
    Roles(String role) {
        this.role=role;
    }

    public String getRole() {
        return role;
    }
}
