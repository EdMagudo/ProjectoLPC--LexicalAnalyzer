
import DAO.Tokens;
import DAO.tabelaToken;
import java.util.ArrayList;

public class sujidade {

    public static void main(String[] args) {
        String linha = "x := 10 + y * (20 - z ) ;";
        ArrayList<tabelaToken> b = new sujidade().dividirTextoEmLinhas(linha);
        
        for(tabelaToken c:b){
            System.out.println(c.toString());
        }
    }

    public ArrayList<tabelaToken> dividirTextoEmLinhas(String textoTextArea) {
    ArrayList<tabelaToken> b = new ArrayList<>();
    if (textoTextArea != null && !textoTextArea.isEmpty()) {
        String[] linhasTexto = textoTextArea.split("\\n");
        int numeroLinha = 1;
        for (String linha : linhasTexto) {
            b.addAll(dividirCodigo(linha, numeroLinha));
            numeroLinha++;
        }
    }
    return b;
}

public ArrayList<tabelaToken> dividirCodigo(String texto, int numeroLinha) {
    ArrayList<tabelaToken> b = new ArrayList<>();
    String[] partes = texto.split("\\s+");
    for (String palavra : partes) {
        String tipoPalavra = verificarTipoPalavra(palavra);
        tabelaToken linha = new tabelaToken(numeroLinha, palavra, tipoPalavra, palavra);
        b.add(linha);
    }
    return b;
}

    public static String verificarTipoPalavra(String token) {
        switch (token) {

            // Operadores aritméticos
            case Tokens.token_soma:
            case Tokens.token_subtraccao:
            case Tokens.token_multiplicacao:
            case Tokens.token_divisao:
                return "Operador Aritmetico";

            // Operadores relacionais
            case Tokens.token_igual:
            case Tokens.token_diferente:
            case Tokens.token_menor:
            case Tokens.token_maior:
            case Tokens.token_maior_igual:
            case Tokens.token_menor_igual:
                return "Operador Relacional";

            // Delimitadores
            case Tokens.token_abrir_parenteses:
            case Tokens.token_fechar_parenteses:
            case Tokens.token_abrir_conchetes:
            case Tokens.token_fechar_conchetes:
            case Tokens.token_ponto_virgula:
            case Tokens.token_ponto:
            case Tokens.token_virgula:
            case Tokens.token_dois_pontos:
                return "Delimitador";

            // Tokens de operadores lógicos
            case Tokens.token_or:
            case Tokens.token_and:
            case Tokens.token_not:
                return "Operador Logico";

            // Tokens de controle de fluxo
            case Tokens.token_if:
            case Tokens.token_then:
            case Tokens.token_else:
            case Tokens.token_while:
            case Tokens.token_do:
            case Tokens.token_begin:
            case Tokens.token_end:
                return "Controle de Fluxo";

            // Tokens de entrada e saida
            case Tokens.token_read:
            case Tokens.token_write:
            case Tokens.token_writeln:
            case Tokens.token_readln:
                return "Entrada ou saida";

            // Tokens de tipos de dados
            case Tokens.token_true:
            case Tokens.token_false:
                return "Valor boleano";

            case Tokens.token_char:
            case Tokens.token_integer:
            case Tokens.token_boolean:
                return "Tipo de Dados";

            //Token de erro   
            case Tokens.token_erro:
                return "Erro";

            // Tokens de declaração
            case Tokens.token_var:
            case Tokens.token_array:
            case Tokens.token_function:
            case Tokens.token_procedure:
            case Tokens.token_program:
                return "Declaracao";

            default:
                if (isNumero(token)) {
                    return "Digito";
                } else {
                    if (isSinalAtribuicao(token)) {
                        return "Sinal de atribuicacao";
                    } else {
                        for (int i = 0; i < token.length(); i++) {
                            char c = token.charAt(i);

                            if (Character.isLetter(c)) {
                                return "Identificador";
                            }
                        }

                        return "Erro";
                    }
                }
        }
    }

    public static boolean isNumero(String palavra) {
        try {
            Integer.parseInt(palavra);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isSinalAtribuicao(String palavra) {
        return palavra.equals(":=");
    }

}
/*


*/
