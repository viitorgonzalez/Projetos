/*
 * Created by: Vítor Silva Pastor Gonzalez
 * 2023
 */

// iImportações de bibliotecas necessárias
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

// classe principal
public class Main {

    // Cria um objeto Scanner para ler as entradas do usuário
    private static Scanner scanner = new Scanner(System.in);
     // Cria um HashMap para armazenar os objetos da classe Pessoa
    private static HashMap<Integer, Pessoa> pessoas = new HashMap<Integer, Pessoa>();
    // Variável que armazena o contador de identificação (id) das pessoas cadastradas
    private static int idCounter = 0;
    

    public static void main(String[] args) {

        // Tenta ler o valor do idCounter do arquivo idCounter.txt
        try {
            // Cria um objeto File com o nome do arquivo
            File file = new File("./arquivos/idCounter.txt");

            // Se o arquivo existir, o valor é lido e armazenado na variável idCounter
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                idCounter = Integer.parseInt(br.readLine());
                br.close();
            }
        } catch (Exception e) {
            // Caso haja algum erro ao ler o arquivo, exibe uma mensagem de erro
            System.out.println("Erro ao ler o arquivo idCounter.txt");
            e.printStackTrace();
        }

        // Loop infinito que exibe as opções para o usuário e realiza as ações de acordo com a opção escolhida
        while(true) {
    
            System.out.println("Escolha uma opção:");
            System.out.println("1- Cadastrar uma pessoa");
            System.out.println("2- Excluir um cadastro existente");
            System.out.println("3- Exibir todos os cadastros");
            System.out.println("4- Sair");
            System.out.print("Opção: ");
    
            int opc = scanner.nextInt();
            scanner.nextLine(); //limpar buffer do teclado

            // Switch case para verificar a opção escolhida pelo usuário
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
                        FileWriter fw = new FileWriter("./arquivos/idCounter.txt");
                        fw.write(Integer.toString(idCounter));
                        fw.close();
                    } catch (Exception e) {
                        System.out.println("Erro ao escrever o arquivo idCounter.txt");
                        e.printStackTrace();
                    }
                    //Sai da aplicação
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarPessoa() {

        // Lê os dados da pessoa a ser cadastrada
        System.out.println("Digite o nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite sua idade:");
        int idade = scanner.nextInt();
        scanner.nextLine(); //limpar buffer do teclado

        // Cria uma nova pessoa e adiciona à lista de pessoas cadastradas
        Pessoa pessoa = new Pessoa(++idCounter, nome, idade);
        pessoas.put(idCounter, pessoa);

        //cadastrar no arquivo .csv
        try {
            File cadastros = new File("./arquivos/Cadastros.csv");
            FileWriter fwc = new FileWriter(cadastros, true);
            
            fwc.write(pessoa.toString());

            fwc.close();
        } catch (Exception e) {
            System.out.println("Erro!");
            e.printStackTrace();
        }

        System.out.println("Cadastro concluído!");
    }

    private static void excluirCadastro() {

        System.out.println("Digite o id do cadastro que deseja excluir:");
        int idRemove = scanner.nextInt();
        scanner.nextLine(); //limpar buffer do teclado

        // Lê todos os cadastros do arquivo CSV e adiciona a lista de pessoas cadastradas
        try {
            File cadastros = new File("./arquivos/Cadastros.csv");
            BufferedReader brc = new BufferedReader(new FileReader(cadastros));
            
            String linha;
            while ((linha = brc.readLine()) != null) {
                // Divide a linha em um array de strings, usando a vírgula como delimitador
                String[] dados = linha.split(",");
                //cria um objeto de Pessoa com os dados lidos do arquivo
                Pessoa pessoa = new Pessoa(Integer.parseInt(dados[0]), dados[1], Integer.parseInt(dados[2]));
                pessoas.put(pessoa.getId(), pessoa);
            }

            brc.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo Cadastros.csv");
            e.printStackTrace();
        }

        // Pesquisa o id digitado e exclui o cadastro caso seja um id válido pra exclusão
        if(pessoas.containsKey(idRemove)) {
            pessoas.remove(idRemove);
            System.out.println("Cadastro excluído com êxito!");

            // Sobrescreve o arquivo CSV com a lista atualizada de cadastros
            try {
                File cadastros = new File("./arquivos/Cadastros.csv");
                FileWriter fwc = new FileWriter(cadastros);
                
                for(Pessoa pessoa : pessoas.values()) {
                    fwc.write(pessoa.toString());
                }

                fwc.close();
            } catch (Exception e) {
                System.out.println("Erro ao escrever o arquivo Cadastros.csv");
                e.printStackTrace();
            }
        } else {
            System.out.println("Não foi encontrado nenhum cadastro com o id informado!");
        }
    }

    private static void mostrarCadastros() { 

        System.out.println("Lista de cadastros:");

        //mostra os cadastros existentes em formato para arquivo .csv
        for(Pessoa res : pessoas.values()) {
            System.out.println(res);
        } 
    }
}

/*
 * to do:
 * criar o método alterarCadastro()
 * verificar UTF-8
 */