
package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import dad.gesaula.ui.model.Grupo;
import dad.gesaula.ui.model.Pesos;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class GrupoController implements Initializable {
	ObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();
	ObjectProperty<Pesos> pesos = new SimpleObjectProperty<>();

	@FXML
	private GridPane view;

	@FXML
	private DatePicker dateinicio;

	@FXML
	private DatePicker datefin;

	@FXML
	private TextField denominaciontext;

	@FXML
	private Slider examenesbar;

	@FXML
	private Slider practicasbar;

	@FXML
	private Slider actitudbar;

	@FXML
	private Label exameneslabel;

	@FXML
	private Label practicaslabel;

	@FXML
	private Label actitudlabel;

	public GrupoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GrupoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setGrupo(new Grupo());
		setPesos(new Pesos());
		denominaciontext.textProperty().bindBidirectional(grupo.get().denominacionProperty());
		grupo.get().iniCursoProperty().bind(dateinicio.valueProperty());
		grupo.get().finCursoProperty().bind(datefin.valueProperty());
		
		examenesbar.valueProperty().addListener((o, ov, nv) -> onexaChanged(o, ov, nv));
		practicasbar.valueProperty().addListener((o, ov, nv) -> onpracChanged(o, ov, nv));
		actitudbar.valueProperty().addListener((o, ov, nv) -> onactChanged(o, ov, nv));
		grupo.addListener((o, ov, nv) -> ongrupoChanged(o, ov, nv));
		

	}

	private void ongrupoChanged(ObservableValue<? extends Grupo> o, Grupo ov, Grupo nv) {
		if (ov != null) {
			denominaciontext.clear();
            dateinicio.getEditor().clear();
            datefin.getEditor().clear();
            examenesbar.setValue(0);
            practicasbar.setValue(0);
            actitudbar.setValue(0);
		}

		if (nv != null) {
			denominaciontext.textProperty().bindBidirectional(nv.denominacionProperty());
			dateinicio.valueProperty().bind(nv.iniCursoProperty());
			datefin.valueProperty().bind(nv.finCursoProperty());
		}

	}

	private void onactChanged(ObservableValue<? extends Number> o, Number ov, Number nv) {
		actitudlabel.setText(nv.toString() + "%");
		pesos.get().actitudProperty().bind(o);
	}

	private void onpracChanged(ObservableValue<? extends Number> o, Number ov, Number nv) {
		practicaslabel.setText(nv.toString() + "%");
		pesos.get().practicasProperty().bind(o);
	}

	private void onexaChanged(ObservableValue<? extends Number> o, Number ov, Number nv) {
		exameneslabel.setText(nv.toString() + "%");
		pesos.get().examenesProperty().bind(o);
	}

	public GridPane getView() {
		return view;
	}

	public ObjectProperty<Grupo> grupoProperty() {
		return this.grupo;
	}

	public Grupo getGrupo() {
		return this.grupoProperty().get();
	}

	public void setGrupo(final Grupo grupo) {
		this.grupoProperty().set(grupo);
	}

	public ObjectProperty<Pesos> pesosProperty() {
		return this.pesos;
	}
	

	public Pesos getPesos() {
		return this.pesosProperty().get();
	}
	

	public void setPesos(final Pesos pesos) {
		this.pesosProperty().set(pesos);
	}
	
	

}
