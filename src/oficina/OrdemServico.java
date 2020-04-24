/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author emile, filipe, igor e arthur
 */
public class OrdemServico implements Serializable {

    private int id;
    private int idOS;
    private Date data;
    private double valorPecas;
    private double valorMaoDeObra;
    private Veiculo veiculo;
    private String placa;
    private String descricao;

    public OrdemServico(int idOs, int id, Date data, double valorPecas, double valorMaoDeObra, String placa, String descricao, Veiculo veiculo) {
        this.id = id;
        this.idOS = idOs;
        this.data = data;
        this.valorPecas = valorPecas;
        this.valorMaoDeObra = valorMaoDeObra;
        this.veiculo = veiculo;
        this.placa = placa;
        this.descricao = descricao;
    }

    public int getIdOS() {
        return idOS;
    }

    public void setIdOS(int idOS) {
        this.idOS = idOS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorPecas() {
        return valorPecas;
    }

    public void setValorPecas(double valorPecas) {
        this.valorPecas = valorPecas;
    }

    public double getValorMaoDeObra() {
        return valorMaoDeObra;
    }

    public void setValorMaoDeObra(double valorMaoDeObra) {
        this.valorMaoDeObra = valorMaoDeObra;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public double getValorTotal() {
        return valorPecas + valorMaoDeObra;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public String toString() {
        return "Id: " + id + "\n"
                + "Id OS: " + idOS + "\n"
                + "Nome " + getVeiculo().getCliente().getNome() + "\n"
                + "Valor Peças: " + valorPecas + "\n"
                + "Valor da mão de obra: " + valorMaoDeObra + "\n"
                + "Placa: " + placa + "\n"
                + "Descrição: " + descricao;
    }
}
