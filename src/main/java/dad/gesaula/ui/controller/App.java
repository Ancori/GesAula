package dad.gesaula.ui.controller;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	private PrincipalController controller;
	private static Stage primaryStage;

	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage=primaryStage;
		controller = new PrincipalController();
		Scene scene = new Scene(controller.getView());
		primaryStage.setTitle("GesAula");
		primaryStage.getIcons().add(new Image("images/app-icon-64x64.png"));
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
	

	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static boolean confirm(String titulo,String cabezero,String Contenido) {
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.initOwner(primaryStage);
		alert.setTitle(titulo);
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		return alert.showAndWait().get().equals(ButtonType.OK);
	}
	public static void error(String cabezero,String Contenido) {
		Alert alert=new Alert(AlertType.ERROR);
		alert.initOwner(primaryStage);
		alert.setTitle("Error");
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		alert.showAndWait();
	}
	public static void info(String titulo,String cabezero,String Contenido) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.initOwner(primaryStage);
		alert.setTitle(titulo);
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		alert.showAndWait();
	}

}
