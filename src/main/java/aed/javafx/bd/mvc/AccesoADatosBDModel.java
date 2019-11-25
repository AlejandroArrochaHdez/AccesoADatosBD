package aed.javafx.bd.mvc;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccesoADatosBDModel {
	
	private StringProperty comboDataBase = new SimpleStringProperty();
	private ListProperty<Residencias> listaResidencias = new SimpleListProperty<Residencias>(FXCollections.observableArrayList());
	private ListProperty<ProcedimientosEstancias> listaEstancias = new SimpleListProperty<ProcedimientosEstancias>(FXCollections.observableArrayList());
	
	public final StringProperty comboDataBaseProperty() {
		return this.comboDataBase;
	}
	
	public final String getComboDataBase() {
		return this.comboDataBaseProperty().get();
	}
	
	public final void setComboDataBase(final String comboDataBase) {
		this.comboDataBaseProperty().set(comboDataBase);
	}
	
	public final ListProperty<Residencias> listaResidenciasProperty() {
		return this.listaResidencias;
	}
	
	public final ObservableList<Residencias> getListaResidencias() {
		return this.listaResidenciasProperty().get();
	}
	
	public final void setListaResidencias(final ObservableList<Residencias> listaResidencias) {
		this.listaResidenciasProperty().set(listaResidencias);
	}

	public final ListProperty<ProcedimientosEstancias> listaEstanciasProperty() {
		return this.listaEstancias;
	}
	

	public final ObservableList<ProcedimientosEstancias> getListaEstancias() {
		return this.listaEstanciasProperty().get();
	}
	

	public final void setListaEstancias(final ObservableList<ProcedimientosEstancias> listaEstancias) {
		this.listaEstanciasProperty().set(listaEstancias);
	}
	
	
	
}
