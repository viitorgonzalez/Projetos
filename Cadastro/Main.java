// IMPORTS
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


// CLASSE MAIN
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
    private static int idCounter = 0;
    

    public static void main(String[] args) {

        // Ler o valor do idCounter do arquivo
        try {
            File file = new File("idCounter.txt");
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                idCounter = Integer.parseInt(br.readLine());
                br.close();
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo idCounter.txt");
            e.printStackTrace();
        }

        while(true){
    
            System.out.println("Escolha uma opção:");
            System.out.println("1- Cadastrar uma pessoa");
            System.out.println("2- Excluir um cadastro existente");
            System.out.println("3- Exibir todos os cadastros");
            System.out.println("4- Sair");
            System.out.print("Opção: ");
    
            int opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 1:
                    cadastrarPessoa();
                    break;
                
                case 2:
                    excluirCadastro();
                    break;

                case 3:
                    mostrarCadastros();
                    break;

                case 4:
                     // Atualizar o arquivo com o último valor de idCounter
                     try {
                        FileWriter fw = new FileWriter("idCounter.txt");
                        fw.write(Integer.toString(idCounter));
                        fw.close();
                    } catch (Exception e) {
                        System.out.println("Erro ao escrever o arquivo idCounter.txt");
                        e.printStackTrace();
                    }
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
    
        }
    }

    private static void cadastrarPessoa() {

        System.out.println("Digite o nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite sua idade:");
        int idade = scanner.nextInt();
        scanner.nextLine();

        Pessoa pessoa = new Pessoa(++idCounter, nome, idade);
        listaPessoas.add(pessoa);

        System.out.println("Cadastro concluído!");
    }

    private static void excluirCadastro() {

        System.out.println("Digite o id do cadastro que deseja excluir:");
        int idRemove = scanner.nextInt();
        scanner.nextLine();

        for(Pessoa pessoa : listaPessoas) {
            if(pessoa.getId() == idRemove) {
                listaPessoas.remove(pessoa);
                System.out.println("Cadastro excluído com êxito!");
                return;
            }
        }
        System.out.println("Não foi encontrado nenhum cadastro com o id informado!");
    }

    private static void mostrarCadastros() {

        System.out.println("Lista de cadastros:");

        for (int i = 0; i < listaPessoas.size(); i++) {
            Pessoa pessoa = listaPessoas.get(i);
            System.out.println("ID: " + pessoa.getId());
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Idade: " + pessoa.getIdade());
            System.out.println("-----------------------------------------------------");
        }
    }
}