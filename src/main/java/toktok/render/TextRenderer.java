package toktok.render;

import toktok.core.ActionResult;

/**
 * @author Jan Kischkel
 */
public interface TextRenderer {

    default ActionResult text(String text) {
        return ActionResult.create(text);
    }

    default ActionResult text(int status, String text) {
        return ActionResult.create(status, text);
    }
}
