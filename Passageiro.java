public class Passageiro {
    private String nome;
    private String cpf;
    private int poltrona;

    public Passageiro(String nome, String cpf, int poltrona){
        this.nome = nome;
        this.cpf = cpf;
        this.poltrona = poltrona;
    }

    // Getters
    public String getNome(){
        return this.nome;
    }

    public String getCpf(){
        return this.cpf;
    }

    public int getPoltrona(){
        return this.poltrona;
    }

    //Setters
    public void setNome(String newNome){
        this.nome = newNome;
    }

    public void setCpf(String newCpf){
        this.cpf = newCpf;
    }

    public void setPoltrona(int newPoltrona){
        this.poltrona = newPoltrona;
    }
    
}
