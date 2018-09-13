package com;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "estudiante")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
	
	String nombres;
	String apellidos;
	String curso;
	String calificacion1;
	String calificacion2;
	String calificacion3;
	String promedio;
	
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getCalificacion1() {
		return calificacion1;
	}
	public void setCalificacion1(String calificacion1) {
		this.calificacion1 = calificacion1;
	}
	public String getCalificacion2() {
		return calificacion2;
	}
	public void setCalificacion2(String calificacion2) {
		this.calificacion2 = calificacion2;
	}
	public String getCalificacion3() {
		return calificacion3;
	}
	public void setCalificacion3(String calificacion3) {
		this.calificacion3 = calificacion3;
	}
	public String getPromedio() {
		Double nota1 = Double.valueOf(getCalificacion1());
		Double nota2 = Double.valueOf(getCalificacion2());
		Double nota3 = Double.valueOf(getCalificacion3());
		promedio = String.valueOf((nota1+nota2+nota3)/3);
		return promedio;
	}
	public void setPromedio(String promedio) {
		this.promedio = promedio;
	}
	
	
	
	

}
