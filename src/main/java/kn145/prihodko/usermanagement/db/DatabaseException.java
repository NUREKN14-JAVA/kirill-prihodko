package kn145.prihodko.usermanagement.db;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public DatabaseException() {
        super();
    }

}