/**
 * Implementação de uma lista dinâmica duplamente encadeada genérica.
 * Esta classe implementa uma lista que pode armazenar elementos de qualquer tipo,
 * utilizando uma estrutura de nós duplamente encadeados.
 *
 * @param <T> o tipo dos elementos armazenados na lista
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.1
 * @since 2025-06-04
 */
public class ListaDinamicaGenerica<T> implements Listavel<T> {

    private int quantidade;
    private int tamanho;
    private NoDuplo<T> ponteiroInicio;
    private NoDuplo<T> ponteiroFim;

    public ListaDinamicaGenerica() {
        this(10);
    }

    public ListaDinamicaGenerica(int tamanho) {
        ponteiroInicio = null;
        ponteiroFim = null;
        quantidade = 0;
        this.tamanho = tamanho;
    }

    @Override
    public void anexar(T dado) {
        if (estaCheia()) {
            throw new OverflowException("Lista Cheia!");
        }
        NoDuplo<T> noTemporario = new NoDuplo<>();
        noTemporario.setDado(dado);
        if (!estaVazia()) {
            ponteiroFim.setProximo(noTemporario);
            noTemporario.setAnterior(ponteiroFim);
        } else {
            ponteiroInicio = noTemporario;
        }
        ponteiroFim = noTemporario;
        quantidade++;
    }

    @Override
    public void inserir(int posicao, T dado) {
        if (estaCheia()) {
            throw new OverflowException("Lista Cheia!");
        }
        if (!(posicao >= 0 && posicao <= quantidade)) {
            throw new IndexOutOfBoundsException("Indice Invalido!");
        }
        NoDuplo<T> noTemporario = new NoDuplo<>();
        noTemporario.setDado(dado);

        NoDuplo<T> ponteiroAnterior = null;
        NoDuplo<T> ponteiroProximo = ponteiroInicio;

        for (int i = 0; i < posicao; i++) {
            ponteiroAnterior = ponteiroProximo;
            ponteiroProximo = ponteiroProximo.getProximo();
        }

        if (ponteiroAnterior != null) {
            ponteiroAnterior.setProximo(noTemporario);
        } else {
            ponteiroInicio = noTemporario;
        }

        if (ponteiroProximo != null) {
            ponteiroProximo.setAnterior(noTemporario);
        } else {
            ponteiroFim = noTemporario;
        }

        noTemporario.setAnterior(ponteiroAnterior);
        noTemporario.setProximo(ponteiroProximo);

        quantidade++;
    }

    @Override
    public T selecionar(int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista Vazia!");
        }
        if (!(posicao >= 0 && posicao < quantidade)) {
            throw new IndexOutOfBoundsException("Indice Invalido!");
        }

        NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        return ponteiroAuxiliar.getDado();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] selecionarTodos() {
        if (estaVazia()) {
            throw new UnderflowException("Lista Vazia!");
        }
        T[] dadosTemporario = (T[]) new Object[quantidade];
        NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < quantidade; i++) {
            dadosTemporario[i] = ponteiroAuxiliar.getDado();
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        return dadosTemporario;
    }

    @Override
    public void atualizar(int posicao, T novoDado) {
        if (estaVazia()) {
            throw new UnderflowException("Lista Vazia!");
        }
        if (!(posicao >= 0 && posicao < quantidade)) {
            throw new IndexOutOfBoundsException("Indice Invalido!");
        }

        NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        ponteiroAuxiliar.setDado(novoDado);
    }

    @Override
    public T apagar(int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista Vazia!");
        }
        if (!(posicao >= 0 && posicao < quantidade)) {
            throw new IndexOutOfBoundsException("Indice Invalido!");
        }

        NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }

        NoDuplo<T> ponteiroAnterior = ponteiroAuxiliar.getAnterior();
        NoDuplo<T> ponteiroProximo = ponteiroAuxiliar.getProximo();

        if (ponteiroAnterior != null) {
            ponteiroAnterior.setProximo(ponteiroProximo);
        } else {
            ponteiroInicio = ponteiroInicio.getProximo();
        }
        if (ponteiroProximo != null) {
            ponteiroProximo.setAnterior(ponteiroAnterior);
        } else {
            ponteiroFim = ponteiroFim.getAnterior();
        }

        quantidade--;
        return ponteiroAuxiliar.getDado();
    }

    @Override
    public boolean estaCheia() {
        return (quantidade == tamanho);
    }

    @Override
    public boolean estaVazia() {
        return (quantidade == 0);
    }

    @Override
    public String imprimir() {
        String resultado = "[";
        NoDuplo<T> ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < quantidade; i++) {
            resultado += ponteiroAuxiliar.getDado();
            if (i != quantidade - 1) {
                resultado += ",";
            }
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        return resultado + "]";
    }
}
