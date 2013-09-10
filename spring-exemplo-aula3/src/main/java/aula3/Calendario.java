package aula3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendario {

	private String data;
	
	public Calendario(final String data) {
		this.data = data;
	}
	
	public Date createDate() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.parse(data);
	}
	
}
