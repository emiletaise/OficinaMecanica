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

public class DAOCliente {

    public List<Cliente> obterTodos() throws FileNotFoundException, IOException, ClassNotFoundException {
        String arquivo = "./src/oficina/arquivo/Clientes.ser";
        List<Cliente> clientes = new ArrayList();
        if (new File(arquivo).exists()) {
            FileInputStream fis = new FileInputStream(arquivo);
            if (fis.available() > 0) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    Cliente c = (Cliente) ois.readObject();
                    clientes.add(c);
                }
                ois.close();
            }
        }
        return clientes;
    }

    public void gravarTodos(List<Cliente> clientes) throws FileNotFoundException, IOException {
        String arquivo = "./src/oficina/arquivo/Clientes.ser";
        FileOutputStream fos = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Cliente c : clientes) {
            if (null != c) {
                oos.writeObject(c);
                oos.flush();
            }
        }
        oos.close();
        System.out.println("Cliente gravado!");
    }

    public Cliente getClienteByID(List<Cliente> clientes, int idCliente) {
        if (!clientes.isEmpty()) {
            Cliente obj;
            for (int i = 0; i < clientes.size(); i++) {
                obj = (Cliente) clientes.get(i);
                if (obj.getId() == idCliente) {
                    return obj;
                }
            }
        }
        return null;
    }

    public Cliente getClienteByNome(List<Cliente> clientes, String nomeCliente) {
        if (!clientes.isEmpty()) {
            Cliente obj;
            for (int i = 0; i < clientes.size(); i++) {
                obj = (Cliente) clientes.get(i);
                if (obj.getNome() == nomeCliente) {
                    return obj;
                }
            }
        }
        return null;
    }
}
