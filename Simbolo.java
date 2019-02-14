package Comp;

public class Simbolo {
	private String nombre;
	private String tipo;
	private String atributo;
	
	public Simbolo(String nombre, String tipo, String atributo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.atributo = atributo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getAtributo() {
		return atributo;
	}
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}


}
