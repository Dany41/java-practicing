package org.assertions;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.fp.collections.FunctionalList;

public class FunctionalListAssertion<T> extends AbstractAssert<FunctionalListAssertion<T>, FunctionalList<T>> {
    protected FunctionalListAssertion(FunctionalList<T> functionalList, Class<?> selfType) {
        super(functionalList, selfType);
    }

    public static <T> FunctionalListAssertion<T> assertThat(FunctionalList<T> functionalList) {
        return new FunctionalListAssertion<>(functionalList, FunctionalListAssertion.class);
    }

    public FunctionalListAssertion<T> hasLength(int expectedLength) {
        Assertions.assertThat(this.actual.length()).isEqualTo(expectedLength);
        return this;
    }

}
