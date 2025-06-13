package Arvores;

import java.util.NoSuchElementException;


public abstract class ArvoreBinariaHeapMaximo<T> implements Amontoavel<T> {

    private T[] dados;


    private int ponteiroFim;


    public ArvoreBinariaHeapMaximo() {
        this(10);
    }


    public ArvoreBinariaHeapMaximo(int tamanho) {
        dados = (T[]) new Object[tamanho];
        ponteiroFim = -1;
    }

    @Override
    public T obterRaiz() {
        if (estaVazia()) {
            throw new NoSuchElementException("Elemento não encontrado!");
        }
        return dados[0];
    }

    @Override
    public void inserir(T dado) {
        if (estaCheia()) {
            throw new OverflowException("Heap Cheia!");
        }
        ponteiroFim++;
        dados[ponteiroFim] = dado;
        ajustarAcima(ponteiroFim);
    }




    private void ajustarAcima(int indice) {
        ajustarAcimaIterativo(indice);
        //ajustarAcimaRecursivo(indice);
    }


    private void ajustarAcimaIterativo(int indice) {
        int pai = indicePai(indice);
        while (indice > 0 && (Long) dados[indice] > (Long) dados[pai]) {
            trocar(indice, pai);
            indice = pai;
            pai = indicePai(indice);
        }
    }


    private void ajustarAcimaRecursivo(int indice) {
        int pai = indicePai(indice);
        if (indice > 0 && (Long) dados[indice] > (Long) dados[pai]) {
            trocar(indice, pai);
            ajustarAcimaRecursivo(pai);
        }
    }

    @Override
    public T extrair() {
        if (estaVazia()) {
            throw new UnderflowException("Heap Vazia!");
        }
        T dadoRaiz = dados[0];
        dados[0] = dados[ponteiroFim];
        ponteiroFim--;
        ajustarAbaixo(0);

        return dadoRaiz;
    }


    private void ajustarAbaixo(int pai) {
        // ajustarAbaixoIterativo(pai);
        ajustarAbaixoRecursivo(pai);
    }


    private void ajustarAbaixoIterativo(int pai) {
        boolean ajustado = false;

        while (!ajustado) {
            int filhoEsquerdo = indiceFilhoEsquerdo(pai);
            int filhoDireito = indiceFilhoDireito(pai);
            int maior = pai;

            //está dentro dos indices válidos do array, no intervalo [0, ponteiroFim]
            if (filhoEsquerdo <= ponteiroFim) {
                if ((Long)dados[filhoEsquerdo] > (Long) dados[maior]) {
                    maior = filhoEsquerdo;
                }
            }

            //está dentro dos indices válidos do array, no intervalo [0, ponteiroFim]
            if (filhoDireito <= ponteiroFim) {
                if ((Long)dados[filhoDireito] > (Long)dados[maior]) {
                    maior = filhoDireito;
                }
            }

            // Se o maior não for o pai, troca e continua descendo
            if (maior != pai) {
                trocar(pai, maior);
                pai = maior; // desce para a posição onde o maior estava
            } else {
                ajustado = true; // heap está ajustado, sai do loop
            }
        }
    }


    private void ajustarAbaixoRecursivo(int pai) {
        int filhoEsquerdo = indiceFilhoEsquerdo(pai);
        int filhoDireito = indiceFilhoDireito(pai);
        int maior = pai;

        //está dentro dos indices válidos do array, no intervalo [0, ponteiroFim]
        if (filhoEsquerdo <= ponteiroFim) {
            if ((Long)dados[filhoEsquerdo] > (Long)dados[maior]) {
                maior = filhoEsquerdo;
            }
        }

        //está dentro dos indices válidos do array, no intervalo [0, ponteiroFim]
        if (filhoDireito <= ponteiroFim) {
            if ((Long)dados[filhoDireito] > (Long)dados[maior]) {
                maior = filhoDireito;
            }
        }

        if (maior != pai) {
            trocar(pai, maior);
            ajustarAbaixoRecursivo(maior);
        }
    }


    private void trocar(int i, int j) {
        T temp = dados[i];
        dados[i] = dados[j];
        dados[j] = temp;
    }


    private int indicePai(int filho) {
        return (filho - 1) / 2;
    }

    private int indiceFilhoEsquerdo(int pai) {
        return 2 * pai + 1;
    }


    private int indiceFilhoDireito(int pai) {
        return (2 * pai + 1) + 1;
    }


    @Override
    public boolean estaVazia() {
        return (ponteiroFim == -1);
    }


    @Override
    public boolean estaCheia() {
        return (ponteiroFim == dados.length-1);
    }


    @Override
    public String imprimir() {
        String resultado = "[";
        for (int i = 0; i <= ponteiroFim; i++) {
            resultado += dados[i];
            if (i != ponteiroFim) {
                resultado += ",";
            }
        }
        return resultado += "]";
    }
}