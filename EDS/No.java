package EDS;
import Items.*;
import java.io.Serializable;

/**
 * No da lista circular duplamente ligada.
 * Guarda um item e referencias para o proximo e o anterior.
 *
 * @author Felipe Estima Correia Urzi
 * @author Igor Dias da Silva
 * @author Thierry Nadjarian Rocha da Silva
 * @author Guilherme Pequeneza
 */
public class No implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Item armazenado no no. */
    private Item item;
    /** Referencia para o proximo no da lista. */
    private No next;
    /** Referencia para o no anterior da lista. */
    private No prev;

    /**
     * Cria um no da lista circular contendo um item.
     *
     * @param item item armazenado neste no
     */
    public No(Item item) {
        this.item = item;
    }

    /**
     * Retorna o item armazenado no no.
     *
     * @return item do no
     */
    public Item getItem() {
        return item;
    }

    /**
     * Altera o item armazenado no no.
     *
     * @param item novo item do no
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Retorna o proximo no da lista.
     *
     * @return proximo no
     */
    public No getNext() {
        return next;
    }

    /**
     * Define o proximo no da lista.
     *
     * @param next no que ficara depois deste
     */
    public void setNext(No next) {
        this.next = next;
    }

    /**
     * Retorna o no anterior da lista.
     *
     * @return no anterior
     */
    public No getPrev() {
        return prev;
    }

    /**
     * Define o no anterior da lista.
     *
     * @param prev no que ficara antes deste
     */
    public void setPrev(No prev) {
        this.prev = prev;
    }
}
