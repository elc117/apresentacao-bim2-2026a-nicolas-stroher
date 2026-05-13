import java.time.*;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class Viagem {
    private int id;
    private int idOnibus;
    private String motorista;
    private LocalDateTime dataHora;
    private Duration duracao;
    private int status; // 0: parado; 1: em andamento; 2: finalizada; -1: invalido
    private List<Passageiro> passageiros;

    public Viagem(){
        this.id = -1;
        this.idOnibus = -1;
        this.motorista = "Indefinido";
        this.dataHora = LocalDateTime.MIN;
        this.duracao = Duration.ZERO;
        this.status = -1;
        this.passageiros = new ArrayList<>();
    }

    public Viagem(int id, int idOnibus, String motorista, LocalDateTime dataHora, Duration duracao){
        this.id = id;
        this.idOnibus = idOnibus;
        this.motorista = motorista;
        this.dataHora = dataHora;
        this.duracao = duracao;
        this.status = 0;
        this.passageiros = new ArrayList<>();
    }

    // Getters
    public int getId(){
        return this.id;
    }

    public int getIdOnibus(){
        return this.idOnibus;
    }

    public String getMotorista(){
        return this.motorista;
    }

    public String getDataHora(){
        return this.dataHora.toString();
    }

    public String getDuracao(){
        return this.duracao.toString();
    }
    
    public List<Passageiro> getPassageiros(){
        return this.passageiros;
    }

    // Setters
    public void setId(int newId){
        this.id = newId;
    }

    public void setIdOnibus(int newId){
        this.idOnibus = newId;
    }

    public void setMotorista(String newMotorista){
        this.motorista = newMotorista;
    }

    public void setData(LocalDateTime newDataHora){
        this.dataHora = newDataHora;
    }

    public void setDuracao(Duration newDuracao){
        this.duracao = newDuracao;
    }

    public void addPassageiro(Passageiro newPassageiro){
        this.passageiros.add(newPassageiro);
    }
    

    public void checarViagem(){
        // Checa se a viagem já terminou
        if(status == 1 && LocalDateTime.now().isAfter(this.dataHora.plus(this.duracao)))
            status = 2;
        switch (status) {
            case 0:
                System.out.println("Viagem futura agendada para " + dataHora.toString());
                break;
            
            case 1:
                System.out.println("Viagem em andamento. Hora prevista de chegada: " + this.dataHora.plus(this.duracao));
                break;

            case 2:
                System.out.println("Viagem finalizada. Ocorreu entre " + this.dataHora + " e " + this.dataHora.plus(this.duracao));
                break;

            case -1:
                System.out.println("Viagem invalida. Corrija os dados da viagem");
                break;
        }
    }

    public int validarViagem(){
        if(this.id < 0){
            System.out.println("Id da viagem indefinido. Por favor, defina um id valido");
            return -1;
        }
        if(this.idOnibus < 0){
            System.out.println("Id do onibus indefinido. Por favor, defina um id valido");
            return -1;
        }
        if(this.motorista == "Indefinido"){
            System.out.println("Motorista indefinido. Por favor, atualize o motorista da viagem");
            return -1;
        }
        if(this.dataHora == LocalDateTime.MIN){
            System.out.println("Horario indefinido. Por favor, defina o horario de partida da viagem");
            return -1;
        }
        if(this.duracao == Duration.ZERO){
            System.out.println("Duracao indefinida. Por favor, defina o tempo esperado de duracao da viagem");
            return -1;
        }
        if(this.passageiros.size() == 0){
            System.out.println("Sem passageiros na viagem");
            return -1;
        }
        this.status = 0;
        return 0;
    }

    public void iniciarViagem(){
        if(validarViagem() != 0){
            System.out.println("Viagem invalida. Por favor, cheque as informacoes da viagem");
            return;
        }

        // Checa se o horario de partida esta a 20 minutos ou menos de diferenca do horario estabelecido
        LocalDateTime horaAtual = LocalDateTime.now();
        Duration diferencaHorarios = Duration.between(horaAtual, this.dataHora);
        long minutosDiferenca = Math.abs(diferencaHorarios.toMinutes());

        if(minutosDiferenca <= 20){
            this.dataHora = horaAtual;
            System.out.println("Viagem iniciada! Expectativa de chegada: " + this.dataHora.plus(this.duracao));
            this.status = 1;
        } else {
            System.out.println("Nao foi possivel iniciar a viagem: horario invalido. Por favor, aguarde ou atualize o horario da viagem");
        }
    }
}
