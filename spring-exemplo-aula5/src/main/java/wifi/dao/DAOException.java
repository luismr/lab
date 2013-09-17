package wifi.dao;

public class DAOException extends RuntimeException {

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String m) {
		super(m);
	}

	private static final long serialVersionUID = 1L;

}
