package toktok.json;

import java.util.Map;

/**
 * @author Jan Kischkel
 */
public interface Json {

    String render(Object object);

    String render(Object[] objets);

    String render(Map<? ,?> objects);

    String render(Iterable<?> objects);
}
