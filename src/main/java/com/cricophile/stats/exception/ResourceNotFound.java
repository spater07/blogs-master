package com.cricophile.stats.exception;

public class ResourceNotFound extends RuntimeException {


    String fieldName;
    String resourceName;
    long id;

    public ResourceNotFound(String fieldName, String resourceName, long id) {
        super(String.format("%s not found with %s : %l", fieldName,  resourceName, id));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.id = id;
    }


}
