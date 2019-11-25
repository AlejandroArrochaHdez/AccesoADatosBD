package aed.javafx.bd;

import aed.javafx.bd.mvc.AccesoADatosBDController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccesoADatosBDApp extends Application {
	private AccesoADatosBDController accesoADatosBDController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		accesoADatosBDController = new AccesoADatosBDController();
		
		Scene scene = new Scene(accesoADatosBDController.getView());
		
		primaryStage.setTitle("Acceso a datos");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		if (accesoADatosBDController.conectado) {
			accesoADatosBDController.desconectar();
		}
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
