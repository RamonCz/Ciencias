public class TreeTest{

	public static void main (String[] args){
        Lista<Integer> test = new Lista<Integer>();
        ArbolBinarioBusqueda<Integer> ab = new ArbolBinarioBusqueda<Integer>();

//         Prueba de arbol binario vacio
        System.out.println("Arbol Binario Vacío: ");
        System.out.println(ab.toString() +	 ab.esVacio() + test.toString() + "\n" );

//         Añadimos un elemento al arbol
        System.out.println("Añardir " + "1" + " al arbol");
        ab.agrega(1);
				System.out.println(ab.esVacio());
        System.out.println("Añadir" + " 2:");
        ab.agrega(2);//2
        System.out.println("Añadir" + " 0:");
        ab.agrega(0);
				System.out.println("Añadir" + " 10:");
				ab.agrega(10);
        System.out.println("Añadir" + "-12:");
        ab.agrega(-12);
        System.out.println("Añadir" + " -4:");
        ab.agrega(-4);
        System.out.println("Añadir" + " 5:");
        ab.agrega(5);
				System.out.println(ab.toString());

//      Vemos si contiene un elemento
        System.out.println("Contiene al elemento 12?");
        System.out.println(ab.contiene(12));
        System.out.println("Contiene al elemento -1?");
        System.out.println(ab.contiene(-1));
        System.out.println("Contiene al elemento 0?");
        System.out.println(ab.contiene(0));
        System.out.println("Contiene al elemento 5?");
        System.out.println(ab.contiene(5) + " \n");

//      Eliminamos un elemento del arbol
        System.out.println("Eliminar a 5:");
        ab.elimina(5);
				System.out.println(ab.toString());
        System.out.println("Eliminar a 2:");
        ab.elimina(2);
				System.out.println(ab.toString());
        System.out.println("Eliminar a 0:");
        ab.elimina(0);
				System.out.println(ab.toString());
				System.out.println();

//         Sacamos el preorden del arbol:
        System.out.println("hacemos preorden al arbol binario. Nos da una lista");
        Lista<Integer> preorden = ab.preOrden();
        System.out.println(preorden.toString());
        System.out.println("hacemos inorden al arbol binario. Nos da una lista");
        Lista<Integer> inorden = ab.inOrden();
        System.out.println(inorden.toString());
        System.out.println("hacemos postorden al arbol binario. Nos da una lista");
        Lista<Integer> postorden = ab.postOrden();
        System.out.println(postorden.toString() + "\n");

//         Hacemos rotaciones para ver que si funciona
        System.out.println(ab.toString());
        System.out.println("Hacemos rotacion derecha sobre la raiz");
      	ab.giraDerecha(ab.raiz());
        System.out.println(ab.toString());
        System.out.println("Hacemos rotacion izquierda sobre la raiz");
        ab.giraIzquierda(ab.raiz());
        System.out.println(ab.toString() + "\n");


	}

}
