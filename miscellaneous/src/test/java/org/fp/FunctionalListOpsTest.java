package org.fp;

import org.fp.collections.FunctionalList;
import org.junit.jupiter.api.Test;

import static org.assertions.AssertionResolver.assertThat;

class FunctionalListOpsTest {

    private final FunctionalList<Integer> functionalList = FunctionalList.create(-1, 2, 3, 4);

    @Test
    void sum() {
        assertThat(FunctionalListOps.sum(functionalList)).isEqualTo(8);
    }

    @Test
    void append() {
        assertThat(FunctionalListOps.append(FunctionalList.create(0), functionalList))
                .contains(0);
    }

    @Test
    void foldLeft() {
        assertThat(FunctionalListOps.foldLeft(functionalList, 0, (e, a) -> e - a)).isEqualTo(4);
    }

    @Test
    void foldRight() {
        assertThat(FunctionalListOps.foldRight(functionalList, 0, (e, a) -> e - a)).isEqualTo(-4);
    }

    @Test
    void setHead() {
        assertThat(FunctionalListOps.setHead(0, functionalList)).isEqualTo(FunctionalList.create(0, -1, 2, 3, 4));
    }

    @Test
    void product() {
        assertThat(FunctionalListOps.product(functionalList.map(Double::valueOf))).isEqualTo(-24);
    }

    @Test
    void length() {
        assertThat(FunctionalListOps.length(functionalList)).isEqualTo(4);
    }
}