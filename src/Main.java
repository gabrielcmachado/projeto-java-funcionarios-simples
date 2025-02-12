import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente"));

        System.out.println("Tarefa: Inserir todos os Funcionários na mesma ordem da tabela - Pressione enter para seguir...");
        scanner.nextLine();
        funcionarios.forEach(System.out::println);

        System.out.println("\nTarefa: Remove João da Lista - Pressione enter para seguir...");
        scanner.nextLine();
        String funcionarioExcluir = "joão";
        long verificaExistencia =  funcionarios.stream().filter(funcionario -> funcionario.getNome().equalsIgnoreCase(funcionarioExcluir)).count();
        if(verificaExistencia > 0){
            System.out.println("Encontramos: " + verificaExistencia + " Registro(s) com o nome: " + funcionarioExcluir);
            funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase("João"));
        }else {
            System.out.println("Não encontramos nenhum Registro(s) com o nome: " + funcionarioExcluir);
        }

        System.out.println("\nTarefa: Imprime todos os funcionários e suas Informações - Pressione enter para seguir...");
        scanner.nextLine();
        funcionarios.forEach(System.out::println);

        System.out.println("\nTarefa: Aplicar um Reajuste de 10% no salário - Pressione enter para seguir...");
        scanner.nextLine();
        Double reajuste = 10.00;
        funcionarios.forEach(funcionario -> {
            BigDecimal aumento = funcionario.getSalario().multiply(BigDecimal.valueOf(reajuste/100));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        });
        funcionarios.forEach(System.out::println);

        System.out.println("\nTarefas: Agrupar Colaboradores por Função e Imprimir - Pressione enter para seguir...");
        scanner.nextLine();
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("******* Função: " + funcao + " *******");
            lista.forEach(System.out::println);
            System.out.println("============================================================");
        });

        System.out.println("\nTarefa: Aniversariantes de Outubro e Dezembro - Pressione enter para seguir...");
        scanner.nextLine();
        funcionarios.stream()
                .filter(funcionario -> {
                    int mes = funcionario.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(System.out::println);

        System.out.println("\nTarefa: Retornar Funcionário Mais Velho - Pressione enter para seguir...");
        scanner.nextLine();
        Funcionario funcionarioMaisVelho = Collections.max(funcionarios,
                Comparator.comparing(funcionario -> Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears()));
        int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("Nome: " + funcionarioMaisVelho.getNome() + " Idade: " + idade);

        System.out.println("\nTarefa: Imprimir Funcionários em Ordem Alfabética - Pressione enter para seguir...");
        scanner.nextLine();
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        System.out.println("\nTarefa: Imprimir soma de todos os salários - Pressione enter para seguir...");
        scanner.nextLine();
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: R$ " + totalSalarios.setScale(2, RoundingMode.HALF_UP).toString().replace(".", ","));

        System.out.println("\nTarefa: Imprimir quantos salários mínimos ganha cada colaborador - Pressione enter para seguir...");
        scanner.nextLine();
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        funcionarios.forEach(funcionario -> {
            BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("Nome: %s, Salários Mínimos: %s \n", funcionario.getNome(), quantidadeSalariosMinimos.toString().replace(".", ","));
        });

        System.out.println("\n\nFim da Tarefa, Obrigado!");
        System.out.println("Pressione enter para sair...");
        scanner.nextLine();
        scanner.close();
    }
}