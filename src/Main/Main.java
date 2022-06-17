package Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
	public static int cBuffers;
	public static int cProductores;
	public static int cProductos;
	public static int cConsumidores;
	public static int buzonesProd;
	public static int buzonesCons;

	public static BufferProductores buffProd;
	public static BufferConsumidores buffCons;
	public static BufferIntermedio buffIntermedio;

	public static Productor[] productores;
	public static Consumidor[] consumidores;

	public static Intermedio intermedio1;
	public static Intermedio intermedio2;

	public static void main(String[] args) {
		try (InputStream input = new FileInputStream("./data/cantidad.properties")) {
			Properties p = new Properties();
			p.load(input);

			buzonesProd = Integer.parseInt(p.getProperty("cantidad.buzonesProd"));
			cProductores = Integer.parseInt(p.getProperty("cantidad.productores"));
			buzonesCons = Integer.parseInt(p.getProperty("cantidad.buzonesCons"));
			cProductos = Integer.parseInt(p.getProperty("cantidad.productos"));

			buffProd = new BufferProductores(buzonesProd );
			buffCons = new BufferConsumidores(buzonesCons );
			buffIntermedio = new BufferIntermedio();

			productores = new Productor[cProductores];
			for (int i = 0; i < cProductores; i++) {
				if( i % 2 == 0)
					productores[i] = new Productor("a",i+"",cProductos,buffProd);
				else
					productores[i] = new Productor("b",i+"",cProductos,buffProd);
				productores[i].start();
			}

			consumidores = new Consumidor[cProductores];
			for (int i = 0; i < cProductores; i++) {
				if( i % 2 == 0)
					consumidores[i] = new Consumidor("a",buffCons,i+"",cProductos);
				else
					consumidores[i] = new Consumidor("b",buffCons,i+"",cProductos);
				consumidores[i].start();
			}

			intermedio1 = new Intermedio("intermedio1",buffProd,buffIntermedio,buffCons,cProductores*cProductos);
			intermedio1.start();
			intermedio2 = new Intermedio("intermedio2",buffProd,buffIntermedio,buffCons,cProductores*cProductos);
			intermedio2.start();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
