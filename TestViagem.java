import java.util.Scanner;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TestViagem {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatadorDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        System.out.println("Insira o id da viagem:");
        String entradaId = scanner.nextLine();
        int id = Integer.parseInt(entradaId);

        System.out.println("Insira o id do onibus:");
        String entradaIdOnibus = scanner.nextLine();
        int idOnibus = Integer.parseInt(entradaIdOnibus);

        System.out.println("Insira o nome do motorista:");
        String motorista = scanner.nextLine();

        System.out.println("Insira a data e hora da viagem:");
        System.out.println("OBS: insira no formato dia/mes/ano hora:minuto");
        String entradaDataHora = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.MIN;

        try{
            dataHora = LocalDateTime.parse(entradaDataHora, formatadorDataHora);
        } catch (DateTimeParseException e){
            System.out.println("Erro: formato invalido.");
        }

        System.out.println("Insira a duracao estimada da viagem:");
        System.out.println("OBS: insira no formato horas:minutos");
        String entradaDuracao = scanner.nextLine();
        Duration duracao = Duration.ZERO;

        try{
            String[] partes = entradaDuracao.split(":");

            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);

            duracao = Duration.ofHours(horas).plusMinutes(minutos);
        } catch (Exception e){
            System.out.println("Erro: formato invalido");
        }

        scanner.close();
        Viagem viagem1 = new Viagem(id, idOnibus, motorista, dataHora, duracao);

        viagem1.checarViagem();

        viagem1.addPassageiro(new Passageiro("Alberto", "12345678910", 10));
        viagem1.addPassageiro(new Passageiro("Fernando", "01987654321", 20));
        List<Passageiro> passageiros = new ArrayList<>(viagem1.getPassageiros());
        for(int i = 0; i < passageiros.size(); i++){
            System.out.println("\nPassageiro: " + passageiros.get(i).getNome());
            System.out.println("Poltrona: " + passageiros.get(i).getPoltrona());
        }
        viagem1.iniciarViagem();
        viagem1.checarViagem();
    }
}
