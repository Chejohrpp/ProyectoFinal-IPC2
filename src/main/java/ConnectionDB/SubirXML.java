/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import objetos.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author sergi
 */
public class SubirXML {

    
    public SubirXML(){
        
    }
    public void subirDB(String base_path, String fileName){
        try {
            String path = base_path + "/" + fileName;
            File archivo = new File(path);
            //creamos el documento que se pueda entender
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document =  documentBuilder.parse(archivo);            
            document.getDocumentElement().normalize();//hacemos que el documento se comporte de una manera statica            
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName()); 
            
             //lista de los objetos
            NodeList listaGerentes = document.getElementsByTagName("GERENTE");
            NodeList listaCajeros = document.getElementsByTagName("CAJERO");
            NodeList listaClientes = document.getElementsByTagName("CLIENTE");
            NodeList listaTransacciones = document.getElementsByTagName("TRANSACCION");
            
            
            //subir a la DB
            gerentes(listaGerentes);
            cajeros(listaCajeros);
            clientes(listaClientes);
            transacciones(listaTransacciones);
            
        } catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
            
        }
    }

    private void gerentes(NodeList listaGerentes) {
       GerenteModelo gerenteModelo = new GerenteModelo();
        for(int i = 0 ; i < listaGerentes.getLength() ; i++) {
        
            try{                
                Node nodo = listaGerentes.item(i);

                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;

                    int codigo = Integer.parseInt(element.getElementsByTagName("CODIGO").item(0).getTextContent());
                    String dpi = element.getElementsByTagName("DPI").item(0).getTextContent();          
                    String nombre = element.getElementsByTagName("NOMBRE").item(0).getTextContent(); 
                    String turno = element.getElementsByTagName("TURNO").item(0).getTextContent(); 
                    String direccion = element.getElementsByTagName("DIRECCION").item(0).getTextContent(); 
                    String sexo = element.getElementsByTagName("SEXO").item(0).getTextContent();                     
                    String password = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    
                    
                    //los llevamos a la DB
                    Gerente gerene = new Gerente(codigo,nombre,turno,dpi,direccion,sexo,password);
                    gerenteModelo.addGerente(gerene);
                }
            
            }catch(Exception e){
                System.out.println("Error gerente" + e.getMessage());
            }
        }
    }

    private void cajeros(NodeList listaCajeros) {
        CajeroModelo cajeroModelo = new CajeroModelo();
        for(int i = 0 ; i < listaCajeros.getLength() ; i++) {
        
            try{                
                Node nodo = listaCajeros.item(i);

                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;

                    int codigo = Integer.parseInt(element.getElementsByTagName("CODIGO").item(0).getTextContent());
                    String dpi = element.getElementsByTagName("DPI").item(0).getTextContent();          
                    String nombre = element.getElementsByTagName("NOMBRE").item(0).getTextContent(); 
                    String turno = element.getElementsByTagName("TURNO").item(0).getTextContent(); 
                    String direccion = element.getElementsByTagName("DIRECCION").item(0).getTextContent(); 
                    String sexo = element.getElementsByTagName("SEXO").item(0).getTextContent();                     
                    String password = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    
                    
                    //los llevamos a la DB
                    Cajero cajero = new Cajero(codigo,nombre,turno,dpi,direccion,sexo,password);
                    cajeroModelo.addCajero(cajero);
                }
            
            }catch(Exception e){
                System.out.println("Error cajero" + e.getMessage());
            }
        }
    }

    private void clientes(NodeList listaClientes) {
        ClienteModelo clienteModelo = new ClienteModelo();
        CuentaModelo cuentaModelo = new CuentaModelo();
        
       for(int i = 0 ; i < listaClientes.getLength() ; i++) {
            try{
                Node nodo = listaClientes.item(i);
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    
                    Element element = (Element) nodo;//recopilamos el elemto elegido en el index acutal
                    int codigo = Integer.parseInt(element.getElementsByTagName("CODIGO").item(0).getTextContent());                   
                    String nombre = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String birth = element.getElementsByTagName("BIRTH").item(0).getTextContent();
                    String dpi = element.getElementsByTagName("DPI").item(0).getTextContent();
                    String direccion = element.getElementsByTagName("DIRECCION").item(0).getTextContent();
                    String sexo = element.getElementsByTagName("SEXO").item(0).getTextContent();
                    String dpiPdf = element.getElementsByTagName("DPI-PDF").item(0).getTextContent();
                    String password = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
  
                    
                    Cliente cliente = new Cliente(codigo,nombre,birth,dpi,direccion,sexo,null,password);
                    clienteModelo.addCliente(cliente);
                    
                    
                    //usamos esot para llenar todas las espeicalidades que tiene un doctor y subirlo a la DB
                    NodeList listaCuentas = element.getElementsByTagName("CUENTAS"); 
                    try{
                        int cant = 0;
                        while(true){
                            Node nodo1 = listaCuentas.item(0);
                            
                            if (nodo1.getNodeType() == Node.ELEMENT_NODE) {
                                
                                Element element1 = (Element) nodo1;
                                int codigoCuenta = Integer.parseInt(element1.getElementsByTagName("CODIGO").item(cant).getTextContent());
                                String creada = element1.getElementsByTagName("CREADA").item(cant).getTextContent();
                                Double credito = Double.parseDouble(element1.getElementsByTagName("CREDITO").item(cant).getTextContent());
                                
                                
                                Cuenta cuenta = new Cuenta(codigoCuenta,credito,creada,codigo);
                                cuentaModelo.addCuenta(cuenta);
                                
                            }
                            cant++;
                        }
                    }catch(Exception e){
                    }                     
                        
                        
                    }
            }catch(Exception e){
                System.out.println("error cliente: " + e.getMessage());
            }            
       }
    }

    private void transacciones(NodeList listaTransacciones) {
         TransaccionModelo transaccionModelo = new TransaccionModelo();
        for(int i = 0 ; i < listaTransacciones.getLength() ; i++) {
        
            try{                
                Node nodo = listaTransacciones.item(i);

                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;

                    int codigo = Integer.parseInt(element.getElementsByTagName("CODIGO").item(0).getTextContent());
                    int cuenta_id = Integer.parseInt(element.getElementsByTagName("CUENTA-ID").item(0).getTextContent());     
                    String fecha = element.getElementsByTagName("FECHA").item(0).getTextContent(); 
                    String hora = element.getElementsByTagName("HORA").item(0).getTextContent(); 
                    String tipo = element.getElementsByTagName("TIPO").item(0).getTextContent(); 
                    Double monto = Double.parseDouble(element.getElementsByTagName("MONTO").item(0).getTextContent());                     
                    int cajero_id = Integer.parseInt(element.getElementsByTagName("CAJERO-ID").item(0).getTextContent());
                    
                    
                    //los llevamos a la DB
                    Transaccion transaccion = new Transaccion(codigo,monto,tipo,hora,fecha,cajero_id,cuenta_id);
                    transaccionModelo.addTransaccion(transaccion);
                }
            
            }catch(Exception e){
                System.out.println("Error cajero" + e.getMessage());
            }
        }
    }
    
    
    
    
}
