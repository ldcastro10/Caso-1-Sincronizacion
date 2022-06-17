package Main;

import java.util.ArrayList;

public class BufferConsumidores {
	private ArrayList<Producto> buff;
	private int n;
	Object lleno, vacio;
	Object enEspera;

	public BufferConsumidores ( int nBuffers) {
		this.n = nBuffers;
		buff = new ArrayList<>( );
		lleno = new Object();
		vacio = new Object();
	}

	public void almacenar(Producto m){
		boolean continuar = true;
		while(continuar) {
			synchronized(this) {
				if(buff.size()<n) {
					buff.add(m);
					continuar = false;
				}
			}
			if(continuar) {
				synchronized(lleno) {
					try{lleno.wait(); }
					catch (Exception e) { e.printStackTrace(); }
				}
			}	
		}
	}

	public Producto retirar(String pTipo){
		boolean continuar = true;
		boolean hay = false;
		int posicion = 0;
		Producto m =null;
		while(continuar) {
			synchronized(this) {
				if(pTipo=="c") {
					hay = true;
					posicion = 0;
				}
				else{
					for(int i = 0; i<buff.size() ; i++) {
						if(buff.get(i).getTipo() == pTipo){
							hay = true;
							posicion = i;
							break;
						}
					}
				}
				if(buff.size()>0 && hay == true) {
					m = buff.remove(posicion);
					continuar = false;
				}
			}
			if(continuar) {
				Thread.yield(); 
			}
		}
		synchronized(lleno) {
			try{ lleno.notify();}
			catch (Exception e) { e.printStackTrace(); }
		}
		return m;
	}
}
