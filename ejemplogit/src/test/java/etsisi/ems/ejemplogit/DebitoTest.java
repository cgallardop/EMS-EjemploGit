package etsisi.ems.ejemplogit;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DebitoTest extends TestCase {
	Debito debito;
	Cuenta cuenta;

	public DebitoTest(String sTestName) {
		super(sTestName);
	}

	@Before
	public void setUp() throws Exception {
		cuenta = new Cuenta("0001.0002.12.1234567890", "Fulano");
		cuenta.ingresar(1000.0);
		Date hoy = new Date();
		LocalDate fechacaducidad = hoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		fechacaducidad.plusYears(4); // Caduca en 4 años
		debito = new Debito("1234567890123456", "Fulano", fechacaducidad);
		debito.setCuenta(cuenta);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetirar1000() {
		try {
			debito.retirar(1000.0);
			assertTrue(debito.getSaldo() == cuenta.getSaldo());
			assertTrue(debito.getSaldo() == 0.0);
		} catch (Exception e) {
			fail("No deberia haber fallado");
		}
	}

}