/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author emile
 */
public class Controle {

    private static List<OrdemServico> ordens = new ArrayList<>();
    private static List<Veiculo> veiculos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();

    private DAOOrdemServico daoOrdemServico;
    private DAOVeiculo daoVeiculo;
    private DAOCliente daoCliente;

    public Controle() {
        this.daoVeiculo = new DAOVeiculo();
        this.daoCliente = new DAOCliente();
        this.daoOrdemServico = new DAOOrdemServico();
    }

    public void criarOS(double valorPecas, double valorMaoDeObra, String placa, String descricao) throws IOException {
        if (!veiculos.isEmpty()) {
            for (int i = 0; i < veiculos.size(); i++) {
                if (placa.equals(veiculos.get(i).getPlaca())) {
                    Date data = new Date(System.currentTimeMillis());
                    OrdemServico ordemServico = new OrdemServico(gerarCodigoOS(), veiculos.get(i).getCliente().getId(), data, valorPecas, valorMaoDeObra,  placa, descricao, veiculos.get(i));
                    ordens.add(ordemServico);
                    daoOrdemServico.gravarTodos(ordens);
                    JOptionPane.showMessageDialog(null, "Ordem de Serviço criada com sucesso!");
                    return;
                } 
            }
                JOptionPane.showMessageDialog(null, "Veículo não encontrado. Cadastre-o primeiro.");
        }
        else 
            JOptionPane.showMessageDialog(null, "Veículos não cadastrados.");
    }

    public void imprimirOSPlaca(String placa) {
        if (!ordens.isEmpty()) {
            OrdemServico obj;
            for (int i = 0; i < ordens.size(); i++) {
                obj = (OrdemServico) ordens.get(i);
                if (obj.getPlaca().equalsIgnoreCase(placa)) {
                    JOptionPane.showMessageDialog(null, obj);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Placa não encontrada!");
        } else {
            JOptionPane.showMessageDialog(null, "Arquivo vazio!");
        }
    }

    public void imprimirOSNome(String nomeCliente) {
        if (!ordens.isEmpty()) {
            OrdemServico obj;
            for (int i = 0; i < ordens.size(); i++) {
                obj = (OrdemServico) ordens.get(i);
                if (obj.getVeiculo().getCliente().getNome().equals(nomeCliente)) {
                    JOptionPane.showMessageDialog(null, obj);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        } else {
            JOptionPane.showMessageDialog(null, "Arquivo vazio!");
        }
    }

    public void imprimirTodos() {
        for (OrdemServico os : ordens) {
            JOptionPane.showMessageDialog(null, os);
        }
        if (ordens.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Arquivo vazio!");
        }
    }

    public void alterarOS(int idOS, double valorPecas, double valorMaoDeObra, String descricao) throws IOException {
        OrdemServico os = daoOrdemServico.getOSByID(ordens, idOS);
        if (os != null) {
            os.setValorPecas(valorPecas);
            os.setValorMaoDeObra(valorMaoDeObra);
            os.setDescricao(descricao);
        } else {
            JOptionPane.showMessageDialog(null, "Não encontrado ou arquivo vazio!");
        }
        daoOrdemServico.gravarTodos(ordens);
    }

    public void cadastrarVeiculo(String placa, String modelo, String nomeDoCliente) throws IOException {
        if (!clientes.isEmpty()) {
            
            for (int i = 0; i < clientes.size(); i++) {
                if (nomeDoCliente.equals(clientes.get(i).getNome())) {
                    Veiculo veiculo = new Veiculo(gerarCodigoVeiculo(), placa, modelo, clientes.get(i));
                    veiculos.add(veiculo);
                    daoVeiculo.gravarTodos(veiculos);
                    return; 
                } 
            }
            JOptionPane.showMessageDialog(null, "Cliente não encontrado. Cadastre-o primeiro.");
        }
            else
                JOptionPane.showMessageDialog(null, "Clientes não cadatrados.");
        } 

    public void cadastrarCliente(String nomeCliente) throws IOException {
        Cliente c = new Cliente(gerarCodigoCliente(), nomeCliente);
        JOptionPane.showMessageDialog(null, c);
        clientes.add(c);
        daoCliente.gravarTodos(clientes);
    }

    public void excluirVeiculo(int idVeiculo) throws IOException {
        if (!veiculos.isEmpty()) {
            Veiculo v = daoVeiculo.getVeiculoById(veiculos, idVeiculo);
            if (v != null) {
                veiculos.remove(v);
                JOptionPane.showMessageDialog(null, "Veículo removido com sucesso!");
                daoVeiculo.gravarTodos(veiculos);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir veiculo.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Arquivo vazio!");
        }
    }

    public void excluirCliente(int idCliente) throws IOException {
        if (!clientes.isEmpty()) {
            Cliente c = daoCliente.getClienteByID(clientes, idCliente);
            if (c != null) {
                clientes.remove(c);
                JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                daoCliente.gravarTodos(clientes);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir cliente.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Arquivo vazio!");
        }
    }

    public String imprimirVeiculos() {
        String saida = "";
        if (!veiculos.isEmpty()) {
            for (Veiculo veiculo : veiculos) {
                saida += veiculo;
            }
        } else {
            saida += "Arquivo vazio!";
        }
        return saida;
    }

    public String imprimirClientes() {
        String saida = "";
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                saida += cliente;
            }
        } else {
            saida += "Arquivo vazio!";
        }
        return saida;
    }

    public void gravarTodos() throws IOException {
        daoVeiculo.gravarTodos(veiculos);
        daoCliente.gravarTodos(clientes);
        daoOrdemServico.gravarTodos(ordens);
    }

    public void carregarTodos() throws IOException, FileNotFoundException, ClassNotFoundException {
        Controle.veiculos = daoVeiculo.obterTodos();
        Controle.clientes = daoCliente.obterTodos();
        Controle.ordens = daoOrdemServico.obterTodos();
    }

    public void carregar() throws IOException, FileNotFoundException, ClassNotFoundException {
        daoVeiculo.obterTodos();
        daoCliente.obterTodos();
        daoOrdemServico.obterTodos();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Veiculo> getVeiculo() {
        return veiculos;
    }

    public List<OrdemServico> getOrdem() {
        return ordens;
    }
    
    public int gerarCodigoOS(){
        return ordens.get(ordens.size()-1).getIdOS() + 1;
    }
    public int gerarCodigoVeiculo(){
        return veiculos.get(veiculos.size()-1).getIdVeiculo() + 1;
    }
    public int gerarCodigoCliente(){
        return clientes.get(clientes.size()-1).getId()+ 1;
    }
}
