package Main;

public class Producto {
	
	private String producto;
	
	private String tipo;
	
	private boolean consumido = false;
	
	private int numProducto; 
	
	
	public Producto( String pProducto, int i, String pTipo) {
		producto = pProducto;
		numProducto = i;
		tipo = pTipo;
	}
	
	public String getProducto() {
		return "Un Producto del productor " + producto +" de tipo "+ tipo;
	}
	
	public synchronized void cambiarConsumido() {
		consumido = !consumido;
		notify();
	}
	public String getTipo() {
		return tipo;
	}
	
}
