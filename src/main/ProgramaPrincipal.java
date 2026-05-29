package main;

import entidade.Pessoa;
import java.util.*;
import util.*;

public class ProgramaPrincipal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Pessoa> pessoas = ArquivoUtil.carregarPessoas();

        int opcao;
        do {
            ArquivoUtil.exibirMenuDoArquivo(); 
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    GeralUtil.cadastroPessoa(sc, pessoas);
                    break;
                case 2:
                    GeralUtil.listarPessoas(pessoas);
                    break;
                case 5:
                    ArquivoUtil.salvarPessoas(pessoas);
                    System.out.println("Dados salvos. Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
        sc.close();
    }
}