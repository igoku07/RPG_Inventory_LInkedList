package EDS;
import Items.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Estrutura de dados circular duplamente ligada usada para armazenar os itens.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Thierry Nadjarian Rocha da Silva
 * @author Guilherme Pequeneza
 */
public class ListaDuplamenteLigadaCirc implements Iterable<Item>, Serializable {
    private static final long serialVersionUID = 1L;

    /** Primeiro no da lista circular. */
    private No head;
    /** Quantidade de itens armazenados na lista. */
    private int tamanho;

    /**
     * Cria uma lista circular duplamente ligada vazia.
     */
    public ListaDuplamenteLigadaCirc() {
    }

    /**
     * Adiciona um item no final da lista circular duplamente ligada.
     *
     * @param item item que sera armazenado em um novo no
     */
    public void adicionar(Item item) {
        No novo = new No(item);

        if (head == null) {
            novo.setNext(novo);
            novo.setPrev(novo);
            head = novo;
        } else {
            No tail = head.getPrev();
            novo.setNext(head);
            novo.setPrev(tail);
            tail.setNext(novo);
            head.setPrev(novo);
        }

        tamanho++;
    }

    /**
     * Remove um item da lista circular, religando os nos vizinhos.
     *
     * @param item item que deve ser removido
     * @return true se o item foi encontrado e removido; false caso contrario
     */
    public boolean remover(Item item) {
        if (head == null || item == null) {
            return false;
        }

        No atual = head;
        for (int i = 0; i < tamanho; i++) {
            if (atual.getItem() == item) {
                if (tamanho == 1) {
                    head = null;
                } else {
                    atual.getPrev().setNext(atual.getNext());
                    atual.getNext().setPrev(atual.getPrev());
                    if (atual == head) {
                        head = atual.getNext();
                    }
                }

                tamanho--;
                return true;
            }

            atual = atual.getNext();
        }

        return false;
    }

    /**
     * Verifica se a lista nao possui itens.
     *
     * @return true quando a lista esta vazia
     */
    public boolean estaVazia() {
        return tamanho == 0;
    }

    /**
     * Retorna a quantidade atual de itens na lista.
     *
     * @return tamanho da lista
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     * Retorna o primeiro no da lista circular.
     *
     * @return no inicial da lista ou null se estiver vazia
     */
    public No getHead() {
        return head;
    }

    /**
     * Cria um iterador que percorre a lista uma unica volta completa.
     *
     * @return iterador dos itens armazenados
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private No atual = head;
            private int percorridos = 0;

            /**
             * Verifica se ainda ha itens para percorrer nesta volta.
             *
             * @return true enquanto o iterador nao percorreu todos os itens
             */
            @Override
            public boolean hasNext() {
                return percorridos < tamanho;
            }

            /**
             * Retorna o item atual e avanca para o proximo no.
             *
             * @return proximo item da iteracao
             */
            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Item item = atual.getItem();
                atual = atual.getNext();
                percorridos++;
                return item;
            }
        };
    }
}
