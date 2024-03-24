package DAO;

public class PalavrasReservadas {

    public static final String[] palavrasReservadas = {
        "div",
        "or",
        "and",
        "not",
        "if",
        "then",
        "else",
        "of",
        "while",
        "do",
        "begin",
        "end",
        "read",
        "write",
        "var",
        "array",
        "function",
        "procedure",
        "program",
        "true",
        "false",
        "char",
        "integer",
        "boolean",
        "writeln",
        "readln"
    };

    public static final String[] operadoresLogicos = {"or", "and", "not"};
    public static final String[] palavrasChaveControleFluxo = {"if", "then", "else", "of", "while", "do", "begin", "end"};
    public static final String[] palavrasChaveIO = {"read", "write", "writeln", "readln"};
    public static final String[] palavrasChaveDeclaracao = {"var", "array", "function", "procedure", "program"};
    public static final String[] tiposDados = {"char", "integer", "boolean"};
    public static final String[] valoresBooleanos = {"true", "false"};

    public static final char[] letras = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static final String[] delimitadores = new String[]{"(", ")", "[", "]", ";", ".", ",", ":"};

    public static final String[] digitos = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static final String[] operadoresAritmeticos = new String[]{"+", "-", "*", "/", "div"};

    public static final String[] sinalAtribuicao = new String[]{":="};

    public static final String[] operadoreRelacional = {"=", "<=", "<", ">", ">=", "<>"};

    public static final String[] caracteresEspeciais = new String[] {"\'", "\""};


}
