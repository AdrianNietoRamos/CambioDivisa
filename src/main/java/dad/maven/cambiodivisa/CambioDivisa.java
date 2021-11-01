package dad.maven.cambiodivisa;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private TextField dineroText;
	private ComboBox<Divisa> divisaCombo;
	private TextField cambioText;
	private ComboBox<Divisa> divisaCombo2;
	private Button cambioButton;
	Alert error = new Alert(AlertType.ERROR);
	@Override
	public void start(Stage primaryStage) throws Exception {
		Divisa euro = new Divisa("Euro", 1.0);
		Divisa libra = new Divisa("Libra", 0.8873);
		Divisa dolar = new Divisa("Dolar", 1.2007);
		Divisa yen = new Divisa("Yen", 133.59);
		
		dineroText=new TextField();
		dineroText.setMaxWidth(75);
		divisaCombo=new ComboBox<Divisa>();
		divisaCombo.getItems().addAll(euro,libra,dolar,yen);
		divisaCombo.getSelectionModel().select(0);
		HBox primerBox=new HBox(5,dineroText,divisaCombo);
		primerBox.setAlignment(Pos.CENTER);
		cambioText=new TextField();
		cambioText.setMaxWidth(75);
		
		divisaCombo2=new ComboBox<Divisa>();
		divisaCombo2.getItems().addAll(euro,libra,dolar,yen);
		divisaCombo2.getSelectionModel().select(0);
		HBox segundoBox=new HBox(5,cambioText,divisaCombo2);
		segundoBox.setAlignment(Pos.CENTER);
		cambioButton=new Button("Cambiar");
		cambioButton.setDefaultButton(true);
		cambioButton.setOnAction(e -> onCambiarButton());
		VBox root = new VBox(5, primerBox, segundoBox, cambioButton);
		root.setAlignment(Pos.CENTER);
		root.setFillWidth(false);
		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("CambioDivisa");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private void onCambiarButton() {
		
		try {
		
		Divisa divisaEntrada=divisaCombo.getSelectionModel().getSelectedItem();
		Divisa divisaSalida=divisaCombo2.getSelectionModel().getSelectedItem();
		double cant=Double.parseDouble(dineroText.getText());
		Divisa.fromTo(divisaEntrada, divisaSalida,cant);
		cambioText.setText(Divisa.fromTo(divisaEntrada, divisaSalida,cant).toString());
		cambioText.disableProperty();
		
	}catch (NumberFormatException e1) {
		error.setTitle("Fallastes");
		error.setHeaderText("Introduce un numero");
		error.showAndWait();
		;
	}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
