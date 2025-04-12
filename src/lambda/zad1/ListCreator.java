package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ListCreator<T> {
    List<T> list;

    public ListCreator(List<T> destinations) {
        this.list=destinations;
    }

    public static <T> ListCreator<T> collectFrom(List<T> destinations) {
        return new ListCreator<T>(destinations);
    }
    
    public ListCreator<T> when(Function<T,Boolean> funct) {
        List<T> newList = new ArrayList<>();
            for(T element: list){
                if(funct.apply(element)){
                    newList.add(element);
                }
            }
            return new ListCreator<>(newList);
    }

    public <U> List<U> mapEvery(Function<T, U> funct){
        List<U> newList = new ArrayList<>();
        for(T element: list){
            newList.add(funct.apply(element));
        }
        return newList;
    }
}

