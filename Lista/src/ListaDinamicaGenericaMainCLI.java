import java.util.Scanner;

/**
 * Classe que demonstra o uso da lista dinâmica genérica através de uma interface de linha de comando.
 * Esta classe implementa um menu interativo que permite ao usuário testar todas as operações
 * disponíveis na lista dinâmica.
 *
 * @author Cláudio Rodolfo Sousa de Oliveira
 * @version 1.0
 * @since 2025-06-04
 */
public class ListaDinamicaGenericaMainCLI {

    private static Scanner scanner;
    private static ListaDinamicaGenerica<String> lista;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        lista = new ListaDinamicaGenerica<>(5);
        executar();
    }

    private static void executar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1) Anexar");
        System.out.println("2) Inserir");
        System.out.println("3) Selecionar");
        System.out.println("4) Selecionar Todos");
        System.out.println("5) Atualizar");
        System.out.println("6) Apagar");
        System.out.println("7) Imprimir");
        System.out.println("8) Está Vazia?");
        System.out.println("9) Está Cheia?");
        System.out.println("0) Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        return scanner.nextInt();
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                anexar();
                break;
            case 2:
                inserir();
                break;
            case 3:
                selecionar();
                break;
            case 4:
                selecionarTodos();
                break;
            case 5:
                atualizar();
                break;
            case 6:
                apagar();
                break;
            case 7:
                imprimir();
                break;
            case 8:
                estaVazia();
                break;
            case 9:
                estaCheia();
                break;
            case 0:
                System.out.println("Programa finalizado!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void anexar() {
        System.out.print("Digite o elemento a ser anexado: ");
        scanner.nextLine(); // Limpa o buffer
        String elemento = scanner.nextLine();
        try {
            lista.anexar(elemento);
            System.out.println("Elemento anexado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao anexar: " + e.getMessage());
        }
    }

    // ... outros métodos de interação com o usuário ...

    private static void imprimir() {
        System.out.println("Lista: " + lista.imprimir());
    }
}
