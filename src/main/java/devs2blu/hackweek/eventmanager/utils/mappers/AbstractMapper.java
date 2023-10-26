package devs2blu.hackweek.eventmanager.utils.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

/*
 * T: Entity
 * U: Request
 * V: Response
 */
@RequiredArgsConstructor
public abstract class AbstractMapper <T, U, V> {
    protected final ModelMapper mapper;

    public T toEntity(U u) {
        return mapper.map(u, getEntityClass());
    }

    public V toResponse(T t) {
        return mapper.map(t, getResponseClass());
    }

    public U toRequest(T t) {
        return mapper.map(t, getRequestClass());
    }

    public T updateEntity(T t, U u) {
        mapper.map(u, t);
        return t;
    }

    public List<V> toResponseList(List<T> t) {
        return t.stream().map(this::toResponse).toList();
    }

    abstract Class<T> getEntityClass();
    abstract Class<U> getRequestClass();
    abstract Class<V> getResponseClass();
}
