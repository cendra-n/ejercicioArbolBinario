/*
a- Cargar en un arbol binario ordenado (números enteros testeando que no exista dicho número). Detener la carga al cargar cero. (el cero no se carga).
b- Imprimir los números ingresados en pre orden, pos orden y entre orden.
c- Borrar el dato mayor y el menor del árbol.
d- Informar la altura del árbol y, en el entre orden, agregar a qué nivel pertenece cada dato.
Imprimir en preOrden.

Pasar los datos a una lista en posOrden.
luego esa lista pasarlo a un vector y que quede ordenado
de menor a mayor
Imprimir la lista.
modelo de parcial
 */

package com.mycompany.copiaarbol;

import java.util.ArrayList;
import java.util.Scanner;


public class CopiaArbol {
    private Scanner teclado= new Scanner(System.in);
    private int altura;
    private int[] vector; //declaramos el vector
    private ArrayList<Integer> listaArbol = new ArrayList<Integer>();
    
    class Nodo {
        int info;
        Nodo izq, der;
    }
    private Nodo raiz;
    
    public void cargarArbol(){
       int numero;
        
       do{
          System.out.print("ingrese un numero: ");
          numero= teclado.nextInt();
          if( numero == 0){
             break;
          }
          else{
              insertar(numero);
          }

        }while(numero !=0 );
        System.out.println("Punto A cumplido,Se cargo el arbol y se detuvo en 0");
    
        
    }
    
    public boolean existe(int info) {//chequea si el num ingresado ya esta en el arbol
      // Crear nodo igualado a raíz (nivel uno).
      Nodo reco = raiz;
      // Un while para recorrer el árbol siempre que reco sea diferente a null
      while (reco != null) {
            if (info == reco.info) {
                return true; // Si existe, retorna verdadero.
            } else if (info > reco.info) {
                // Si no existe, busca por la derecha
                reco = reco.der;
            } else {
                // Si no existe, busca por la izquierda
                reco = reco.izq;
            }
        }
      // Si no encuentra el dato, retorna false.
      return false;
    }

    public void insertar(int info) { //inserta el numero en el arbol
        if (!existe(info)) {
            Nodo nuevo = new Nodo();
            nuevo.info = info;
            nuevo.izq = null;
            nuevo.der = null;

            if (raiz == null) {
                raiz = nuevo; // Asigna el nuevo nodo como raíz si el árbol está vacío.
            } else {
                Nodo anterior = null, reco;
                reco = raiz;

                while (reco != null) {
                    anterior = reco;
                    if (info < reco.info) {
                        reco = reco.izq;
                    } else {
                        reco = reco.der;
                    }
                }

                // Asigna el nuevo nodo como hijo izquierdo o derecho según corresponda.
                if (info < anterior.info) {
                    anterior.izq = nuevo;
                } else {
                    anterior.der = nuevo;
                }
            }
        }
    }
    
    //Metodo recursivo que recíbe el puntero del nodo
    private void recorrerPre(Nodo reco) {
      //certificar que reco este apuntando a un nodo
      if (reco != null) {
         //si reco apunta a un nodo, realizar lo que el algoritmo dice
         //visitar la raiz
          System.out.print(reco.info + " - ");
         //realizar las llamadas recursivas para recorrer en pre-Orden
         recorrerPre(reco.izq);
         recorrerPre(reco.der);
        }
    }
    
    //Metodo público para llamar al método recursivo
    public void recorrerPre() {
      //llamar a recorrerPre enviando la raiz
      recorrerPre(raiz);
      System.out.println();
    }
    
    private void imprimirEntre(Nodo reco) {
        if (reco != null) {
            imprimirEntre(reco.izq);
            System.out.print(reco.info + " - ");
            imprimirEntre(reco.der);
        }
    }

    public void imprimirEntre() {
        imprimirEntre(raiz);
        System.out.println();
    }
    
    private void imprimirPost(Nodo reco) {
        if (reco != null) {
            imprimirPost(reco.izq);
            imprimirPost(reco.der);
            System.out.print(reco.info + " - ");
        }
    }

    public void imprimirPost() {//ESTE ES EL QUE SE LLAMA PARA IMPRIMIR, el que no tiene parametros
        imprimirPost(raiz);
        System.out.println();
    }
    
    public void borrarMenor() { //borra el dato menor dentro del arbol
        // Para saber si existe
        if (raiz != null) {
            if (raiz.izq == null) {
                // Si la raíz izquierda no existe, igualamos raíz con derecha.
                raiz = raiz.der;
            } else {
                // Si existe la raíz izquierda, creamos dos punteros.
                // Un puntero que apunta a la raíz.
                Nodo atras = raiz;
                // Un puntero que apunta al subárbol izquierdo.
                Nodo reco = raiz.izq;
                // Descendemos siempre y cuando exista un dato menor.
                while (reco.izq != null) {
                    // Actualizamos los punteros a la última posición existente.
                    atras = reco;
                    reco = reco.izq;
                }
                // Al salir del while, los punteros apuntan al nodo a borrar.
                // Enlazamos el izquierdo anterior al actual derecho para no perder el dato.
                atras.izq = reco.der;
            }
        }
    }
    
