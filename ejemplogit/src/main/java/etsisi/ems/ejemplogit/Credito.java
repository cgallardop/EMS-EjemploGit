package etsisi.ems.ejemplogit;

import java.util.Vector;
import java.time.LocalDate;

public class Credito extends Tarjeta {
	protected double mCredito;
	protected Vector<Movimiento> mMovimientos;

	public Credito(String numero, String titular, LocalDate fechaCaducidad, double credito) {
		super(numero, titular, fechaCaducidad);
		mCredito = credito;
		mMovimientos = new Vector<Movimiento>();
	}

	public void retirar(double x) throws Exception {
		Movimiento m = new Movimiento();
		m.setConcepto("Retirada en cuenta asociada (cajero automático)");
		// x=(x*0.05<3.0 ? 3 : x*0.05); // Añadimos una comisión de un 5%, mínimo de 3 euros.
		// m.setImporte(x);
		double comision = (x * 0.05 < 3.0 ? 3 : x * 0.05); // Añadimos una comisión de un 5%, mínimo de 3 euros.
		m.setImporte(x + comision);
		mMovimientos.addElement(m);
		if (x > getCreditoDisponible())
			throw new Exception("Crédito insuficiente");
	}

	public void ingresar(double x) throws Exception {
		// Movimiento m=new Movimiento();
		// m.setConcepto("Ingreso en cuenta asociada (cajero automático)");
		// m.setImporte(x);
		// mMovimientos.addElement(m);
		mCuentaAsociada.ingresar(x);
	}

	public void pagoEnEstablecimiento(String datos, double x) throws Exception {
		Movimiento m = new Movimiento();
		m.setConcepto("Compra a crédito en: " + datos);
		m.setImporte(x);
		mMovimientos.addElement(m);
	}

	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {
			Movimiento m = (Movimiento) mMovimientos.elementAt(i);
			r += m.getImporte();
		}
		return r;
	}

	public double getCreditoDisponible() {
		return mCredito - getSaldo();
	}

	public void liquidar(int mes, int anyo) {
		Movimiento liq = new Movimiento();
		liq.setConcepto("Liquidación de operaciones tarj. crédito, " + (mes) + " de " + (anyo));
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {
			Movimiento m = (Movimiento) mMovimientos.elementAt(i);
			if (m.getFecha().getMonthValue() == mes && m.getFecha().getYear() == anyo)
				r += m.getImporte();
		}
		liq.setImporte(r);
		if (r != 0)
			mCuentaAsociada.addMovimiento(liq);
	}
}