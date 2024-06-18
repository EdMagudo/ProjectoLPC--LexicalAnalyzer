package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import DAO.AnalisadorToken;
import DAO.tabelaToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

public class MainController implements Initializable {

    @FXML
    private TextArea campo;

    @FXML
    private TableColumn<tabelaToken, String> erro;

    @FXML
    private TableColumn<tabelaToken, String> lexema;

    @FXML
    private TableColumn<tabelaToken, Integer> linha;

    @FXML
    private ImageView new_File;

    @FXML
    private ImageView open_Folder;

    @FXML
    private ImageView run;

    @FXML
    private ImageView save;

    @FXML
    private ImageView settings;

    @FXML
    private TableView<tabelaToken> tabela;

    @FXML
    private Text time;

    @FXML
    private TableColumn<tabelaToken, String> token;

    @FXML
    private Button touch;

    @FXML
    private VBox linhas;

    FileChooser fileChooser = new FileChooser();

    ObservableList<tabelaToken> tokensList = FXCollections.observableArrayList();
    String fileIn = new String();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabela.getItems().clear();

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Ficheiro de Texto", "*.txt"),
        new FileChooser.ExtensionFilter("Ficheiro de Pascal", "*.pas"));

        tabela.setPlaceholder(new Label(""));
        
        campo.textProperty().addListener((observable,oldValue,newValue) -> {
            colocarLinhas(newValue);
        });

    }

    String paragrafos;
    String[] palavras;
    List<tabelaToken> lisTabelaTokens;
    @SuppressWarnings("rawtypes")
    List linhas_por_palavra;

    @FXML
    void create(MouseEvent event) {
        fileChooser.setTitle("Criar um ficheiro");
        fileIn = fileChooser.showSaveDialog(null).getAbsolutePath();
        File file = new File(fileIn);
        String texto = campo.getText();
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(fileIn);
            writer.write(texto);
            writer.close();
            Alert alerta = new Alert(AlertType.CONFIRMATION, "Criado com sucesso");
            alerta.showAndWait();
        } catch (IOException e) {
            Alert alerta = new Alert(AlertType.ERROR, "Ocorreu um erro");
            alerta.showAndWait();
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        campo.setText("");
        tabela.getItems().clear();
    }

    @FXML
    void openFloder(MouseEvent event) {
        fileChooser.setTitle("Abrir um ficheiro");
        try {
            fileIn = fileChooser.showOpenDialog(null).getAbsolutePath();
            FileReader reader = new FileReader(fileIn);
            int data = reader.read();
            StringBuilder texto = new StringBuilder();
            while (data != -1) {
                texto.append((char) data);
                data = reader.read();
            }
            reader.close();
            campo.setText(texto.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void save(MouseEvent event) {
        if (fileIn.equals("")) {
            fileChooser.setTitle("Guardar um ficheiro");
            fileIn = fileChooser.showSaveDialog(null).getAbsolutePath();
        }
        String texto = campo.getText();
        try {
            FileWriter writer = new FileWriter(fileIn);
            writer.write(texto);
            writer.close();
            Alert alerta = new Alert(AlertType.CONFIRMATION, "Salvo com sucesso");
            alerta.showAndWait();
        } catch (IOException e) {
            Alert alerta = new Alert(AlertType.ERROR, "Ocorreu um erro");
            alerta.showAndWait();
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void runn(MouseEvent event) {

        int tempo1 = (int) System.currentTimeMillis();
        String texto = campo.getText();

        ObservableList<tabelaToken> tokensList = FXCollections.observableArrayList();
        AnalisadorToken a = new AnalisadorToken();
        List<tabelaToken> h = new ArrayList<>();
        tabelaToken f;
        List<String> b = a.QuebrarLinhas(campo.getText());
        int numeroLinha = 0;
        for (String c : b) {
            List<String> palavra = a.QuebrarPalavras(c);
            numeroLinha++;
            for (String tokens : palavra) {

                f = new tabelaToken(numeroLinha, new AnalisadorToken().verificarTipoPalavra(tokens), tokens);
                tokensList.add(f);
                System.out.println(f.toString());
            }
        }

        linha.setCellValueFactory(new PropertyValueFactory<tabelaToken, Integer>("numero_linha"));
        token.setCellValueFactory(new PropertyValueFactory<tabelaToken, String>("token"));
        lexema.setCellValueFactory(new PropertyValueFactory<tabelaToken, String>("lexema"));

        tabela.setItems(tokensList);
        int tempo2 = (int) System.currentTimeMillis();

        time.setText((tempo2 - tempo1) + " " + "milisegundos");
    }

    public void barraLinha(String txt) {
        String[] lines = txt.split("\n");
        for (int i = 0; i < txt.length(); i++) {

        }
    }

    @FXML
    void trocarImagens(MouseEvent event) {
        ImageView imagem = ((ImageView) event.getSource());
        imagem.setImage(new Image("/Image/" + imagem.getId() + "1.png"));
    }

    @FXML
    void voltarImagens(MouseEvent event) {
        ImageView imagem = ((ImageView) event.getSource());
        imagem.setImage(new Image("/Image/" + imagem.getId() + ".png"));
    }

    public String[] stringEmArray(String string) {
        for (int i = 0; i < string.length(); i++) {
        }
        return null;
    }

    @FXML
    void configurar(MouseEvent event) {
        JOptionPane.showMessageDialog(null,
                "UEM\nCriado por:\nEdilton Idrice Magudo\nIvania Perce Chirindza\nKelton Gerlado Manjate\n\nLexicalAnalyzer V.1.0",
                "Developers",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public void colocarLinhas(String txt) {
    linhas.getChildren().clear();
    String[] linhasDividas = txt.split("\n");

    for (int i = 0; i < linhasDividas.length; i++) {
        Label lineNumberLabel = new Label(String.valueOf(i + 1));
        linhas.getChildren().add(lineNumberLabel);
    }
}

}
