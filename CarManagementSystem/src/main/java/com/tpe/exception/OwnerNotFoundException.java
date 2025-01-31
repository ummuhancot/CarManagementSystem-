package com.tpe.exception;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(String ownerNotFound) {
        super(ownerNotFound);
    }
}
