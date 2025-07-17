public class Abp<T extends Comparable<T>> implements Arvore<T> {
    private NodoTriplo<T> raiz;

    public Abp() {
        raiz = null;
    }

    @Override
    public NodoTriplo<T> getRaiz() {
        return raiz;
    }

    @Override
    public void limpar() {
        raiz = null;
    }

    @Override
    public void inserir(T dado) {
        NodoTriplo<T> novoNo = new NodoTriplo<>();
        novoNo.setDado(dado);
        if (raiz == null) {
            raiz = novoNo;
        } else {
            NodoTriplo<T> aux = raiz;
            while (true) {
                int comparacao = dado.compareTo(aux.getDado());
                if (comparacao == 0) {
                    if (aux.getEsquerda() == null) {
                        aux.setEsquerda(novoNo);
                        novoNo.setGenitor(aux);
                        break;
                    }
                    aux = aux.getEsquerda();
                } else {
                    if (aux.getDireita() == null) {
                        aux.setDireita(novoNo);
                        novoNo.setGenitor(aux);
                        break;
                    }
                    aux = aux.getDireita();
                }
            }
        }
    }

    @Override
    public T apagar(T dado) {
        NodoTriplo<T> aux = buscar(dado);
        if (aux == null)
            return null;
        if (aux.getEsquerda() == null && aux.getDireita() == null)
            apagarNoFolha(aux);
        else if (aux.getEsquerda() == null || aux.getDireita() == null)
            apagarComUmFilho(aux);
        else
            apagarComDoisFIlhos(aux);
        return dado;
    }

    private void apagarComDoisFIlhos(NodoTriplo<T> aux) {
    }

    private void apagarComUmFilho(NodoTriplo<T> aux) {
    }

    private void apagarNoFolha(NodoTriplo<T> nodo) {
        NodoTriplo<T> pai = nodo.getGenitor();
        if (pai == null) {
            raiz = null;
        } else {
            if (nodo.equals(pai.getEsquerda())) {
                pai.setEsquerda(null);
            } else {
                pai.setDireita(null);
            }
        }
    }

    private NodoTriplo<T> buscar(T dado) {
        NodoTriplo<T> atual = raiz;
        while (atual != null) {
            int cmp = dado.compareTo(atual.getDado());
            if (cmp == 0) return atual;
            else if (cmp < 0) atual = atual.getEsquerda();
            else atual = atual.getDireita();
        }
        return null;
    }

    @Override
    public boolean existe(T dado) {
        return buscar(dado) != null;
    }

    @Override
    public String imprimirPreOrdem() {
        return "";
    }

    @Override
    public String imprimirEmOrdem() {
        return "";
    }

    @Override
    public String imprimirPosOrdem() {
        return "";
    }
}