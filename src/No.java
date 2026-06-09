public class No{

    No next;
    No ante;
    Object conteudo;

    public No(){
        this.ante = null;
        this.conteudo = null;
        this.next = null;
    }

    public No getAnte() {
        return ante;
    }

    public No getNext() {
        return next;
    }

    public Object getConteudo() {
        return conteudo;
    }

    public void setAnte(No ante) {
        this.ante = ante;
    }

    public void setNext(No next) {
        this.next = next;
    }

    public void setConteudo(Object conteudo) {
        this.conteudo = conteudo;
    }
}