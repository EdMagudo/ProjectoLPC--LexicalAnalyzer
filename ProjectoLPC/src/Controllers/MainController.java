package Controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import DAO.AnalisadorToken;
import DAO.TabelaToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

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
    FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        linha.setCellValueFactory(new PropertyValueFactory<TabelaToken, Integer>("numero_linha"));
        token.setCellValueFactory(new PropertyValueFactory<TabelaToken, String>("token"));
        lexema.setCellValueFactory(new PropertyValueFactory<TabelaToken, String>("lexema"));
        erro.setCellValueFactory(new PropertyValueFactory<TabelaToken, String>("erro"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Ficheiro de Texto", "*.txt"),
                new FileChooser.ExtensionFilter("Ficheiro de Pascal", "*.pas"));
    }

    String paragrafos;
    String[] palavras;
    List<TabelaToken> lisTabelaTokens;
    @SuppressWarnings("rawtypes")
    List linhas_por_palavra;

    String fileIn = new String();

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
    }

    @FXML
    void openFloder(MouseEvent event) throws IOException {
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
        long inicio = System.currentTimeMillis();
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
        long fim = System.currentTimeMillis();
        time.setText((fim - inicio) + (" mls"));
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

    List<Integer> nao_corta = new ArrayList<>();

    public String[] transfomarPalavras(String texto) {
        nao_corta = new ArrayList<>();
        List<String> palavras = new ArrayList<>();
        StringBuilder palavra = new StringBuilder();
        List<Integer> numLinha = new ArrayList<>();

        int linha = 1;
        char c = '\n';
        boolean env = false;
        for (char caracter : texto.toCharArray()) {
            if (AnalisadorToken.verificarTipoPalavra(caracter + "") == "Operador Aritmetico" ||
                    AnalisadorToken.verificarTipoPalavra(caracter + "") == "Operador Relacional" ||
                    AnalisadorToken.verificarTipoPalavra(caracter + "") == "Delimitador" ||
                    AnalisadorToken.verificarTipoPalavra(caracter + "") == "Operador Logico") {
                if (env)
                    nao_corta.add(0);
                else
                    nao_corta.add(1);
                env = false;
                palavras.add(caracter + "");
                numLinha.add(linha);
            } else if (Character.isWhitespace(caracter)) {
                if (palavra.length() > 0) {
                    palavras.add(palavra.toString());
                    palavra.setLength(0);
                    numLinha.add(linha);
                }
                env = true;
                if (c == caracter) {
                    linha++;
                }

            } else {
                palavra.append(caracter);
            }
        }

        if (palavra.length() > 0)

        {
            palavras.add(palavra.toString());
            numLinha.add(linha);
        }
        linhas_por_palavra = numLinha;
        concatenar(palavras);
        return palavras.toArray(new String[palavras.size()]);
    }

    public void concatenar(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.size() != i + 1)
                if ((nao_corta.get(i) != 1 && nao_corta.get(i + 1) != 0)
                        && linhas_por_palavra.get(i) == linhas_por_palavra.get(i + 1) && ((((":".equals(list.get(i))
                                ||
                                "<".equals(list.get(i))
                                ||
                                ">".equals(list.get(i))) &&
                                "=".equals(list.get(i + 1))) ||
                                ("<".equals(list.get(i)) && ">".equals(list.get(i + 1)))))) {
                    nao_corta.remove(i + 1);
                    list.set(i, list.get(i) + list.remove(i + 1));
                    linhas_por_palavra.remove(i);
                }
        }
        System.out.println(nao_corta);
    }

}