package dad.gesaula.ui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Grupo;
import dad.gesaula.ui.model.Pesos;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class PrincipalController implements Initializable {
	GrupoController grupocontroller = new GrupoController();
	AlumnoController alumnocontroller = new AlumnoController();
	EditAlumnoController editalumnocontroller = new EditAlumnoController();
	private StringProperty nombregrupo = new SimpleStringProperty();

	@FXML
	private BorderPane view;

	@FXML
	private TextField grupotext;

	@FXML
	private Button nuevobutton;

	@FXML
	private Button savebutton;

	@FXML
	private BorderPane grupopane;

	@FXML
	private BorderPane alumnopane;

	@FXML
	private BorderPane seleccionadopane;

	public PrincipalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		grupopane.setCenter(grupocontroller.getView());
		alumnopane.setCenter(alumnocontroller.getView());
		nombregrupo.bind(grupotext.textProperty());
		alumnocontroller.alumnoProperty().addListener((o, ov, nv) -> onseleccionadochanged(o, ov, nv));
	}

	private void onseleccionadochanged(ObservableValue<? extends Alumno> o, Alumno ov, Alumno nv) {
		if (nv != null) {
			seleccionadopane.setCenter(editalumnocontroller.getView());
			editalumnocontroller.alumnoProperty().bind(alumnocontroller.alumnoProperty());
		} else {
			seleccionadopane.setCenter(new Label("Seleccione un alumno de la tabla izquierda"));
		}
	}

	@FXML
	void onnuevobutton(ActionEvent event) {
		grupocontroller.setGrupo(new Grupo());
		grupocontroller.setPesos(new Pesos());
		alumnocontroller.getAlumnos().clear();
	}

	@FXML
	void onsavebutton(ActionEvent event) {
		grupocontroller.getGrupo().setPesos(grupocontroller.getPesos());
		grupocontroller.getGrupo().setAlumnos(alumnocontroller.getAlumnos());
		;
		try {
			grupocontroller.getGrupo().save(new File(nombregrupo.get() + ".xml"));
			App.info("GesAula", "Archivo guardado con exito", null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public BorderPane getView() {
		return view;
	}

}
