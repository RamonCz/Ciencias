import java.util.Random;

public class TreeTest{

	public static void main (String[] args){
        Lista<Integer> test = new Lista<Integer>();
        ArbolAVL<Integer> ab = new ArbolAVL<Integer>();

//         Prueba de arbol binario vacio
        System.out.println("ArbolAVL ");
        System.out.println("es vacio? " + ab.esVacio());
        System.out.println("inorden: " + test.toString());
        System.out.println("altura : " +ab.altura() + "\n" );

//         Añadimos un elemento al arbol
        System.out.println("Añardir " + "1" + " al arbol");
        ab.agrega(1);
        System.out.println("es vacio? " + ab.esVacio());
    //    test = ab.inOrden
				System.out.println(ab.toString());
		  	System.out.println("Añadir" + " 2:");
        ab.agrega(2);
        System.out.println("Añadir" + " 0:");
        ab.agrega(0);
        System.out.println("Añadir" + " 10:");
        ab.agrega(10);
        System.out.println("Añadir" + " 12:");
        ab.agrega(12);
        System.out.println("Añadir" + "-12:");
        ab.agrega(-12);
        System.out.println("Añadir" + " -4:");
        ab.agrega(-4);
        System.out.println("Añadir" + " 5:");
        ab.agrega(5);
        test = ab.inOrden();
        System.out.println(ab.toString());
        System.out.println(test.toString() + " -. La altura del arbol es:"+ ab.altura() + "\n");
				System.out.println("*********************************");
//      Vemos si contiene un elemento
        System.out.println("Contiene al elemento 12?");
        System.out.println(ab.contiene(12));
        System.out.println("Contiene al elemento -1?");
        System.out.println(ab.contiene(-1));
        System.out.println("Contiene al elemento 0?");
        System.out.println(ab.contiene(0));
        System.out.println("Contiene al elemento 5?");
        System.out.println(ab.contiene(5) + "");

      	// prubas de elimina
				System.out.println("************************************");
        System.out.println("Prueba para eliminar con 2");
        ab.elimina(-12);
        ab.agrega(-5);
        ab.agrega(-2);
        ab.agrega(-3);
        ab.agrega(-1);
        ab.agrega(0);
        ab.agrega(3);
        ab.agrega(7);
        ab.agrega(2);
        ab.agrega(4);
        ab.agrega(6);
        ab.agrega(8);
        System.out.println("El arbol esta lleno, vamos a eliminar nodos clave");
        Lista<Integer> l1 = ab.inOrden();
        System.out.println("Inorden correcto: " + l1.toString());
        System.out.println(ab.toString());
        System.out.println("Eliminar -1");
        ab.elimina(-1);
        System.out.println(ab.toString());
        System.out.println("Eliminar la raiz " + ab.raiz() );
        ab.elimina(ab.raiz().elemento);
        System.out.println(ab.toString());
        System.out.println("Eliminar a -4 !!");
        ab.elimina(-4);
        l1 = ab.inOrden();
        System.out.println(ab.toString() + "");
        System.out.println("Inorden correcto: " + l1.toString() + "\n");
	}

}
