package toktok.core;

import toktok.render.JsonRenderer;
import toktok.render.NothingRenderer;
import toktok.render.TextRenderer;

/**
 * @author Jan Kischkel
 */
public interface JsonController
        extends Controller,
                TextRenderer,
                JsonRenderer,
                NothingRenderer {}
