package by.htp.client.console;

public class FailedConnectionException extends Exception {

	public FailedConnectionException() {
		super();
	}

	public FailedConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public FailedConnectionException(String message) {
		super(message);
	}

	public FailedConnectionException(Throwable cause) {
		super(cause);
	}

}
