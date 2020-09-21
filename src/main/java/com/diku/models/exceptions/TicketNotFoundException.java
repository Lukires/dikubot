package com.diku.models.exceptions;

import java.util.UUID;

public class TicketNotFoundException extends Exception {

    private UUID uuid;
    public TicketNotFoundException(UUID uuid) {
        this.uuid=uuid;
    }

    public String toString() {
        return String.format("There is no ticket with UUID %s", uuid.toString());
    }
}
