package com.teanlime.domain.api.util.streams;

import com.annimon.stream.Collector;
import com.annimon.stream.function.BiConsumer;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Supplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImmutableCollectors {

    private ImmutableCollectors() {
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return new ImmutableCollector<>(
                ArrayList::new,
                new BiConsumer<List<T>, T>() {
                    @Override
                    public void accept(List<T> t, T u) {
                        t.add(u);
                    }
                },
                Collections::unmodifiableList
        );
    }

    private static final class ImmutableCollector<T, A, R> implements Collector<T, A, R> {

        private final BiConsumer<A, T> accumulator;
        private final Function<A, R> finisher;
        private final Supplier<A> supplier;

        public ImmutableCollector(Supplier<A> supplier,
                                  BiConsumer<A, T> accumulator,
                                  Function<A, R> finisher) {
            this.accumulator = accumulator;
            this.supplier = supplier;
            this.finisher = finisher;
        }

        @Override
        public BiConsumer<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public Function<A, R> finisher() {
            return finisher;
        }

        @Override
        public Supplier<A> supplier() {
            return supplier;
        }

    }
}
