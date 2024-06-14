package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchivoServicio {
    private Cliente cliente;
    public List<Cliente> cargarDatos(String fileName) {
        List<Cliente> listaClientes = new ArrayList<>();

        System.out.println("Cargar Datos");
        File archivo = new File("src/main/java/datos/" + fileName);

        try(FileReader newReader = new FileReader(archivo);
            BufferedReader newBufferedR = new BufferedReader(newReader)) {
            String linea;
            while ((linea = newBufferedR.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    String runCliente = datos[0].trim();
                    String nombreCliente = datos[1].trim();
                    String apellidoCliente = datos[2].trim();
                    int aniosCliente = Integer.parseInt(datos[3].trim());

                    if(datos[4].trim().equals("Activo")) {
                        cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, CategoriaEnum.ACTIVO);
                    } else if(datos[4].trim().equals("Inactivo")) {
                        cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, CategoriaEnum.INACTIVO);
                    } else {
                        System.out.println("Categoría no determinada");
                    }
                    listaClientes.add(cliente);
                }
            }
            System.out.println("Datos cargados correctamente en la lista");

        } catch (IOException e) {
            System.out.println("Error al Leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir número: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error al interpretar estado del cliente: " + e.getMessage());
        }
        return listaClientes;
    }
}
