public class ListaDuplamenteLigadaCirc{

    No inicio;
    No fim;

    public ListaDuplamenteLigadaCirc() {
        this.fim = null;
        this.inicio = null;
    }

    public No getFim() {
        return fim;
    }

    public No getInicio() {
        return inicio;
    }

    public void setFim(No fim) {
        this.fim = fim;
    }

    public void setInicio(No inicio) {
        this.inicio = inicio;
    }
}