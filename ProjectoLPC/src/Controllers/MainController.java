package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import DAO.AnalisadorToken;
import DAO.TabelaToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainController implements Initializable {

    @FXML
    private TextArea campo;

    @FXML
    private TableColumn<TabelaToken, String> erro;

    @FXML
    private TableColumn<TabelaToken, String> lexema;

    @FXML
    private TableColumn<TabelaToken, Integer> linha;

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
    private TableView<TabelaToken> tabela;

    @FXML
    private Text time;

    @FXML
    private TableColumn<TabelaToken, String> token;

    @FXML
    private Button touch;

    ObservableList<TabelaToken> tokensList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        linha.setCellValueFactory(new PropertyValueFactory<TabelaToken, Integer>("numero_linha"));
        token.setCellValueFactory(new PropertyValueFactory<TabelaToken, String>("token"));
        lexema.setCellValueFactory(new PropertyValueFactory<TabelaToken, String>("lexema"));
        erro.setCellValueFactory(new PropertyValueFactory<TabelaToken, String>("erro"));
    }

    String paragrafos;
    String[] palavras;
    List<TabelaToken> lisTabelaTokens;
    @SuppressWarnings("rawtypes")
    List linhas_por_palavra;

    @FXML
    void create(MouseEvent event) {
        JFileChooser fileChooser = new JFileChooser();

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
        tabela.getItems().clear();
        paragrafos = campo.getText();
        palavras = transfomarPalavras(paragrafos);

        for (int i = 0; i < palavras.length; i++) {
            String tokene = null;
            String erro = "Sem erros";

            tokene = (AnalisadorToken.verificarTipoPalavra(palavras[i]));
            if (tokene == "Erro") {
                erro = tokene;
                tokene = null;
            }
            TabelaToken token = new TabelaToken(
                    (int) linhas_por_palavra.get(i),
                    tokene,
                    palavras[i],
                    erro);

            tokensList.add(token);
        }
        tabela.setItems(tokensList);
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

    public String[] stringEmArray(String string) {
        for (int i = 0; i < string.length(); i++) {
        }
        return null;
    }

    public String[] transfomarPalavras(String texto) {
        List<String> palavras = new ArrayList<>();
        StringBuilder palavra = new StringBuilder();
        List<Integer> numLinha = new ArrayList<>();
        int linha = 1;
        char c = '\n';
        for (char caracter : texto.toCharArray()) {
            if (Character.isWhitespace(caracter)) {
                if (c == caracter) {
                    linha++;
                }
                if (palavra.length() > 0) {
                    palavras.add(palavra.toString());
                    palavra.setLength(0);
                    numLinha.add(linha);
                }
            } else {
                palavra.append(caracter);
            }
        }

        if (palavra.length() > 0) {
            palavras.add(palavra.toString());
            numLinha.add(linha);
        }

        linhas_por_palavra = numLinha;
        return palavras.toArray(new String[palavras.size()]);
    }

}
