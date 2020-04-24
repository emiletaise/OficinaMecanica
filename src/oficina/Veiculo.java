/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina;

import java.io.Serializable;

/**
 *
 * @author emile, filipe, igor e arthur
 */
public class Veiculo implements Serializable {

    private int idVeiculo;
    private String placa;
    private String modelo;
    private Cliente cliente;

    public Veiculo(int idVeiculo, String placa, String modelo, Cliente cliente) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.modelo = modelo;
        this.cliente = cliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String toString() {
        String temp = "";
        temp += "Id Cliente" + cliente.getId() + "\n";
        temp += "Id Veículo: " + getIdVeiculo() + "\n";
        temp += "Nome do cliente: " + getCliente().getNome() + "\n";
        temp += "Placa: " + getPlaca() + "\n";
        temp += "Modelo: " + getModelo() + "\n";
        return temp;
    }
}
