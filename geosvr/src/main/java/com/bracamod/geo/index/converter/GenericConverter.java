package com.bracamod.geo.index.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 
 * @author daniel
 *
 * @param <I>
 * @param <O>
 */
public interface GenericConverter<I, O> extends Function<I, O> {

	/**
	 * 
	 * @param input
	 * @return
	 */
    default O convert(final I input) {
        O output = null;
        if (input != null) {
            output = this.apply(input);
        }
        return output;
    }

    /**
     * 
     * @param input
     * @return
     */
    default List<O> convert(final List<I> input) {
        List<O> output = new ArrayList<>();
        if (input != null) {
            output = input.stream().map(this::apply).collect(Collectors.toList());
        }
        return output;
    }
}