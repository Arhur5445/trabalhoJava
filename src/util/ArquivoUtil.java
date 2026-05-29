package util;

import entidade.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ArquivoUtil {
    private static final String PESSOA_CSV = "pessoas.csv";
    private static final String LOG_TXT = "log.txt";
    private static final String MENU_TXT = "menu.txt";

    public static void registrarLog(String operacao) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(LOG_TXT), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(new java.util.Date() + " - " + operacao);
            bw.newLine();
        } catch (IOException ignored) {}
    }
    public static void exibirMenuDoArquivo() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(MENU_TXT))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Arquivo menu.txt nao encontrado! Crie o arquivo na raiz.");
        }
    }

    public static List<Pessoa> carregarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        if (!Files.exists(Paths.get(PESSOA_CSV))) return pessoas;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(PESSOA_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                Pessoa p = new Pessoa(campos[0], campos[1], campos[2], Boolean.parseBoolean(campos[3]));
                
                // Se houver endereços salvos na mesma linha
                if (campos.length > 4 && !campos[4].isEmpty()) {
                    String[] ends = campos[4].split("\\|");
                    for (String endStr : ends) {
                        String[] f = endStr.split(",");
                        p.getEnderecos().add(new Endereco(f[0], f[1], f[2], f[3], f[4], Boolean.parseBoolean(f[5])));
                    }
                }
                pessoas.add(p);
            }
        } catch (IOException ignored) {}
        return pessoas;
    }

    public static void salvarPessoas(List<Pessoa> pessoas) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(PESSOA_CSV))) {
            for (Pessoa p : pessoas) {
                StringBuilder sbEnd = new StringBuilder();
                for (Endereco e : p.getEnderecos()) {
                    sbEnd.append(String.join(",", e.getCep(), e.getLogradouro(), e.getNumero(), e.getComplemento(), e.getTipoEndereco(), String.valueOf(e.isAtivo()))).append("|");
                }
                String endsStr = sbEnd.length() > 0 ? sbEnd.substring(0, sbEnd.length() - 1) : "";
                
                bw.write(String.join(";", p.getCodigo(), p.getNome(), p.getTipoPessoa(), String.valueOf(p.isAtivo()), endsStr));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}