package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainController implements Initializable {

    @FXML
    private TextArea campo;

    @FXML
    private TableColumn<String, String> erro;

    @FXML
    private TableColumn<String, String> lexema;

    @FXML
    private TableColumn<String, String> linha;

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
    private TableView<String> tabela;

    @FXML
    private Text time;

    @FXML
    private TableColumn<String, String> token;

    @FXML
    private Button touch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    int nrParagrafos = 0;
    String paragrafos;
    String[] palavras;
    int[] linhas_por_palavra;

    @FXML
    void escrevendoCodigo(KeyEvent event) {
        ObservableList<CharSequence> list = campo.getParagraphs();
        paragrafos = campo.getText();
        palavras = dividirEmPalavras(paragrafos);
        nrParagrafos = list.size();
        linhas_por_palavra = new int[nrParagrafos];
        numerar(paragrafos.toString());
        /*
         * palavras = campo.getParagraphs().toArray();
         * cont_palavra(paragrafos);
         */
        System.out.println("--------------------------------------------------------------------");
        System.out.println(paragrafos);
        System.out.println(palavras.length);
        System.out.println(linhas_por_palavra[0]);
        System.out.println("--------------------------------------------------------------------");
    }

    @FXML
    void create(MouseEvent event) {
        System.out.println("1");
    }

    @FXML
    void openFloder(MouseEvent event) {
        System.out.println("2");

    }

    @FXML
    void save(MouseEvent event) {
        System.out.println("3");
    }

    @FXML
    void runn(MouseEvent event) {
        tabela.setItems(null);

    }

    @FXML
    void configurar(MouseEvent event) {
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

    public void numerar(String string) {
        int nrPorParagrafos = 1;
        int y = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ',') {
                System.out.print(nrPorParagrafos);
                linhas_por_palavra[y] = nrPorParagrafos;
                nrPorParagrafos++;
            } else if (string.charAt(i) == ' ') {
                y++;
            }
        }
    }

    public String[] stringEmArray(String string) {
        for (int i = 0; i < string.length(); i++) {
        }
        return null;
    }

    public String[] dividirEmPalavras(String texto) {
        List<String> palavras = new ArrayList<>();
        StringBuilder palavra = new StringBuilder();

        for (char caracter : texto.toCharArray()) {
            if (Character.isWhitespace(caracter)) {
                if (palavra.length() > 0) {
                    palavras.add(palavra.toString());
                    palavra.setLength(0); 
                }
            } else {
                palavra.append(caracter);
            }
        }

        if (palavra.length() > 0) {
            palavras.add(palavra.toString());
        }

        return palavras.toArray(new String[palavras.size()]);
    }

}
