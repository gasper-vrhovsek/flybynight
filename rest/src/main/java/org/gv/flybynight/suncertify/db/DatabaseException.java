package org.gv.flybynight.suncertify.db;

public class DatabaseException extends Exception {
    public DatabaseException() {}

    public DatabaseException(String msg) {
        super(msg);
    }
}