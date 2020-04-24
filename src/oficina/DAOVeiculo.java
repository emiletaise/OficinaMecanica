/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emile, filipe, igor e arthur
 */
public class DAOVeiculo {

    public List<Veiculo> obterTodos() throws FileNotFoundException, IOException, ClassNotFoundException {
        String arquivo = "./src/oficina/arquivo/Veiculos.ser";
        List<Veiculo> veiculos = new ArrayList();
        if (new File(arquivo).exists()) {
            FileInputStream fis = new FileInputStream(arquivo);
            if (fis.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    Veiculo v = (Veiculo) ois.readObject();
                    veiculos.add(v);
                }
                ois.close();
            }
        }
        return veiculos;
    }

    public void gravarTodos(List<Veiculo> Veiculos) throws FileNotFoundException, IOException {
        String arquivo = "./src/oficina/arquivo/Veiculos.ser";
        FileOutputStream fos = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Veiculo v : Veiculos) {
            if (null != v) {
                oos.writeObject(v);
                oos.flush();
            }
        }
        oos.close();
        System.out.println("Veículo gravado!");
    }

    public Veiculo getVeiculoByPlaca(List<Veiculo> Veiculos, String placa) {
        if (!Veiculos.isEmpty()) {
            Veiculo obj;
            for (int i = 0; i < Veiculos.size(); i++) {
                obj = (Veiculo) Veiculos.get(i);
                if (obj.getPlaca().equals(placa)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public Veiculo getVeiculoById(List<Veiculo> veiculos, int id) {
        if (!veiculos.isEmpty()) {
            Veiculo obj;
            for (int i = 0; i < veiculos.size(); i++) {
                obj = (Veiculo) veiculos.get(i);
                if (obj.getIdVeiculo() == id) {
                    return obj;
                }
            }
        }
        return null;
    }
}
