package util;

import entidade.*;
import java.util.*;

public class GeralUtil {

    public static void cadastroPessoa(Scanner sc, List<Pessoa> pessoas) {
        System.out.print("Código da Pessoa: ");
        String codigo = sc.nextLine();

        Pessoa existente = pessoas.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst().orElse(null);

        if (existente != null) {
            
            System.out.print("Novo nome: "); existente.setNome(sc.nextLine());
            System.out.print("Novo Tipo (Cliente/Fornecedor/Ambos): "); existente.setTipoPessoa(sc.nextLine());
            System.out.print("Está ativo (Sim/Nao): "); existente.setAtivo(GeralUtil.retornaBoolean("Sim", "Nao", sc.nextLine()));
            
            ArquivoUtil.registrarLog("ALTERAÇÃO - Pessoa Código: " + codigo);
            System.out.println("Pessoa atualizada com sucesso!");
        } else {
            // Fluxo de Inclusão
            System.out.print("Nome: "); String nome = sc.nextLine();
            System.out.print("Tipo (Cliente/Fornecedor/Ambos): "); String tipo = sc.nextLine();
            System.out.print("Está ativo (Sim/Nao): "); boolean ativo = GeralUtil.retornaBoolean("Sim", "Nao", sc.nextLine());

            Pessoa novaPessoa = new Pessoa(codigo, nome, tipo, ativo);

            
            System.out.println("\n--- Cadastro de Endereço ---");
            System.out.print("CEP: "); String cep = sc.nextLine();
            System.out.print("Logradouro: "); String logr = sc.nextLine();
            System.out.print("Número: "); String num = sc.nextLine();
            System.out.print("Complemento: "); String comp = sc.nextLine();
            System.out.print("Tipo Endereço (Residencial/Comercial/Entrega): "); String tipoEnd = sc.nextLine();
            
            novaPessoa.getEnderecos().add(new Endereco(cep, logr, num, comp, tipoEnd, true));

            pessoas.add(novaPessoa);
            ArquivoUtil.registrarLog("INCLUSÃO - Pessoa Código: " + codigo);
            System.out.println("Pessoa cadastrada com sucesso!");
        }
    }

    public static void listarPessoas(List<Pessoa> pessoas) {
        System.out.println("\n--- RELATÓRIO DE PESSOAS ---");
        for (Pessoa p : pessoas) {
            System.out.printf("Cód: %s | Nome: %s | Tipo: %s | Ativo: %b\n", p.getCodigo(), p.getNome(), p.getTipoPessoa(), p.isAtivo());
            for (Endereco e : p.getEnderecos()) {
                System.out.printf("  -> Endereço: %s, Nº %s - CEP: %s (%s)\n", e.getLogradouro(), e.getNumero(), e.getCep(), e.getTipoEndereco());
            }
        }
    }

    public static boolean retornaBoolean(String msgTrue, String msgFalse, String valor) {
        return msgTrue.toUpperCase().equals(valor.toUpperCase());
    }
}