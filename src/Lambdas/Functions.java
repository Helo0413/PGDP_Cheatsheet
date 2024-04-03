package Lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Functions {



    public static <A,B> List<B> map(List<A> toMap, Function<A,B> function){
        var list=new ArrayList<B>();

        for(A mapping : toMap){
            list.add(function.apply(mapping));
        }

        return list;
    }
    public static List<Integer> square(List<Integer> toSquare){
        return map(toSquare, (x) -> x*x);
    }


    public static <A> List<String> toString(List<A> toString){
        return map(toString, (x) -> x + "");
    }
    public static <A> List<A> filter(List<A> toFilter, Predicate<A> filter){
        var list = new ArrayList<A>();
        for(A filtering : toFilter){
            if(filter.test(filtering)){
                list.add(filtering);
            }
        }
        return list;
    }

    public static <A> List<A> filterAny(List<A> toFilter,Predicate<A> filter1,Predicate<A> filter2){
        return filter(toFilter, (x) -> filter1.test(x) || filter2.test(x));
    }

    public static List<Integer> multiple2or7(List<Integer>numbers){
        return filterAny(numbers, (x) -> x % 2 == 0, (x) -> x % 7 == 0);
    }
}