    public void borrarMayor() { //borra el dato mayor dentro del arbol
        // Para saber si existe
        if (raiz != null) {
            if (raiz.der == null) {
                // Si la raíz der no existe, igualamos raíz con izq.
                raiz = raiz.izq;
            } else {
                // Si existe la raíz izquierda, creamos dos punteros.
                // Un puntero que apunta a la raíz.
                Nodo atras = raiz;
                // Un puntero que apunta al subárbol izquierdo.
                Nodo reco = raiz.der;
                // Descendemos siempre y cuando exista un dato menor.
                while (reco.der != null) {
                    // Actualizamos los punteros a la última posición existente.
                    atras = reco;
                    reco = reco.der;
                }
                // Al salir del while, los punteros apuntan al nodo a borrar.
                // Enlazamos el izquierdo anterior al actual derecho para no perder el dato.
                atras.der= reco.izq;
            }
        }
    }

    public void mayorValor() { //BUSCA EL MAYOR VALOR DENTRO DEL ARBOL
      if (raiz!=null) {
            Nodo reco=raiz;
            while (reco.der!=null){
             reco=reco.der;
             System.out.println("Mayor valor del arbol:"+reco.info);
           }
        }
    }


    public void menorValor() {// BUSCA EL MENOR VALOR DENTRO DEL ARBOL
      if (raiz!=null) {
            Nodo reco=raiz;
        while (reco.izq!=null){
            reco=reco.izq;
            System.out.println("Menor valor del arbol:"+reco.info);
        }
      }
    }
    
    private void retornarAltura(Nodo reco, int nivel) {// para sacar la altura del arbol
        // Siempre que haya nodo.
        if (reco != null) {
            if (nivel > altura) {
                // Cuando el nivel sea mayor a la altura, los igualamos.
                altura = nivel;
            }
           // System.out.print(reco.info+": "++" -- ");//nivel
            retornarAltura(reco.izq, nivel + 1);
            retornarAltura(reco.der, nivel + 1);
        }
}

    // Crear el atributo private int altura; //ya esta declarado al inicio
    public int retornarAltura() {
        // Inicializar altura en 0.
        altura = 0;
        // Llamar al método retornarAltura enviando raíz y un 1 (que es el nivel de la raíz).
        retornarAltura(raiz, 1);
        return altura;
    }
    
    
    
    //grabamos la lista en post orden
    private void grabarLista(Nodo reco) {
        if (reco != null) {
            grabarLista(reco.izq);
            grabarLista(reco.der);
            listaArbol.add(reco.info);//se carga el valor en el arraylist
        }
    }

    public void grabarLista() {//ESTE ES EL QUE SE LLAMA PARA IMPRIMIR, el que no tiene parametros
        grabarLista(raiz);
        //System.out.println();
    }
    
    public void cargarVector(){//lo valores que estan en el arraylist se copiaran en el vector
        vector=new int[listaArbol.size()]; //instanciamos el vector
        
        for(int i=0; i<listaArbol.size(); i++){
            vector[i]=listaArbol.get(i); //se graba el valor en el vector
        }
        ordenarVector();
    }
    
    public void ordenarVector(){ //ordenamos el vector
       int aux=0;
        for(int y=0; y < vector.length-1; y++){
            
           for(int x=0; x < vector.length-1; x++){
               if(vector[x]>vector[x+1]){
                 aux=vector[x];
                 vector[x]= vector[x+1];
                 vector[x+1]= aux;
               }
           }
        }
        //se muestra el vector ordenado
        for(int i=0; i<vector.length; i++){ //se imprime el vector ordenado
             System.out.println(vector[i]);
         }
   
    }

    public void informar(){
        System.out.println("----------------------------------------");
        System.out.println("Punto B cumplido se muestra el recorrido en los 3 ordenes ");
        System.out.println("----PRE ORDEN----");
        recorrerPre();
        System.out.println("---ENTRE ORDEN---");
        imprimirEntre() ;
        System.out.println("---POST ORDEN---");
        imprimirPost();
        System.out.println("----------------------------------------");
        System.out.println("Punto C cumplido,Se borra el menor y mayor valor del arbol");
        borrarMenor();
        borrarMayor();
        System.out.println("se imprime sin los borrados (recorrido entre orden)");
        imprimirEntre();
        System.out.println("-----------------------------------------");
        System.out.println("EXTRA MUESTRA EL MENOR Y MAYOR VALOR DENTRO DEL ARBOL (despues del borrado)");
        mayorValor();
        menorValor();
        System.out.println("--------------------------------------------------");
        System.out.println("Punto D cumplido, se informa altura de arbol y niveles de los datos");
        int alt= retornarAltura();
        System.out.println("");
        System.out.println("La altura del arbol es : "+ alt);
        System.out.println("----------------------------------------");
        grabarLista();
        System.out.println("Pasamos los valores del arbol a la lista y luego a un vector");
        System.out.println("Se muestra el vector ordenado");
        cargarVector();
    }
    
    public static void main(String[] args) {
       CopiaArbol  arbol = new CopiaArbol ();
       arbol.cargarArbol();
       arbol.informar();
       
    }
}
