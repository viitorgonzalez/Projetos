public class Pessoa {
    private final int id;
    private String nome;
    private int idade;

    public Pessoa() {
        this.id = -1;
        this.nome = "admin";
        this.idade = -1;
    }

    public Pessoa(int id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    //usado para mostrar todo o conteúdo do arraylist
    @Override
    public String toString() {
        return id + ", " + nome + ", " + idade;
    }
}