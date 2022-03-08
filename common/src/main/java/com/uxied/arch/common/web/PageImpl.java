package com.uxied.arch.common.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageImpl<T> {
    private boolean last;
    private boolean first;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    List<T> content;

    public static <T, R> List<R> map(List<T> content, Function<? super T, ? extends R> converter) {
        return content.stream().map(converter).collect(Collectors.toList());
    }
}
