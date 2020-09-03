public class MinHeap<T extends Comparable<T>> extends Heap<T>{

    public MinHeap(Lista<T> c){
	     super(c);
    }

    public void reordena(int indice){
	super.reordena(indice,true);
    }
}
