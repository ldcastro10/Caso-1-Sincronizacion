package Main;

public class Productor extends Thread{

	private String id;
	private BufferProductores bff;
	private int productos;
	private String tipo;

	public Productor(String tipo, String id, int pProductos,BufferProductores bff) {
		this.tipo = tipo;
		this.id = id;
		this.bff = bff;
		this.productos = pProductos;
	}

	synchronized public void run(){
		if(this.tipo=="a") {
			for (int i = 0; i < productos; i++) {
				Producto producto = new Producto(id, i,"a");
				bff.almacenar(producto);
			}
		}
		if(this.tipo=="b") {
			for (int i = 0; i < productos; i++) {
				Producto producto = new Producto(id, i,"b");
				bff.almacenar(producto);
			}
		}
		
		System.out.println("\n--------------------\nEl Productor " + id + " terminó de producir.\n--------------------");
	}

}
