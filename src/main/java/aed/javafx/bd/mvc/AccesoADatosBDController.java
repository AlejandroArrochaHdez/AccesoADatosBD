package aed.javafx.bd.mvc;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AccesoADatosBDController implements Initializable {

	private AccesoADatosBDModel model = new AccesoADatosBDModel();

	public boolean conectado = false, access = false;
	
	public int indiceCombo = 0;

	private ArrayList<String> codUni = new ArrayList<String>();

	private Stage secondStage, tercerStage, cuartaStage;

	private Connection con;

	public Connection getCon() {
		return con;
	}

	@FXML
	private GridPane viewModificar;

	@FXML
	private TextField residenciaTextModificar;

	@FXML
	private ComboBox<String> universidadComboModificar;

	@FXML
	private TextField precioTextModificar;

	@FXML
	private CheckBox comedorCheckModificar;

	@FXML
	private TextField residenciaText;

	@FXML
	private ComboBox<String> universidadCombo;

	@FXML
	private TextField precioText;

	@FXML
	private CheckBox comedorCheck;

	@FXML
	private GridPane view;

	@FXML
	private GridPane viewAlta;

	@FXML
	private ComboBox<String> dataBaseCombo;

	@FXML
	private TableView<Residencias> tableView;

	@FXML
	private TableColumn<Residencias, Integer> codResColumn;

	@FXML
	private TableColumn<Residencias, String> nomResColumn;

	@FXML
	private TableColumn<Residencias, String> codUniColumn;

	@FXML
	private TableColumn<Residencias, String> nomUniColumn;

	@FXML
	private TableColumn<Residencias, Integer> preMenColumn;

	@FXML
	private TableColumn<Residencias, Boolean> comColumn;
	
	@FXML
	private Button modificarButton;

	@FXML
	private Button eliminarButton;

	@FXML
	private Button insertarButton;
	
	@FXML
    private Button procButton;
	
	@FXML
	private Button conectarButton;

	@FXML
	private Button desconectarButton;
	
    @FXML
    private TabPane procView;

    @FXML
    private TextField dniTextEstanciaProc;

    @FXML
    private TableView<ProcedimientosEstancias> tableViewEstanciaProc;

    @FXML
    private TableColumn<ProcedimientosEstancias, String> nomResProcColumn;

    @FXML
    private TableColumn<ProcedimientosEstancias, String> nomUniProcColumn;

    @FXML
    private TableColumn<ProcedimientosEstancias, String> fecIniProcColumn;

    @FXML
    private TableColumn<ProcedimientosEstancias, String> fecFinProcColumn;

    @FXML
    private TableColumn<ProcedimientosEstancias, Integer> prePagProcColumn;

    @FXML
    private TextField precioTextUniProc;

    @FXML
    private ComboBox<String> comboUniProc;

    @FXML
    private Label uniProcLabel;

    @FXML
    private Label uniProcPreLabel;

    @FXML
    private TextField residenciaTextALtaProc;

    @FXML
    private ComboBox<String> universidadComboAltaProc;

    @FXML
    private TextField precioTextAltaProc;

    @FXML
    private CheckBox comedorCheckAltaProc;

    @FXML
    private TextField dniTextFunction;

    @FXML
    private Label mesesFuncLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model.comboDataBaseProperty().bind(dataBaseCombo.getSelectionModel().selectedItemProperty());
		dataBaseCombo.getItems().clear();
		dataBaseCombo.getItems().addAll("MySql", "SqlServer", "Access");
		dataBaseCombo.getSelectionModel().select(indiceCombo);

		tableView.itemsProperty().bind(model.listaResidenciasProperty());
		codResColumn.setCellValueFactory(new PropertyValueFactory<Residencias, Integer>("id"));
		nomResColumn.setCellValueFactory(new PropertyValueFactory<Residencias, String>("nombreResidencia"));
		codUniColumn.setCellValueFactory(new PropertyValueFactory<Residencias, String>("codUniversidad"));
		nomUniColumn.setCellValueFactory(new PropertyValueFactory<Residencias, String>("nombreUniversidad"));
		preMenColumn.setCellValueFactory(new PropertyValueFactory<Residencias, Integer>("precioMensual"));
		comColumn.setCellValueFactory(new PropertyValueFactory<Residencias, Boolean>("comedor"));
		
		modificarButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
		eliminarButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
	}

	public AccesoADatosBDController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AccesoADatosBDView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}

	private void mostrarTabla() {
		try {
			Statement visualizar;
			visualizar = con.createStatement();
			ResultSet col = visualizar.executeQuery(
					"SELECT codResidencia, nomResidencia, residencias.codUniversidad, nomUniversidad, precioMensual, comedor FROM residencias INNER JOIN universidades ON universidades.codUniversidad = residencias.codUniversidad");
			tableView.getItems().clear();
			while (col.next()) {
				Residencias x = new Residencias(col.getInt("codResidencia"), col.getString("nomResidencia"), col.getString("codUniversidad"), col.getString("nomUniversidad"),
						col.getInt("precioMensual"), col.getBoolean("comedor"));
				tableView.getItems().add(x);
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al mostrar la tabla");
			alert.showAndWait();
		}

	}

	private void conectarAccess() {
		try {
			if (conectado) {
				desconectar();
			}
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con = DriverManager.getConnection("jdbc:ucanaccess://residencias.accdb");
			mostrarTabla();
			conectado = true;
			access = true;
			indiceCombo = 2;
		} catch (ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al conectar con la base de datos");
			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al conectar con la base de datos");
			alert.showAndWait();
		}
	}
	
	private void conectarSQLServer() {
		try {
			if (conectado) {
				desconectar();
			}
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-2ILJVIFA\\SQLEXPRESS;DataBaseName=bdResidenciasEscolares","sa","sa");
			mostrarTabla();
			conectado = true;
			indiceCombo = 1;
		} catch (ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al conectar con la base de datos");
			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al conectar con la base de datos");
			alert.showAndWait();
		}
	}
	
	private void conectarMYSQL() {
		try {
			if (conectado) {
				desconectar();
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdresidenciasescolares", "root", "");
			mostrarTabla();
			conectado = true;
			indiceCombo = 0;
		} catch (ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al conectar con la base de datos");
			alert.showAndWait();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al conectar con la base de datos");
			alert.showAndWait();
		}
	}

	public void desconectar() {
		try {
			con.close();
			tableView.getItems().clear();
			conectado = false;
			access = false;
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al desconectar la conexión con la base de datos");
			alert.showAndWait();
		}
	}

	@FXML
	void onConectarAction(ActionEvent event) {
		if (dataBaseCombo.getSelectionModel().getSelectedItem() == "MySql") {
			conectarMYSQL();
		} else if (dataBaseCombo.getSelectionModel().getSelectedItem() == "SqlServer") {
			conectarSQLServer();
		} else {
			conectarAccess();
		}
	}

	@FXML
	void onDesconectarAction(ActionEvent event) {
		if (conectado) {
			desconectar();
		}
		tableView.getItems().clear();
	}
	

	@FXML
	void onAceptarAction(ActionEvent event) {
		String nomResidencia, codUnString;
		int precioMensual = 0;
		boolean comedor;
		nomResidencia = residenciaText.getText();
		codUnString = codUni.get(universidadCombo.getSelectionModel().getSelectedIndex());
		try {
			precioMensual = Integer.parseInt(precioText.getText());
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("El campo precio mensual es debe ser un número");
			alert.showAndWait();
		}
		comedor = comedorCheck.isSelected();
		try {
			PreparedStatement insertar = con.prepareStatement(
					"INSERT INTO residencias(nomResidencia,codUniversidad,precioMensual,comedor) VALUES (?,?,?,?)");
			insertar.setString(1, nomResidencia);
			insertar.setString(2, codUnString);
			insertar.setInt(3, precioMensual);
			insertar.setBoolean(4, comedor);
			insertar.execute();
			mostrarTabla();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al introducir la residencia");
			alert.showAndWait();
		}
		secondStage.close();
	}

	@FXML
	void onAceptarModificarAction(ActionEvent event) {
		String nomResidencia, codUnString;
		int precioMensual = 0;
		boolean comedor;
		nomResidencia = residenciaTextModificar.getText();
		codUnString = codUni.get(universidadComboModificar.getSelectionModel().getSelectedIndex());
		try {
			precioMensual = Integer.parseInt(precioTextModificar.getText());
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("El campo precio mensual es debe ser un número");
			alert.showAndWait();
		}
		comedor = comedorCheckModificar.isSelected();
		try {
			PreparedStatement insertar = con.prepareStatement(
					"UPDATE residencias SET nomResidencia = ?, codUniversidad = ?, precioMensual = ?, comedor = ? WHERE codResidencia = ?");
			insertar.setString(1, nomResidencia);
			insertar.setString(2, codUnString);
			insertar.setInt(3, precioMensual);
			insertar.setBoolean(4, comedor);
			insertar.setInt(5, tableView.getSelectionModel().getSelectedItem().getId());
			insertar.execute();
			mostrarTabla();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al modificar la residencia");
			alert.showAndWait();
		}
		tercerStage.close();
	}

	@FXML
	void onEliminarAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar");
		alert.setHeaderText("Eliminar");
		alert.setContentText("¿Está seguro de eliminar la residencia?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				PreparedStatement eliminar = con.prepareStatement(
						"DELETE FROM residencias WHERE codResidencia = ?");
				eliminar.setInt(1, tableView.getSelectionModel().getSelectedItem().getId());
				eliminar.execute();
				mostrarTabla();
			} catch (SQLException e) {
				Alert alert2 = new Alert(AlertType.ERROR);
				alert2.setTitle("Error");
				alert2.setHeaderText("ERROR");
				alert2.setContentText("Error al eliminar la residencia");
				alert2.showAndWait();
			}
		} else {
		}
	}

	@FXML
	void onInsertar(ActionEvent event) throws IOException {
		if (conectado) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AltaView.fxml"));
			loader.setController(this);
			loader.load();
			try {
				Statement comboUni = con.createStatement();
				ResultSet com = comboUni.executeQuery(
						"SELECT codUniversidad,nomUniversidad nomCodUniversidad FROM universidades ORDER BY codUniversidad;");
				while (com.next()) {
					codUni.add(com.getString(1));
					universidadCombo.getItems().add(com.getString(2));
				}
				universidadCombo.getSelectionModel().selectFirst();
			} catch (SQLException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("ERROR");
				alert.setContentText("Error al conectar con la base de datos");
				alert.showAndWait();
			}

			secondStage = new Stage();

			secondStage.setTitle("Acceso a datos");
			secondStage.setScene(new Scene(viewAlta));
			secondStage.show();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Primero debe conectarse a la base de datos");
			alert.showAndWait();
		}
	}

	@FXML
	void onModificarAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ModificarView.fxml"));
		loader.setController(this);
		loader.load();
		try {
			String nombreResidencia = tableView.getSelectionModel().getSelectedItem().getNombreResidencia();
			String nombreUniversidad = tableView.getSelectionModel().getSelectedItem().getNombreUniversidad();
			int precioMensual = tableView.getSelectionModel().getSelectedItem().getPrecioMensual();
			boolean comedor = tableView.getSelectionModel().getSelectedItem().isComedor();
			residenciaTextModificar.setText(nombreResidencia);
			precioTextModificar.setText("" + precioMensual);
			comedorCheckModificar.setSelected(comedor);
			Statement comboUni = con.createStatement();
			ResultSet com = comboUni.executeQuery(
					"SELECT codUniversidad,nomUniversidad nomCodUniversidad FROM universidades ORDER BY codUniversidad;");
			while (com.next()) {
				codUni.add(com.getString(1));
				universidadComboModificar.getItems().add(com.getString(2));
			}
			universidadComboModificar.getSelectionModel().select(nombreUniversidad);
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al conectar con la base de datos");
			alert.showAndWait();
		}

		tercerStage = new Stage();

		tercerStage.setTitle("Acceso a datos");
		tercerStage.setScene(new Scene(viewModificar));
		tercerStage.show();
	}

	@FXML
	void onProcedimientosAction(ActionEvent event) throws IOException {
		if (conectado) {
		if (!access) {
			
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProcedimientosView.fxml"));
			loader.setController(this);
			loader.load();
			
			try {
				Statement comboUni = con.createStatement();
				ResultSet com = comboUni.executeQuery(
						"SELECT codUniversidad,nomUniversidad nomCodUniversidad FROM universidades ORDER BY codUniversidad;");
				while (com.next()) {
					codUni.add(com.getString(1));
					universidadComboAltaProc.getItems().add(com.getString(2));
					comboUniProc.getItems().add(com.getString(2));
				}
				universidadComboAltaProc.getSelectionModel().selectFirst();
				comboUniProc.getSelectionModel().selectFirst();
			} catch (SQLException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("ERROR");
				alert.setContentText("Error al conectar con la base de datos");
				alert.showAndWait();
			}
			
			cuartaStage = new Stage();
			cuartaStage.setTitle("Acceso a datos");
			cuartaStage.setScene(new Scene(procView));
			cuartaStage.show();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("No existen procedimientos en Access");
			alert.showAndWait();
		}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Primero debe conectarse a la base de datos");
			alert.showAndWait();
		}
	}
	
    @FXML
    void onAceptarAltaProcAction(ActionEvent event) {
    	try {
    		int precio = 0;
    		try {
				precio = Integer.parseInt(precioTextAltaProc.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("ERROR");
				alert.setContentText("El campo precio mensual es debe ser un número");
				alert.showAndWait();
			}
			CallableStatement consulta = con.prepareCall("{Call insertarRes(?,?,?,?,?,?)}");
			consulta.registerOutParameter(1, Types.INTEGER);
			consulta.registerOutParameter(2, Types.INTEGER);
			consulta.setString(3, residenciaTextALtaProc.getText());
			consulta.setString(4, codUni.get(universidadComboAltaProc.getSelectionModel().getSelectedIndex()));
			consulta.setInt(5, precio);
			consulta.setBoolean(6, comedorCheckAltaProc.isSelected());
			consulta.execute();
			cuartaStage.close();
			mostrarTabla();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al ejecutar el procedimiento");
			alert.showAndWait();
		}
    }

    @FXML
    void onBuscarEstanciaProcAction(ActionEvent event) {
    	try {
			tableViewEstanciaProc.getItems().clear();
			nomResProcColumn.setCellValueFactory(new PropertyValueFactory<ProcedimientosEstancias, String>("nombreResidencia"));
			nomUniProcColumn.setCellValueFactory(new PropertyValueFactory<ProcedimientosEstancias, String>("nombreUniversidad"));
			fecIniProcColumn.setCellValueFactory(new PropertyValueFactory<ProcedimientosEstancias, String>("fechaInicio"));
			fecFinProcColumn.setCellValueFactory(new PropertyValueFactory<ProcedimientosEstancias, String>("fechaFin"));
			prePagProcColumn.setCellValueFactory(new PropertyValueFactory<ProcedimientosEstancias, Integer>("precioPagado"));
			CallableStatement consulta = con.prepareCall("{CALL residenciasEstudiantes(?)}");
			consulta.setString(1, dniTextEstanciaProc.getText());
			ResultSet result = consulta.executeQuery();
			while (result.next()) {
				tableViewEstanciaProc.getItems().add(new ProcedimientosEstancias(result.getString(1), result.getString(2),
						result.getDate(3).toString(), result.getDate(4).toString(), result.getInt(5)));
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al ejecutar el procedimiento");
			alert.showAndWait();
		}
    }	

    @FXML
    void onBuscarFunctionAcion(ActionEvent event) {
    	try {
			CallableStatement consulta = con.prepareCall("{?=Call meses(?)}");
			consulta.registerOutParameter(1, Types.INTEGER);
			consulta.setString(2, dniTextFunction.getText());
			consulta.execute();
			mesesFuncLabel.setText("" + consulta.getInt(1));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al ejecutar el procedimiento");
			alert.showAndWait();
		}
    }

    @FXML
    void onBuscarProcUniAction(ActionEvent event) {
    	try {
    		int precio = 0;
    		try {
				precio = Integer.parseInt(precioTextUniProc.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("ERROR");
				alert.setContentText("El campo precio mensual es debe ser un número");
				alert.showAndWait();
			}
			CallableStatement consulta = con.prepareCall("{Call residenciasPorUni(?,?,?,?)}");
			consulta.registerOutParameter(1, Types.INTEGER);
			consulta.registerOutParameter(2, Types.INTEGER);
			consulta.setString(3, comboUniProc.getSelectionModel().getSelectedItem());
			consulta.setInt(4, precio);
			consulta.execute();
			uniProcLabel.setText("" + consulta.getInt(1));
			uniProcPreLabel.setText("" + consulta.getInt(2));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("ERROR");
			alert.setContentText("Error al ejecutar el procedimiento");
			alert.showAndWait();
		}
    }
}
