public class MaxHeap <T extends Comparable<T>> extends Heap<T>{

    public MaxHeap(Lista<T> c){
	super(c);
    }
    
    public void reordena(int indice){
	super.reordena(indice,false);
    }

}
