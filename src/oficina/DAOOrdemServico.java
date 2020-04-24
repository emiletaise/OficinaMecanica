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
public class DAOOrdemServico {
    public List<OrdemServico> obterTodos() throws FileNotFoundException, IOException, ClassNotFoundException {
        String arquivo = "./src/oficina/arquivo/OrdensDeServico.ser";
        List<OrdemServico> ordensServico = new ArrayList();
        if (new File(arquivo).exists()) {
            FileInputStream fis = new FileInputStream(arquivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                OrdemServico os = (OrdemServico) ois.readObject();
                ordensServico.add(os);
            }
            ois.close();
        }
        return ordensServico;
    }

    public void gravarTodos(List<OrdemServico> Servicos) throws FileNotFoundException, IOException {
        String arquivo = "./src/oficina/arquivo/OrdensDeServico.ser";
        FileOutputStream fos = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (OrdemServico os : Servicos) {
            if (null != os) {
                oos.writeObject(os);
                oos.flush();
            }
        }
        oos.close();
        System.out.println("Ordem de serviço gravada!");
    }

    public OrdemServico getOSByPlaca(List<OrdemServico> ordemServico, String idPlaca) {
        if (!ordemServico.isEmpty()) {
            OrdemServico obj;
            for (int i = 0; i < ordemServico.size(); i++) {
                obj = (OrdemServico) ordemServico.get(i);
                if (obj.getVeiculo().getPlaca().equals(idPlaca)) {
                    return obj;
                }
            }
        }
        return null;
    }

    public OrdemServico getOSByID(List<OrdemServico> ordemServico, int idOS) {
        if (!ordemServico.isEmpty()) {
            OrdemServico obj;
            for (int i = 0; i < ordemServico.size(); i++) {
                obj = (OrdemServico) ordemServico.get(i);
                if (obj.getIdOS() == idOS){
                    return obj;
                }
            }
        }
        return null;
    }

    public OrdemServico getOSByNome(List<OrdemServico> ordemServico, String nomeCliente) {
        if (!ordemServico.isEmpty()) {
            OrdemServico obj;
            for (int i = 0; i < ordemServico.size(); i++) {
                obj = (OrdemServico) ordemServico.get(i);
                if (obj.getVeiculo().getCliente().getNome().equals(nomeCliente)) {
                    return obj;
                }
            }
        }
        return null;
    }
}
