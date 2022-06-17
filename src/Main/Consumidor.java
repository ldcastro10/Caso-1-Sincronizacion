package Main;

public class Consumidor extends Thread{
	
	private BufferConsumidores buffer;
	private String nombre;
	private int productos;
	private String tipo; 
	
	
	public Consumidor(String tipo, BufferConsumidores pBuffer, String nom,int nProductos) {
		this.tipo = tipo;
		buffer = pBuffer;
		this.nombre = nom;
		this.productos = nProductos;
	} 
	
	public void run(){
		for (int i = 0; i < productos; i++) {
			if(this.tipo == "a") {
				Producto producto = buffer.retirar("a");
				System.out.println(producto.getProducto() + " ha sido entregado al consumidor " + nombre+" de tipo "+ tipo);
				producto.cambiarConsumido();
			}
			if(this.tipo == "b"){
				Producto producto = buffer.retirar("b");
				System.out.println(producto.getProducto() + " fue tomado por el consumidor " + nombre +" tipo "+tipo);
				producto.cambiarConsumido();
			}
		}
		
		System.out.println(" \n--------------------\nEl Consumidor " + nombre + " ya tomó todos sus productos \n--------------------");
	}	
}
