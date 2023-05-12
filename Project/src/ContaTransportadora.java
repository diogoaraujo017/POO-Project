public class ContaTransportadora extends Conta{

    public ContaTransportadora() {
        super();
    }

    public ContaTransportadora(String cod, String mail,String pass) {
        super(cod,mail,pass);
    }

    public ContaTransportadora(Conta c) {
        super(c);
    }
}
