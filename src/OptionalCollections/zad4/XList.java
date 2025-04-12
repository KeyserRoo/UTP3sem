package zad4;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XList<T> extends ArrayList<T> {
    public XList(T... elements) {
        super(Arrays.asList(elements));
    }

    public XList(Collection<T> elements) {
        super(elements);
    }

    public static <T> XList<T> of(T... elements) {
        return new XList<>(elements);
    }

    public static <T> XList<T> of(Collection<T> elements) {
        return new XList<>(elements);
    }

    public static XList<String> charsOf(String s) {//
        return XList.of(s.chars().mapToObj(ch -> String.valueOf((char) ch)).collect(Collectors.toList()));
    }

    public static XList<String> tokensOf(String s, String str) {
        return XList.of(s.split(str));
    }

    public static XList<String> tokensOf(String s) {
        return tokensOf(s, " ");
    }

    public XList<T> union(Collection<T> c) {
        return Stream.concat(stream(), c.stream())
                .collect(Collectors.toCollection(XList::new));
    }

    public XList<T> union(T... elements) {
        return union(Arrays.asList(elements));
    }

    public XList<T> diff(Collection<T> collection) {
        return stream()
                .filter(element -> !collection.contains(element))
                .collect(Collectors.toCollection(XList::new));
    }

    public XList<T> unique() {
        return XList.of(new LinkedHashSet<>(this));
    }

    private <U> XList<XList<U>> combine(List<XList<U>> src) {
        if (src.isEmpty()) {
            XList<XList<U>> list = new XList();
            list.add(new XList());
            return list;
        }

        List<XList<U>> subList = src.subList(0, src.size() - 1);
        XList<XList<U>> temp = combine(subList);
        XList<XList<U>> result = new XList<>();

        for (U element : src.get(src.size() - 1)) {
            for (XList<U> el : temp) {
                XList<U> newEl = new XList<>(el);
                newEl.add(element);
                result.add(newEl);
            }
        }

        return result;
    }

    public <U> XList<XList<U>> combine() {
        XList<XList<U>> src = new XList();
        for (List<U> t : (List<List<U>>) this) {
            src.add(XList.of(t));
        }
        return this.combine(src);
    }

    public <U> XList<U> collect(Function<T, U> function) {
        return XList.of(this.stream()
                .map(function)
                .collect(Collectors.toList()));
    }

    public String join(String s) {
        return this.stream()
                .map(Object::toString)
                .collect(Collectors.joining(s));
    }

    public String join() {
        return join("");
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < this.size(); i++) {
            consumer.accept(this.get(i), i);
        }
    }
}
