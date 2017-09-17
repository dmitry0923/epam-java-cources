package com.epam.university.java.core.task014;

import static org.junit.Assert.assertEquals;

import com.epam.university.java.core.helper.TestHelper;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class Task014Test {

    private Task014 instance;
    private VampireNumberFactory factory;

    @Before
    public void setUp() throws Exception {
        instance = TestHelper.getInstance(Task014.class);
        factory = TestHelper.getInstance(VampireNumberFactory.class);
    }

    @Test
    public void checkNumbers() throws Exception {
        final List<VampireNumber> targetCollection = Arrays.asList(
            factory.newInstance(1260, 21, 60),
            factory.newInstance(1395, 15, 93),
            factory.newInstance(1435, 41, 35),
            factory.newInstance(1530, 51, 30),
            factory.newInstance(1827, 21, 87),
            factory.newInstance(2187, 27, 81),
            factory.newInstance(6880, 86, 80)
        );
        final Collection<VampireNumber> vampireNumbers = instance.getVampireNumbers();
        Set<VampireNumber> vampireSet = new HashSet<>();
        vampireSet.addAll(vampireNumbers);
        vampireSet.addAll(targetCollection);
        assertEquals("Incorrect vampire numbers collection",
            vampireSet.size(),
            7
        );
    }
}