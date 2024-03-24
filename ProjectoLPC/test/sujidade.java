
import DAO.PalavrasReservadas;
import DAO.Tokens;
import java.util.ArrayList;
import java.util.List;

public class sujidade {

    public static void main(String[] args) {
        String linha = "x := 10 + y * ( 20 - z ) ;";
        System.out.println(divid(linha));
    }

    public static String divid(String texto) {

        List<String> partes = new ArrayList<>();
        int i = 0;
        do {

            if (texto.charAt(i) == ' ') {
                i++;
            } else {

                int fimPalavra = i + 1;
                while (fimPalavra < texto.length() && texto.charAt(fimPalavra) != ' ') {
                    fimPalavra++;
                }
                partes.add(texto.substring(i, fimPalavra));
                i = fimPalavra;
            }
        } while (i < texto.length());

        StringBuilder resultado = new StringBuilder();
        for (String palavra : partes) {
            String tipoPalavra = verificarTipoPalavra(palavra);
            resultado.append("Token: ").append(palavra).append(" , Tipo: ").append(tipoPalavra).append(" \n");
        }
        return resultado.toString();
    }

    public static void analisarPalavras(String linha, int numeroLinha) {
        String[] palavras = linha.split("\\s+"); 

        for (String palavra : palavras) {
            String tipoPalavra = verificarTipoPalavra(palavra);
            System.out.println("Token: " + palavra + ", Tipo: " + tipoPalavra + ", Linha: " + numeroLinha);
        }
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
                            String a = Character.toString(token.charAt(i));
                            // Nao consigo usar esse metodo contains
                            //basicamente ele deve ver se cada caracter do tken faz parte do alfatebo, nesse casso letras
                            if(a.contains(PalavrasReservadas.letras)){
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
