public class Artigo {
    
    private String descricao;
    private String marca;
    private String codigo;
    private double preco_base;
    private double desconto;
    private char estado; // n - artigo novo // a - artigo usado (como novo) // b - artigo usado (bom estado) // c - artigo usado (estado médio/mau)
    private int n_donos;

    public Artigo (){
        this.descricao = null;
        this.marca = null;
        this.codigo = null;
        this.preco_base = 0.00;
        this.desconto = 0.00;
        this.estado = ' ';
        this.n_donos=0;
    }

    public Artigo (String descricao, String marca, String codigo, double preco_base, double desconto, char estado, int n_donos){
        this.descricao = descricao;
        this.marca = marca;
        this.codigo = codigo;
        this.preco_base = preco_base;
        this.desconto = desconto;
        this.estado = estado;
        this.n_donos = n_donos;
    }

    public Artigo (Artigo artigo){
        this.descricao = artigo.getDescricao();
        this.marca = artigo.getMarca();
        this.codigo = artigo.getCodigo();
        this.preco_base = artigo.getPrecoBase();
        this.desconto = artigo.getDesconto();
        this.estado = artigo.getEstado();
        this.n_donos = artigo.getNDonos();
    }

    public String getDescricao(){
        return this.descricao;
    }

    public String getMarca(){
        return this.marca;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public double getPrecoBase(){
        return this.preco_base;
    }

    public double getDesconto(){
        return this.desconto;
    }

    public char getEstado(){
        return this.estado;
    }

    public int getNDonos(){
        return this.n_donos;
    }

    
    public void setDescricao(String descricao){
        this.descricao=descricao;
    }

    public void setMarca(String marca){
        this.marca=marca;
    }

    public void setCodigo(String codigo){
        this.codigo=codigo;
    }

    public void setPrecoBase(double preco_base){
        this.preco_base=preco_base;
    }

    public void setDescricao(double desconto){
        this.desconto=desconto;
    }

    public void setEstado(char estado){
        this.estado=estado;
    }

    public void setNDonos(int n_donos){
        this.n_donos=n_donos;
    }
}
