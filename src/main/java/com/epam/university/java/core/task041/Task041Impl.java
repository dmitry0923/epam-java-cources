package com.epam.university.java.core.task041;

import java.util.Collection;
import java.util.Optional;

/**
 * Author Dmitry Novikov 22-Oct-20.
 */
public class Task041Impl implements Task041 {
    /**
     * Perform <p>Create</p> operation with <code>collection</code> of entities.
     * Tip: Pay attention that id of entity is unique.
     *
     * @param collection in which should create new entity.
     * @param value      for creation new entity.
     * @return created entity.
     */
    @Override
    public Entity create(Collection<Entity> collection, String value) {
        Optional<Collection<Entity>> optional = Optional.ofNullable(collection);
        Optional<String> optional1 = Optional.ofNullable(value);
        if (optional.isEmpty() || optional1.isEmpty()
                || optional1.get().isBlank()) {
            throw new IllegalArgumentException();
        }

        Entity entity = new EntityImpl(value);
        collection.add(entity);

        return entity;
    }

    /**
     * Perform <p>Read</p> operation with <code>collection</code> of entities.
     *
     * @param collection from which should read entity.
     * @param entity     to be read.
     * @return read entity.
     */
    @Override
    public Entity read(Collection<Entity> collection, Entity entity) {
        Optional<Collection<Entity>> optional = Optional.ofNullable(collection);
        Optional<Entity> optional1 = Optional.ofNullable(entity);
        if (optional.isEmpty() || optional1.isEmpty()) {
            throw new IllegalArgumentException();
        }

        for (Entity e : collection
        ) {
            if (e.equals(entity)) {
                return e;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return null;
    }

    /**
     * Perform <p>Update</p> operation with <code>collection</code> of entities.
     *
     * @param collection in which have to update entity object.
     * @param entity     to be updated.
     * @param value      that have to be changed in entity object.
     */
    @Override
    public void update(Collection<Entity> collection, Entity entity, String value) {
        Optional<Collection<Entity>> optional = Optional.ofNullable(collection);
        Optional<Entity> optional1 = Optional.ofNullable(entity);
        Optional<String> optional2 = Optional.ofNullable(value);
        if (optional.isEmpty() || optional1.isEmpty()
                || optional2.isEmpty() || optional2.get().isBlank()) {
            throw new IllegalArgumentException();
        }

        EntityImpl entityForUpdate = null;

        for (Entity e : collection
        ) {
            if (e.equals(entity)) {
                entityForUpdate = (EntityImpl) e;
                entityForUpdate.setValue(value);
            }
        }
        if (entityForUpdate == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Perform <p>Delete</p> operation with <code>collection</code> of entities.
     *
     * @param collection from which have to delete object.
     * @param entity     to be deleted.
     */
    @Override
    public void delete(Collection<Entity> collection, Entity entity) {
        Optional<Collection<Entity>> optional = Optional.ofNullable(collection);
        Optional<Entity> optional1 = Optional.ofNullable(entity);
        if (optional.isEmpty() || optional1.isEmpty()) {
            throw new IllegalArgumentException();
        }
        EntityImpl entityToDelete = null;
        for (Entity e : collection
        ) {
            if (e.equals(entity)) {
                entityToDelete = (EntityImpl) e;
            }
        }

        if (entityToDelete != null) {
            collection.remove(entityToDelete);
        }
    }
}
