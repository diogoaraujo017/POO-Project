import java.io.Serializable;

public class ContaTransportadora extends Conta implements Serializable {

    public ContaTransportadora() {
        super();
    }

    public ContaTransportadora(String cod, String mail,String pass) {
        super(cod,mail,pass);
    }

    public ContaTransportadora(ContaTransportadora c) {
        super(c);
    }

    public Conta clone() {
        return new ContaTransportadora(this);
    }
}
