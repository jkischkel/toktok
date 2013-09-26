package toktok.render;

import toktok.core.ActionResult;
import toktok.json.Json;
import toktok.json.JsonFactory;

/**
 * @author Jan Kischkel
 */
public interface JsonRenderer {

    Json json = JsonFactory.jackson();

    default ActionResult json(Object o) {
        return ActionResult.create(json.render(o));
    }

    default ActionResult json(int status, Object o) {
        return ActionResult.create(status, json.render(o));
    }
}
