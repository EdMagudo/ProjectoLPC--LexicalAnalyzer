package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainController implements Initializable {

    @FXML
    private TextArea campo;

    @FXML
    private TableColumn<?, ?> erro;

    @FXML
    private TableColumn<?, ?> lexema;

    @FXML
    private TableColumn<?, ?> linha;

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
    private TableView<?> tabela;

    @FXML
    private Text time;

    @FXML
    private TableColumn<?, ?> token;

    @FXML
    private Button touch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    int nrParagrafos = 0;
    String paragrafos;
    String palavras;

    @FXML
    void escrevendoCodigo(KeyEvent event) {
        ObservableList<CharSequence> list = campo.getParagraphs();
        paragrafos = campo.getParagraphs().toString();
        nrParagrafos = list.size();
        System.out.println(paragrafos);
    }

    @FXML
    void create(MouseEvent event) {

    }

    @FXML
    void openFloder(MouseEvent event) {

    }

    @FXML
    void runn(MouseEvent event) {

    }

    @FXML
    void save(MouseEvent event) {

    }

    @FXML
    void trocarImagens(MouseEvent event) {

    }

    @FXML
    void voltarImagens(MouseEvent event) {

    }

    public String jump(String paragrafo) {
        return null;
    }

}
