package etsisi.ems.ejemplogit;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Movimiento {
	protected String mConcepto;
	protected LocalDate mFecha;
	protected double mImporte;

	public Movimiento() {
		Date date = new Date();
		mFecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	}

	public double getImporte() {
		return mImporte;
	}

	public String getConcepto() {
		return mConcepto;
	}

	public void setConcepto(String newMConcepto) {
		mConcepto = newMConcepto;
	}

	public LocalDate getFecha() {
		return mFecha;
	}

	public void setFecha(LocalDate newMFecha) {
		mFecha = newMFecha;
	}

	public void setImporte(double newMImporte) {
		mImporte = newMImporte;
	}
}