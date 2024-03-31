package DAO;

import java.util.ArrayList;
import java.util.List;

public class AnalisadorToken {

    public List<String> dividirTextoEmLinhas(String textoTextArea) {
        List<String> linhas = new ArrayList<>();
        if (textoTextArea != null && !textoTextArea.isEmpty()) {
            String[] linhasTexto = textoTextArea.split("\\n");

            for (String linha : linhasTexto) {
                linhas.add(linha);

            }
        }
        return linhas;
    }

    //Metodo para dividir e validar caracteres 
    public static List<String> analisarLexicamente(String linha) {
        List<String> partes = new ArrayList<>();
        int i = 0;

        while (i < linha.length()) {
            // Ignora espaços em branco
            while (i < linha.length() && Character.isWhitespace(linha.charAt(i))) {
                i++;
            }

            if (i == linha.length()) {
                break;
            }

            // Símbolos especiais
            char c = linha.charAt(i);
            if (c == '(' || c == ')' || c == '[' || c == ']') {
                partes.add(Character.toString(c));
                i++;
                continue;
            }

            // Operadores
            if (c == '+' || c == '-' || c == '*' || c == ';') {
                partes.add(Character.toString(c));
                i++;
                continue;
            }

            if (c == '<') {
                if (i + 1 < linha.length() && linha.charAt(i + 1) == '>') {
                    partes.add("<>");
                    i += 2;
                    continue;
                } else {
                    partes.add(Character.toString(c));
                }
            } else {

                // Atribuição
                if (c == ':') {
                    if (i + 1 < linha.length() && linha.charAt(i + 1) == '=') {
                        partes.add(":=");
                        i += 2;
                        continue;
                    } else {
                        partes.add(Character.toString(c));
                        i++;
                        continue;
                    }
                } else // Identificadores e palavras-chave
                if (Character.isLetter(c)) {
                    int fimPalavra = i + 1;
                    while (fimPalavra < linha.length() && (Character.isLetterOrDigit(linha.charAt(fimPalavra)) || linha.charAt(fimPalavra) == '_')) {
                        fimPalavra++;
                    }
                    String palavra = linha.substring(i, fimPalavra);
                    partes.add(palavra);
                    i = fimPalavra;
                    continue;
                } else // Números
                if (Character.isDigit(c)) {
                    int fimNumero = i + 1;
                    while (fimNumero < linha.length() && Character.isDigit(linha.charAt(fimNumero))) {
                        fimNumero++;
                    }
                    String numero = linha.substring(i, fimNumero);
                    partes.add(numero);
                    i = fimNumero;
                    continue;
                } else // Delimitadores
                if (c == '/' || c == ',') {
                    partes.add(Character.toString(c));
                    i++;
                    continue;
                } else //erro para string que comeca com _
                //((c == '_')|| (c == '@') || c == '!') 
                if ((c == '_') || (c == '@') || (c == '!') || (c == '?')) {
                    int fimPalavra = i + 1;
                    while (fimPalavra < linha.length() && (Character.isLetterOrDigit(linha.charAt(fimPalavra)) || linha.charAt(fimPalavra) == '_')) {
                        fimPalavra++;
                    }
                    String palavra = linha.substring(i, fimPalavra);
                    partes.add(palavra);
                    i = fimPalavra;
                    continue;
                } else {
                    partes.add(Character.toString(c));
                    i++;
                }
            }
        }

        return partes;
    }

    public String verificarTipoPalavra(String token) {
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

            // Token de erro
            case Tokens.token_erro:
                return "Erro";

            // Tokens de declaração
            case Tokens.token_var:
            case Tokens.token_array:
            case Tokens.token_function:
            case Tokens.token_procedure:
            case Tokens.token_program:
                return "Declaracao";

            case Tokens.token_SinalAtribuicao:
                return "Sinal Atribuicao";

            default:
                if (isNumero(token)) {
                    return "Digito";
                } else {
                    if (isSinalAtribuicao(token)) {
                        return "Sinal de atribuicacao";
                    } else {
                        if ((Identificador(token))) {
                            return "Identificador";
                        }

                        return "Erro";
                    }
                }
        }
    }

    public static boolean Identificador(String palavra) {
        if (palavra.isEmpty() || (!Character.isLetter(palavra.charAt(0)) && (palavra.charAt(0) != '_' || palavra.charAt(0) != '@'))) {
            return false;
        }

        return true;
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
