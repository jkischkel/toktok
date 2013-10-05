package toktok.core;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * @author Jan Kischkel
 */
public final class ActionResult {

    private final Integer status;

    private final String content;

    private final Map<String, String> headers;

    private ActionResult(
            Integer status,
            String content,
            Map<String, String> headers) {
        this.status = status;
        this.content = content;
        this.headers = Collections.unmodifiableMap(headers);
    }

    public Integer getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public Map<String, String> headers() {
        return headers;
    }

    public ActionResult withHeader(String name, String value) {
        Map<String, String> copy = Maps.newHashMap(headers());
        copy.put(name, value);

        return new ActionResult(status, content, copy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionResult other = (ActionResult) o;
        return Objects.equal(content, other.content)
            && Objects.equal(status, other.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status, content);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("status", status)
                .add("content", content)
                .toString();
    }

    public static ActionResult create(String content) {
        return create(200, content);
    }

    public static ActionResult create(int status, String content) {
        return new ActionResult(status, content, Collections.emptyMap());
    }
}
