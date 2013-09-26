package toktok.core;

import com.google.common.base.Objects;

/**
 * @author Jan Kischkel
 */
public final class ActionResult {

    private final Integer status;

    private final String content;

    private ActionResult(Integer status, String content) {
        this.status = status;
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public String getContent() {
        return content;
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
        return new ActionResult(status, content);
    }
}
