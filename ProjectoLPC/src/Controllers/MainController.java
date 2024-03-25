package Controllers;

import DAO.AnalisadorToken;
import DAO.Tokens;
import DAO.tabelaToken;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainController implements Initializable {

    @FXML
    private TextArea campo;

    @FXML
    private TableColumn<tabelaToken, String> tipoToken;

    @FXML
    private TableColumn<tabelaToken, String> lexema;

    @FXML
    private TableColumn<tabelaToken, String> linha;

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
    private Button touch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void runn(ActionEvent event) {
       
        ArrayList<tabelaToken> a = new AnalisadorToken().dividirTextoEmLinhas(campo.getText());
        new MainController().listar(a);

        int tempo = (int) System.currentTimeMillis();
        time.setText(String.valueOf(tempo));

    }

    public void listar(ArrayList<tabelaToken> tokens) {
        
        for(tabelaToken f : tokens)
            System.out.print(f.toString());
        /*ObservableList<tabelaToken> observableTokens = FXCollections.observableArrayList(tokens);
        linha.setCellValueFactory(new PropertyValueFactory<>("numero_linha"));
        tipoToken.setCellValueFactory(new PropertyValueFactory<>("tipoToken"));
        lexema.setCellValueFactory(new PropertyValueFactory<>("lexema"));

        tabela.setItems(observableTokens);*/

    }

}
