package aed.javafx.bd.mvc;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProcedimientosEstancias {
	
	private StringProperty nombreResidencia;
	private StringProperty nombreUniversidad;
	private StringProperty fechaInicio;
	private StringProperty fechaFin;
	private IntegerProperty precioPagado;
	
	public ProcedimientosEstancias(String nombreResidencia,String nombreUniversidad, String fechaInicio, String fechaFin, int precioPagado) {
		this.nombreResidencia = new SimpleStringProperty(nombreResidencia);
		this.nombreUniversidad = new SimpleStringProperty(nombreUniversidad);
		this.fechaInicio = new SimpleStringProperty(fechaInicio);
		this.fechaFin = new SimpleStringProperty(fechaFin);
		this.precioPagado = new SimpleIntegerProperty(precioPagado);
	}

	public final StringProperty nombreResidenciaProperty() {
		return this.nombreResidencia;
	}
	

	public final String getNombreResidencia() {
		return this.nombreResidenciaProperty().get();
	}
	

	public final void setNombreResidencia(final String nombreResidencia) {
		this.nombreResidenciaProperty().set(nombreResidencia);
	}
	

	public final StringProperty nombreUniversidadProperty() {
		return this.nombreUniversidad;
	}
	

	public final String getNombreUniversidad() {
		return this.nombreUniversidadProperty().get();
	}
	

	public final void setNombreUniversidad(final String nombreUniversidad) {
		this.nombreUniversidadProperty().set(nombreUniversidad);
	}
	

	public final StringProperty fechaInicioProperty() {
		return this.fechaInicio;
	}
	

	public final String getFechaInicio() {
		return this.fechaInicioProperty().get();
	}
	

	public final void setFechaInicio(final String fechaInicio) {
		this.fechaInicioProperty().set(fechaInicio);
	}
	

	public final StringProperty fechaFinProperty() {
		return this.fechaFin;
	}
	

	public final String getFechaFin() {
		return this.fechaFinProperty().get();
	}
	

	public final void setFechaFin(final String fechaFin) {
		this.fechaFinProperty().set(fechaFin);
	}
	

	public final IntegerProperty precioPagadoProperty() {
		return this.precioPagado;
	}
	

	public final int getPrecioPagado() {
		return this.precioPagadoProperty().get();
	}
	

	public final void setPrecioPagado(final int precioPagado) {
		this.precioPagadoProperty().set(precioPagado);
	}
	

}
