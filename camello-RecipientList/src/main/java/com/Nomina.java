package com;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name= "nomina")
@XmlAccessorType(XmlAccessType.FIELD)
public class Nomina {
	@XmlElement(name = "empleados")
	 Empleados empleados;
	 public Empleados getEmpleados() {
			return empleados;
		}


		public void setEmpleados(Empleados empleados) {
			this.empleados = empleados;
		}

}


class Aportes{
	int salud;
	int pension;
	public int getSalud() {
		return salud;
	}
	public void setSalud(int salud) {
		this.salud = salud;
	}
	public int getPension() {
		return pension;
	}
	public void setPension(int pension) {
		this.pension = pension;
	}
	
}
