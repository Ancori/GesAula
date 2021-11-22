package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Alumno;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class AlumnoController implements Initializable {
	EditAlumnoController editalumnocontroller=new EditAlumnoController();
	private ObjectProperty<Alumno> alumno = new SimpleObjectProperty<>();
	private ListProperty<Alumno> alumnos = new SimpleListProperty<>(FXCollections.observableArrayList());

	@FXML
	private BorderPane view;

	@FXML
	private TableView<Alumno> table;

	@FXML
	private TableColumn<Alumno, String> NombreColumn;

	@FXML
	private TableColumn<Alumno, String> ApellidosColumn;

	@FXML
	private TableColumn<Alumno, LocalDate> FechaColumn;

	@FXML
	private Button nuevoalumnobutton;

	@FXML
	private Button deletealumnobutton;

	public AlumnoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		alumnos.bind(table.itemsProperty());
		alumno.bind(table.getSelectionModel().selectedItemProperty());
       
		NombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		ApellidosColumn.setCellValueFactory(v -> v.getValue().apellidosProperty());
		FechaColumn.setCellValueFactory(v -> v.getValue().fechaNacimientoProperty());
	}

	@FXML
	void ondeletealumnobutton(ActionEvent event) {
		if (App.confirm("GesAula",
				"Se va a elimar el alumno " + alumno.get().getNombre() + " " + alumno.get().getApellidos(),
				"¿Estas segur@?")) {
			alumnos.remove(alumno.get());
		}

	}

	@FXML
	void onnuevoalumnobutton(ActionEvent event) {
		Alumno alumnonuevo = new Alumno();
		alumnonuevo.setNombre("Sin nombre");
		alumnonuevo.setApellidos("Sin apellidos");
		alumnos.add(alumnonuevo);
		

	}

	public BorderPane getView() {
		return view;
	}

	public ListProperty<Alumno> alumnosProperty() {
		return this.alumnos;
	}

	public ObservableList<Alumno> getAlumnos() {
		return this.alumnosProperty().get();
	}

	public void setAlumnos(final ObservableList<Alumno> alumnos) {
		this.alumnosProperty().set(alumnos);
	}

	public ObjectProperty<Alumno> alumnoProperty() {
		return this.alumno;
	}
	

	public Alumno getAlumno() {
		return this.alumnoProperty().get();
	}
	

	public void setAlumno(final Alumno alumno) {
		this.alumnoProperty().set(alumno);
	}
	
	

}
