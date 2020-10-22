package com.epam.university.java.core.task041;

import com.epam.university.java.core.helper.TestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class Task041Test {

    private Task041 instance;
    private Collection<Entity> targetCollection;

    @Before
    public void setUp() throws Exception {
        instance = TestHelper.getInstance(getClass());
        targetCollection = new ArrayList<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithNullArguments() {
        instance.create(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadWithNullArguments() {
        instance.read(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithNullArguments() {
        instance.update(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteWithNullArguments() {
        instance.delete(null, null);
    }

    @Test
    public void testCreate() {
        Entity entity = instance.create(targetCollection, "Another Entity");
        assertEquals(entity.getId(), targetCollection.size() - 1);
    }

    @Test
    public void testRead() {
        Entity entity = instance.create(targetCollection, "First Entity");
        assertEquals(entity.getId(), instance.read(targetCollection, entity).getId());
    }

    @Test
    public void testUpdate() {
        Entity entity = instance.create(targetCollection, "Another Entity"); // 2 Another Entity
        int currentId = entity.getId(); // 2
        instance.update(targetCollection, entity, "Changed Value"); // 2 Changed value
        List<Entity> current = new ArrayList<>(targetCollection); //
        assertEquals("Changed Value", current.get(currentId).getValue()); // 2 instead of 0
    }

    @Test
    public void testDelete() {
        Collection<Entity> current = new ArrayList<>(targetCollection);
        Entity entity = instance.create(current, "Another Entity");

        Collection<Entity> tmpCollection = new ArrayList<>(current);
        Entity anotherEntity = instance.create(current, "Another One Entity");

        instance.delete(current, anotherEntity);
        assertEquals(tmpCollection, current);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithNoEntity() {
        Entity entity = instance.create(targetCollection, "Another Entity");
        instance.delete(targetCollection, entity);
        instance.update(targetCollection, entity, "Changed Value");
    }
}