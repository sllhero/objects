package com.BibliotecaMusical.ui.Canciones;

import com.BibliotecaMusical.bl.Artista.Artista;
import com.BibliotecaMusical.bl.Compositor.Compositor;
import com.BibliotecaMusical.bl.Genero.Genero;
import com.BibliotecaMusical.bl.Usuario.Usuario;
import com.BibliotecaMusical.tl.Controlador;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

public class RegistroCancionCli {

    public DatePicker dp_fecha;
    public TextField txt_nombre;
    public TextField txt_Calificacion;
    public TextField txt_pathCancion;
    public ComboBox cb_compositor;
    public Button bntLP;
    Controlador controlador = new Controlador();
    public ComboBox cb_generos;
    public ComboBox cb_artista;
    Image img;
    String pathSong = "";


    public void listarGeneros(MouseEvent mouseEvent) {
        ObservableList<Genero> genresList = controlador.listaGeneros();
        cb_generos.setItems(genresList);
    }

    public void listarArtista(MouseEvent mouseEvent) {
        ObservableList<Artista> artistList = controlador.listaArtistas();
        cb_artista.setItems(artistList);
    }

    public void subirCancion(ActionEvent actionEvent) throws IOException {
        Scene scene = cb_generos.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));
        File selectedFile = fileChooser.showOpenDialog(stage);

        byte[] btSong = Files.readAllBytes(selectedFile.toPath());
        ByteArrayInputStream imgByteArray = new ByteArrayInputStream(btSong);
        pathSong = selectedFile.toPath().toString();
        txt_pathCancion.setText(pathSong);
    }

    public void listarCompositores(MouseEvent mouseEvent) {
        ObservableList<Compositor> compositorsList = controlador.listaCompositor();
        cb_compositor.setItems(compositorsList);
    }


    public void registrarCancion(ActionEvent actionEvent) {
        Compositor compositor = (Compositor) cb_compositor.getValue();
        Artista artista = (Artista) cb_artista.getValue();
        Genero genero = (Genero) cb_generos.getValue();
        LocalDate fechaLanzamiento = dp_fecha.getValue();
        String nombre = txt_nombre.getText();
        String pathCancion = txt_pathCancion.getText();
        String calificacion = txt_Calificacion.getText();
        int calificacionInt = Integer.parseInt(calificacion);

        controlador.registrarCancion(nombre,pathCancion, fechaLanzamiento, calificacionInt, genero,compositor, artista );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro correcto");
        alert.setHeaderText("La cancion ha sido registrada!");
        alert.setContentText("La cancion ha sido registrada de manera exitosa.");
        alert.show();

        limpiarCajas();

    }

    private void limpiarCajas() {
        dp_fecha.setValue(null);
        txt_nombre.setText(null);
        txt_pathCancion.setText(null);
        txt_Calificacion.setText(null);
        cb_generos.setValue(null);
        cb_artista.setValue(null);
        cb_compositor.setValue(null);

    }

    public void listarCanciones(ActionEvent actionEvent) throws IOException {

        Scene scene = bntLP.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        Parent root = FXMLLoader.load(getClass().getResource("../Canciones/listarCancionesCli.fxml"));
        stage.setScene(new Scene(root));
    }

    public void listaReproducciones(ActionEvent actionEvent) throws IOException {
        Scene scene = bntLP.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        Parent root = FXMLLoader.load(getClass().getResource("../inicioCliente/inicioCliente.fxml"));
        stage.setScene(new Scene(root));
    }

    public void registroGenero(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Genero/Genero.fxml"));
        stage.setTitle("Biblioteca Musical");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void registrarArtista(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Artista/Artista.fxml"));
        stage.setTitle("Biblioteca Musical");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void RegistraCompositor(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Compositor/registroCompositor.fxml"));
        stage.setTitle("Biblioteca Musical");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        Usuario.getInstance().setTipo(null);
        Usuario.getInstance().setNombreUsuario(null);
        Usuario.getInstance().setNombre(null);
        Usuario.getInstance().setTipo(null);
        Usuario.getInstance().setImg(null);
        Usuario.getInstance().setCorreo(null);
        Usuario.getInstance().setContrasenna(null);
        Usuario.getInstance().setIdUsuario(-1);


        Scene scene = dp_fecha.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        Parent root = FXMLLoader.load(getClass().getResource("/com/BibliotecaMusical/ui/InicioSesion/inicioSesion.fxml"));
        stage.setScene(new Scene(root));
    }

    public void inicionCliente(ActionEvent actionEvent) throws IOException {

        Scene scene = dp_fecha.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        Parent root = FXMLLoader.load(getClass().getResource("../inicioAdmin/inicioAdmin.fxml"));
        stage.setScene(new Scene(root));
    }

    public void generoLista(ActionEvent actionEvent) throws IOException {
        Scene scene = dp_fecha.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        Parent root = FXMLLoader.load(getClass().getResource("../Genero/generoAdmin.fxml"));
        stage.setScene(new Scene(root));
    }

    public void compositorLista(ActionEvent actionEvent) throws IOException {
        Scene scene = dp_fecha.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        Parent root = FXMLLoader.load(getClass().getResource("../Compositor/CompositorAdmin.fxml"));
        stage.setScene(new Scene(root));
    }

    public void artistaLista(ActionEvent actionEvent) throws IOException {
        Scene scene = dp_fecha.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        Parent root = FXMLLoader.load(getClass().getResource("../Artista/ArtistaAdmin.fxml"));
        stage.setScene(new Scene(root));
    }
}
