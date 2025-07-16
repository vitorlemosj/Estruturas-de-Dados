package Arvore.AVLgenerica.src.main.java;

public interface Arvore<T> {

    NoTriplo<T> getRaiz();
    void inserir(T dado);
    T apagar(T dado);
    boolean existe(T dado);
    void limpar();
    String imprimirPreOrdem();
    String imprimirEmOrdem();
    String imprimirPosOrdem();
}