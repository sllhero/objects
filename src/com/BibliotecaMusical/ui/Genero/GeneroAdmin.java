package com.BibliotecaMusical.ui.Genero;

import com.BibliotecaMusical.bl.Genero.Genero;
import com.BibliotecaMusical.tl.Controlador;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneroAdmin {
    public Button btn_registraGenero;
    public TableView tableGeneros;
    public TableColumn colNombre;
    public TableColumn colDescripcion;
    public TableColumn colEliminar;
    public TableColumn colModificar;
    Controlador controlador = new Controlador();

    public void registrarGenero(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Genero/Genero.fxml"));
        stage.setTitle("Biblioteca Musical");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void actualizarTabla(ActionEvent actionEvent) {
        ObservableList<Genero> genList = controlador.listaGeneros();
        tableGeneros.setItems(null);
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEliminar.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        colModificar.setCellValueFactory(new PropertyValueFactory<>("modificar"));

        tableGeneros.setItems(genList);

    }
}
