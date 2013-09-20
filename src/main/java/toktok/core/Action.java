package toktok.core;

import toktok.http.Request;

import java.util.function.Function;

public interface Action extends Function<Request, String> {}
