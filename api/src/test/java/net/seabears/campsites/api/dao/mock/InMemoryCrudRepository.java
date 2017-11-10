package net.seabears.campsites.api.dao.mock;

import org.springframework.data.repository.CrudRepository;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public abstract class InMemoryCrudRepository<T, K> implements CrudRepository<T, K> {
    private final Map<K, T> data = new HashMap<>();

    protected InMemoryCrudRepository() {
        this(emptyList());
    }

    protected InMemoryCrudRepository(final Iterable<T> initialData) {
        initialData.forEach(this::save);
    }

    protected abstract BiConsumer<T, K> idSetter();

    protected abstract Function<T, K> idGetter();

    protected abstract K newId();

    @Override
    public <S extends T> S save(final S entity) {
        K id = idGetter().apply(entity);
        if (id == null) {
            id = newId();
            idSetter().accept(entity, id);
        }
        data.put(id, entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(final Iterable<S> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public boolean existsById(final K id) {
        return findById(id).isPresent();
    }

    @Override
    public Optional<T> findById(final K id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Iterable<T> findAllById(final Iterable<K> ids) {
        final LinkedList<T> results = new LinkedList<>();
        ids.forEach(id -> findById(id).ifPresent(results::add));
        return results;
    }

    @Override
    public Iterable<T> findAll() {
        return data.values();
    }

    protected <K> Iterable<T> findAll(final Function<T, K> getter, final K key) {
        return data.values().stream()
                .filter(entity -> Objects.equals(getter.apply(entity), key))
                .collect(toList());
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public void deleteById(final K id) {
        data.remove(id);
    }

    @Override
    public void delete(final T entity) {
        data.remove(idGetter().apply(entity));
    }

    @Override
    public void deleteAll(final Iterable<? extends T> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        data.clear();
    }
}
