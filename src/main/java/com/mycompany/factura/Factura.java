
package com.mycompany.factura;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Factura {

    public static void main(String[] args) {
       String client=JOptionPane.showInputDialog(null,"digite nombre del cliente   ","Bienvenido amig@ Cliente - Sistema de Factura ABC",1);
       String[] articulo = new String[100];
       int cant[]=new int [100];
       double precio[]=new double[100];
       double dcto[]=new double [100];
       double total []=new double[100];
       int n=0,i=0;
       double iva=0.0,neto=0.0;
       leer_factura(articulo,cant,precio,dcto,total,n,client);
       while (cant[i]!=0){i++;}n=i;
       iva = calcular_iva(total,n);
       neto = calcular_neto(total,n,iva);
       //imprimir_factura(articulo,cant,precio,dcto,total,n,iva,neto);
       imprimir_facturaNew(articulo,cant,precio,dcto,total,n,iva,neto,client);
    } // cierra void main
    
    static  void leer_factura(String articulo[],int cant[],double precio[],double dcto[],double total[],int n,String client){
      int siga=0;   //  siga=0 ciclo sigue,  =1, ciclo termina
      n=0;
      while (siga==0){
          articulo[n]=JOptionPane.showInputDialog(null,"digite nombre del articulo # "+(n+1),"Capturando datos - Sistema de Factura ABC",1);
          cant[n]=Integer.parseInt(JOptionPane.showInputDialog(null,"digite cantidad de "+articulo[n],"Capturando datos - Sistema de Factura ABC",1));
          precio[n]=Double.parseDouble(JOptionPane.showInputDialog(null,"digite precio unitario del(a) "+articulo[n],"Capturando datos - Sistema de Factura ABC",1));
          dcto[n]=Double.parseDouble(JOptionPane.showInputDialog(null,"digite % de descuento del(a) "+articulo[n],"Capturando datos - Sistema de Factura ABC",1));
          total[n]=precio[n]*cant[n]-(precio[n]*cant[n]*dcto[n])/100;
          siga=JOptionPane.showConfirmDialog(null,"desea facturar otro mas ? ","Factura del(a) sr(a). "+client,1);
          if (siga==0) n++;
      }// cierra while   
    }  // cierra leer_factura


    static double calcular_iva(double total[], int n){
        double suma=0,iva;
        int i;
        for (i=0;i<n;i++){
            suma=suma+total[i];
        }
        iva= suma*16/100;
        return iva;
    }
    
     static double calcular_neto(double total[], int n, double iva){
        double suma=0,neto;
        int i;
        for (i=0;i<n;i++){
            suma=suma+total[i];
        }
        neto= suma+iva;
        return neto;
    }

    static void imprimir_factura(String articulo[],int cant[],double precio[],double dcto[],double total[],int n,double iva, double neto){
        int i;
        double bruto=0;
        for (i=0;i<n;i++){
            bruto+=total[i];
        }
         System.out.println("nombre\t#\tprecio\tdcto\ttotal");
         System.out.println("--------------------------------------");
         for (i=0;i<n;i++){
            System.out.println(articulo[i]+"\t"+cant[i]+"\t"+precio[i]+"\t"+dcto[i]+"%\t"+total[i]);
        } 
        System.out.println("--------------------------------------");
        System.out.println("Total Bruto...............$"+bruto);
        System.out.println("Total Iva.................$ "+iva);
        System.out.println("Total Neto a pagar........$"+neto);
    }
    
     static void imprimir_facturaNew(String articulo[],int cant[],double precio[],double dcto[],double total[],int n,double iva, double neto, String client){
        int i;
        double bruto=0;
        for (int j=0;j<n;j++){
            bruto+=total[j];
        }
         String t= "nombre\t#\tprecio\tdcto\ttotal\n";
        
         for (i=0;i<n;i++){
           
           t += articulo[i]+"\t"+cant[i]+"\t"+precio[i]+"\t"+dcto[i]+"%\t"+total[i]+"\n";
        }        
        t += "Total Bruto\t\t\t\t$"+bruto+"\n";
        t += "Total Iva\t\t\t\t$"+iva+"\n";
        t += "Total Neto a pagar\t\t\t$"+neto+"\n";
        JTextArea resultado=new JTextArea();
        resultado.setText (t);
        JOptionPane.showMessageDialog(null,resultado, "Factura del(a) sr(a) "+client,JOptionPane.INFORMATION_MESSAGE);
        
    }
}
