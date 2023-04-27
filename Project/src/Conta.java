import java.util.Objects;

public class Conta {

    private String codigo;
    private String email;
    private String password;


    public Conta() {
        this.codigo = null;
        this.email = null;
        this.password = null;
    }

    public Conta(String codigo, String email, String password) {
        this.codigo = codigo;
        this.email = email;
        this.password = password;
    }


    public Conta(Conta c){
        this.codigo = c.codigo;
        this.email = c.email;
        this.password = c.password;
    }

    // GETTERS
    public String getCodigo(){
        return this.codigo;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    //SETTERS

    public void setCodigo(String cod){
        this.codigo = cod;
    }

    public void setEmail(String novoEmail){
        this.email = novoEmail ;
    }

    public void setPassword(String novaPass){
        this.password = novaPass;
    }

    //CLONE
    public Conta clone() {
        return new Conta(this);
    }

    //EQUALS
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return this.codigo.equals(conta.codigo) &&
                this.email.equals(conta.email) &&
                this.password.equals(conta.password);
    }

    //ToString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo da conta: '").append(this.codigo).append("'\n");
        sb.append("Email: '").append(this.email).append("'\n");
        sb.append("Password: '").append(this.password.replaceAll("\\S","*")).append("'\n");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hash(this.email, this.password);
    }

    //public boolean verificaPassword (String password){
    //    return this.password.equals(password);
    //}
}