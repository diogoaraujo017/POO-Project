import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Conta implements Serializable {

    private String codigo;
    private String email;
    private String password;


    public Conta() {
        this.codigo = "";
        this.email = "";
        this.password = "";
    }

    public Conta(String codigo, String email, String password) {
        this.codigo = codigo;
        this.email = email;
        this.password = password;
    }


    public Conta(Conta c){
        this.codigo = c.getCodigo();
        this.email = c.getEmail();
        this.password = c.getPassword();
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
        sb.append("Codigo da conta: ").append(this.codigo).append("\n");
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Password: ").append(this.password).append("\n");
        return sb.toString();
    }

    public int hashCode() {
        return Objects.hash(this.email, this.password);
    }

    //public boolean verificaPassword (String password){
    //    return this.password.equals(password);
    //}

    public String geraCodigo(){
        String charSet = "qwertyuiopasdfghjklzxcvbnm0123456789";
        Random rand = new Random();
        int codigoLength = 8;
        codigo="";
        for (int j = 0; j < codigoLength; j++) {
            this.codigo += charSet.charAt(rand.nextInt(charSet.length())); // adiciona um caractere aleatório da string charSet
        }
        return codigo;
    }
}