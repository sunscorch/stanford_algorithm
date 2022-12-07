package course2.week3.heap;

import course1.week3.heapSort.HeapSort;

import java.util.Arrays;

public class BinaryHeap<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 1;
    private int currentSize;
    public T[] arr;

    BinaryHeap(){
        currentSize = 0;
        arr = (T[]) new Comparable[DEFAULT_CAPACITY];
    }
    public static final <T> void swap (T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    //
    private void enlargeArray(int newSize) {
        T[] old = arr;
        arr = (T[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++) {
            arr[i] = old[i];
        }
    }

    //add at the tail, then pop up

    public void insert(T t) {
        if (currentSize == arr.length - 1) {
            // 如果当前元素个数为数组长度-1，则扩容
            enlargeArray(arr.length * 2 + 1);
        }
        int hole = ++currentSize;
        arr[hole] = t;
        T holeV = arr[hole];
        int parentIdx = hole /2;
        while(parentIdx >= 1 && arr[parentIdx].compareTo(arr[hole]) > 0){
            swap(arr,parentIdx, hole);
            hole = parentIdx;
            parentIdx = hole/2;
        }
    }
    public T pop(){
        T res = arr[0];
        deleteByIdx(1);
        return res;
    }
    //delete a element with index(idx)
    //swap it with last element
    public void deleteByIdx(int idx){
        if(idx <= 0 || idx > currentSize) throw new RuntimeException("invalid idx for delete");
        arr[idx] = arr[currentSize--];
        bubbleDown(idx);
    }

    private void bubbleDown(int parentIdx){

        while(parentIdx <= currentSize){

            int maxIdx = parentIdx;
            int lIdx = 2 * parentIdx ;
            int rIdx = 2 * parentIdx + 1;


            if(lIdx <= currentSize && arr[maxIdx].compareTo(arr[lIdx]) > 0 ){
                maxIdx = lIdx;
            }

            if(rIdx <= currentSize && arr[maxIdx].compareTo(arr[rIdx]) > 0 ){
                maxIdx = rIdx;
            }

            //id parent is the smallest value, we break
            if(maxIdx != lIdx && maxIdx != rIdx){
                break;
            }else{
                swap(arr, maxIdx, parentIdx);
                parentIdx = maxIdx;
            }

        }
    }



    public T first() throws Exception {
        if(currentSize == 0) throw new Exception("heap is empty");
        return arr[1];
    }

    public T last() throws Exception {
        if(currentSize == 0) throw new Exception("heap is empty");
        return arr[currentSize];
    }

    public void clear(){
        for(int i = 1;i <= currentSize; i++){
            arr[i] = null;
        }
        currentSize = 0;
    }

    public void print(){
       T[] res = Arrays.copyOfRange(arr,1,currentSize+1);
        System.out.println(Arrays.toString(res));
    }




    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        /*
        heap.insert(2);
        heap.insert(1);
        heap.insert(0);

         */
        //System.out.println(Arrays.toString(heap.arr));

        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        heap.print();
        heap.deleteByIdx(1);
        heap.print();
    }

}
