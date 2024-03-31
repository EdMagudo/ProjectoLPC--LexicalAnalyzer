package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import DAO.AnalisadorToken;
import DAO.tabelaToken;
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

    ObservableList<tabelaToken> tokensList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabela.getItems().clear();
    }

    String paragrafos;
    String[] palavras;
    List<tabelaToken> lisTabelaTokens;
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
        
        int tempo1 = (int) System.currentTimeMillis();
        String texto = campo.getText();

        ObservableList<tabelaToken> tokensList = FXCollections.observableArrayList();
        AnalisadorToken a = new AnalisadorToken();
        List<tabelaToken> h = new ArrayList<>();
        tabelaToken f;
        List<String> b = a.dividirTextoEmLinhas(campo.getText());
        int numeroLinha = 0;
        for (String c : b) {
            List<String> palavra = a.analisarLexicamente(c);
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
        int tempo2 =(int) System.currentTimeMillis();
        
        
        time.setText((tempo2-tempo1) + " " + "mls");
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
                "Criado por:\nEdilton Idrice Magudo\nIvania Perce Chirindza\nKelton Gerlado Manjate\n\nLexicalAnalyzer V.1.0",
                "Developers",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
