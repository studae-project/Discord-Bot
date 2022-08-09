package br.com.studae.commons.command.collection;

public record Triple<A, B, C>(
  A a,
  B b,
  C c
) {
    public static <A, B, C> Triple<A, B, C> of(A a, B b, C c) {
        return new Triple<>(a, b, c);
    }
}
