package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;

public class EditAlumnoController implements Initializable {
	
	private ObjectProperty<Alumno> alumno = new SimpleObjectProperty<>();
	private StringProperty nombre=new SimpleStringProperty();
	private StringProperty apellidos=new SimpleStringProperty();
	private ObjectProperty<LocalDate> fechaNacimiento=new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<Sexo> sexo=new SimpleObjectProperty<Sexo>();
	private BooleanProperty repite=new SimpleBooleanProperty();
	
	@FXML
	private GridPane view;

	@FXML
	private TextField nombretext;

	@FXML
	private TextField apellidostext;

	@FXML
	private DatePicker nacimientodate;

	@FXML
	private ComboBox<Sexo> sexobox;

	@FXML
	private CheckBox repitecheck;

	public EditAlumnoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditAlumnoView.fxml"));
		loader.setController(this);
		loader.load();
	}


	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
       nombretext.textProperty().bindBidirectional(nombre);
       apellidostext.textProperty().bindBidirectional(apellidos);
       nacimientodate.valueProperty().bindBidirectional(fechaNacimiento);
       alumno.addListener((o, ov, nv) -> onseleccionadochanged(o, ov, nv));
       repitecheck.selectedProperty().bindBidirectional(repite);
       sexobox.getItems().addAll(sexo.get().values());
       sexobox.valueProperty().bindBidirectional(sexo);
	}


	private void onseleccionadochanged(ObservableValue<? extends Alumno> o, Alumno ov, Alumno nv) {
		if (ov != null) {
			// desbindeo el bean viejo
			nombre.unbindBidirectional(ov.nombreProperty());
			apellidos.unbindBidirectional(ov.apellidosProperty());
			fechaNacimiento.unbindBidirectional(ov.fechaNacimientoProperty());
			sexo.unbindBidirectional(ov.sexoProperty());
			repite.unbindBidirectional(ov.repiteProperty());
		}

		if (nv != null) {
			// bindeo el bean nuevo
			nombre.bindBidirectional(nv.nombreProperty());
			apellidos.bindBidirectional(nv.apellidosProperty());
			fechaNacimiento.bindBidirectional(nv.fechaNacimientoProperty());
			sexo.bindBidirectional(nv.sexoProperty());
			repite.bindBidirectional(nv.repiteProperty());
		}
	
	}

	public GridPane getView() {
		return view;
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
