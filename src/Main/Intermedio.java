package Main;

public class Intermedio extends Thread{
	private String id;
	private BufferProductores bff;
	private BufferIntermedio bff2;
	private BufferConsumidores bff3;
	private int tam;
	
	public Intermedio(String id,BufferProductores bff,BufferIntermedio bff2,BufferConsumidores bff3, int tam) {
		this.id = id;
		this.bff = bff;
		this.bff2 = bff2;
		this.bff3 = bff3;
		this.tam = tam;
	}
	
	synchronized public void run(){
		while(tam != 0) {
			if(id=="intermedio1") {
				Producto producto = bff.retirar("c");
				bff2.almacenar(producto);
			}
			if(id=="intermedio2") {
				Producto producto = bff2.retirar();
				bff3.almacenar(producto);
			}
			tam--;
		}
	}
}
