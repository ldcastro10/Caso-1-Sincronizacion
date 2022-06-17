package Main;

import java.util.ArrayList;

public class BufferIntermedio {
	private ArrayList<Producto> buff;
	private int n;	
	Object lleno, vacio;

	public BufferIntermedio() {
		this.n = 1;
		buff = new ArrayList<>( );
		lleno = new Object();
		vacio = new Object();
	}
	public void almacenar ( Producto m ){
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
		synchronized(vacio) {
			try { vacio.notify();}
			catch (Exception e) {e.printStackTrace();}
		}
	}


	public Producto retirar (){
		boolean continuar = true;
		Producto m =null;
		while(continuar) {
			synchronized(this) {
				if(buff.size()>0) {
					m = buff.remove(0);
					continuar = false;
				}
			}
			if(continuar) {
				synchronized(vacio) {
					try{vacio.wait();}
					catch (Exception e) { e.printStackTrace(); }
				}
			}
		}
		synchronized(lleno) {
			try{ lleno.notify();}
			catch (Exception e) { e.printStackTrace(); }
		}
		return m;
	}
}


