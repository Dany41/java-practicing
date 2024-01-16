package org.assertions;

import org.assertj.core.api.Assertions;
import org.fp.collections.FunctionalList;

public class AssertionResolver extends Assertions {
    public static <T> FunctionalListAssertion<T> assertThat(FunctionalList<T> functionalList) {
        return new FunctionalListAssertion<>(functionalList, FunctionalListAssertion.class);
    }
}
