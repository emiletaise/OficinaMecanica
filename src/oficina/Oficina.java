/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author emile, filipe, igor e arthur
 */
public class Oficina {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        int opcao = 10;
        Controle oficina = new Controle();
        try {
            oficina.carregarTodos();
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados!");
        } catch (ClassNotFoundException e) {
            System.err.println("Classe não encontrada!");
        }
        while (opcao > 0) {
            String entrada = JOptionPane.showInputDialog(null,
                    "Escolha uma das opções abaixo: \n"
                    + "1 - Criar ordem de serviço;\n"
                    + "2 - Alterar uma OS informando valor das peças, descrição e valor da mão de obra;\n"
                    + "3 - Imprimir uma ordem de serviço informando a placa do carro;\n"
                    + "4 - Imprimir ordem de serviço informando o nome do cliente;\n"
                    + "5 - Imprimir todas as ordens de serviço;\n"
                    + "6 - Cadastrar veículos;\n"
                    + "7 - Cadastrar clientes;\n"
                    + "8 - Excluir veículos;\n"
                    + "9 - Excluir clientes;\n"
                    + "10 - Imprimir veículos;\n"
                    + "11 - Imprimir clientes;\n"
                    + "0 - Sair.");
            try {
                opcao = Integer.parseInt(entrada);
                String nomeDoCliente;
                switch (opcao) {
                    case 1:
                        //1 - Criar ordem de serviço;
                        try {
                            String placa = JOptionPane.showInputDialog(null, "Digite a placa do veiculo: ");
                            String p = JOptionPane.showInputDialog(null, "Digite o valor da peça: ");
                            double pecas = (double) Double.parseDouble(p);
                            String m = JOptionPane.showInputDialog(null, "Digite o valor da mão de obra: ");
                            double maoDeObra = (double) Double.parseDouble(m);
                            String descricao = JOptionPane.showInputDialog(null, "Descrição do serviço: ");
                            oficina.criarOS(pecas, maoDeObra, placa, descricao);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Entrada dos valores inválida. Tente novamente");
                        }
                        break;

                    case 2:
                        //2 - Alterar uma OS informando valor das peças, descrição e valor da mão de obra;
                        try {
                            String idos = JOptionPane.showInputDialog(null, "Digite o ID do OS a ser alterado: ");
                            int idOS = (int) Integer.parseInt(idos);
                            String p = JOptionPane.showInputDialog(null, "Digite o valor da peça a ser alterado: ");
                            double pecas = (double) Double.parseDouble(p);
                            String m = JOptionPane.showInputDialog(null, "Digite o valor da nova mão de obra: ");
                            double maoDeObra = (double) Double.parseDouble(m);
                            String descricao = JOptionPane.showInputDialog(null, "Digite a descrição do serviço: ");
                            oficina.alterarOS(idOS, pecas, maoDeObra, descricao);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Entrada inválida. Tente novamente");
                        }
                        break;

                    case 3:
                        //3 - Imprimir uma ordem de serviço informando a placa do carro;
                        String placa = JOptionPane.showInputDialog(null, "Digite a placa do veículo: ");
                        oficina.imprimirOSPlaca(placa);
                        break;

                    case 4:
                        //4 - Imprimir ordem de serviço informando o nome do cliente;
                        String nomeCliente = JOptionPane.showInputDialog(null, "Digite o nome do cliente: ");
                        oficina.imprimirOSNome(nomeCliente);
                        break;

                    case 5:
                        //5 - Imprimir todas as ordens de serviço;
                        oficina.imprimirTodos();
                        break;

                    case 6:
                        //6 - Cadastrar veículos;
                        nomeDoCliente = JOptionPane.showInputDialog(null, "Digite o nome do cliente: ");
                        placa = JOptionPane.showInputDialog(null, "Digite a placa do veículo: ");
                        String modelo = JOptionPane.showInputDialog(null, "Digite o modelo do veículo: ");
                        oficina.cadastrarVeiculo(placa, modelo, nomeDoCliente);
                        break;

                    case 7:
                        //7 - Cadastrar clientes;
                        nomeDoCliente = JOptionPane.showInputDialog(null, "Digite  nome do cliente:");
                        oficina.cadastrarCliente(nomeDoCliente);
                        break;

                    case 8:
                        //8 - Excluir veículos;
                        String idv = JOptionPane.showInputDialog(null, "Digite o ID do veículo a ser removido: ");
                        int idVeiculo = (int) Integer.parseInt(idv);
                        oficina.excluirVeiculo(idVeiculo);
                        break;

                    case 9:
                        //9 - Excluir clientes;
                        String c = JOptionPane.showInputDialog(null, "Digite o ID do cliente: ");
                        int idClient = Integer.parseInt(c);
                        oficina.excluirCliente(idClient);
                        break;

                    case 10:
                        JOptionPane.showMessageDialog(null, oficina.imprimirVeiculos());
                        break;

                    case 11:
                        JOptionPane.showMessageDialog(null, oficina.imprimirClientes());
                        break;

                    case 0:
                        oficina.gravarTodos();
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Tente novamente");
                e.getMessage();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro.");
                e.getMessage();
            }
        }
    }
}
