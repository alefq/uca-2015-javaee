package py.edu.uca.test.web.dto;

import java.util.Date;

public class NumberDTO {
	int value; 
	Date fecha;
	boolean par;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isPar() {
		return par;
	}
	public void setPar(boolean par) {
		this.par = par;
	}
	
	

}
