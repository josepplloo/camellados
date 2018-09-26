package com;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name= "empleado")
@XmlAccessorType(XmlAccessType.FIELD)
public class Empleado {
	 String nombres;
	 String apellidos;
	 String documento;
	 Object Aportes;
	 Object subtotal;
	 int total;
	 
	
	 
	 
	public String getNombres() {
		return nombres;
	}
	public  void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Object getAportes() {
		return Aportes;
	}
	public void setAportes(Object aportes) {
		Aportes = aportes;
	}
	public Object getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Object subtotal) {
		this.subtotal = subtotal;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
