package toktok.render;

import toktok.core.ActionResult;

/**
 * @author Jan Kischkel
 */
public interface NothingRenderer {

    default ActionResult nothing() {
        return ActionResult.create(null);
    }

    default ActionResult nothing(int status) {
        return ActionResult.create(status, null);
    }
}
