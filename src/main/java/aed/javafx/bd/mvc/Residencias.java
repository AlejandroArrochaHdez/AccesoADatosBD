package aed.javafx.bd.mvc;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Residencias {
	
	private IntegerProperty id;
	private StringProperty nombreResidencia;
	private StringProperty codUniversidad;
	private StringProperty nombreUniversidad;
	private IntegerProperty precioMensual;
	private BooleanProperty comedor;
	
	public Residencias(int id, String nombreResidencia, String codUniversidad, String nombreUniversidad, int precioMensual, boolean comedor) {
		this.id = new SimpleIntegerProperty(id);
		this.nombreResidencia = new SimpleStringProperty(nombreResidencia);
		this.codUniversidad = new SimpleStringProperty(codUniversidad);
		this.nombreUniversidad = new SimpleStringProperty(nombreUniversidad);
		this.precioMensual = new SimpleIntegerProperty(precioMensual);
		this.comedor = new SimpleBooleanProperty(comedor);
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
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
	

	public final StringProperty codUniversidadProperty() {
		return this.codUniversidad;
	}
	

	public final String getCodUniversidad() {
		return this.codUniversidadProperty().get();
	}
	

	public final void setCodUniversidad(final String codUniversidad) {
		this.codUniversidadProperty().set(codUniversidad);
	}
	

	public final IntegerProperty precioMensualProperty() {
		return this.precioMensual;
	}
	

	public final int getPrecioMensual() {
		return this.precioMensualProperty().get();
	}
	

	public final void setPrecioMensual(final int precioMensual) {
		this.precioMensualProperty().set(precioMensual);
	}
	

	public final BooleanProperty comedorProperty() {
		return this.comedor;
	}
	

	public final boolean isComedor() {
		return this.comedorProperty().get();
	}
	

	public final void setComedor(final boolean comedor) {
		this.comedorProperty().set(comedor);
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
	
	

	
	
}
