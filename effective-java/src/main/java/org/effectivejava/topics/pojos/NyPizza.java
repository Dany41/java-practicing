package org.effectivejava.topics.pojos;

import java.util.Objects;

public class NyPizza extends Pizza {
    public enum Size { SMALL, MEDIUM, LARGE }
    private final Size size;

    public static class NyBuilder extends Pizza.Builder<NyBuilder> {
        private final Size size;

        public NyBuilder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override public NyPizza build() {
            return new NyPizza(this);
        }

        @Override protected NyBuilder self() { return this; }
    }

    private NyPizza(NyBuilder builder) {
        super(builder);
        size = builder.size;
    }

    @Override public String toString() {
        return "New York Pizza with " + toppings;
    }
}
