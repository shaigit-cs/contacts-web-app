package com.wooky.core;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StaticFieldsTest {

    @Test
    public void checkIfSingletonHasSameHash() {

        StaticFields instance1 = StaticFields.getInstance();
        StaticFields instance2 = StaticFields.getInstance();

        assertThat(instance1).hasSameHashCodeAs(instance2);
    }
}
