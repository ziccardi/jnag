/*
 * Copyright (c) 2014 Frederico Campos & Massimiliano Ziccardi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yasmin.core.config.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for configuration tokens.
 * 
 * @author Massimiliano Ziccardi
 */
public class TokenFactory {
	/**
	 * List of all known token parsers...
	 */
	private static List<IYasminConfigToken> knownTokens = null;

	/**
	 * Token Factory can't be built...
	 */
	private TokenFactory() {

	}

	/**
	 * Initializes the list of known token parsers..
	 */
	private static synchronized void init() {
		if (knownTokens == null) {
			knownTokens = new ArrayList<IYasminConfigToken>();
			knownTokens.add(new EmptyLineToken());
			knownTokens.add(new CommentToken());
			knownTokens.add(new KeyValueToken());
		}
	}

	/**
	 * Produce the token relative to the given configuration line
	 * 
	 * @param line
	 *            The configuration line
	 * @return The token object or null if the line can't be recognized
	 */
	public static IYasminConfigToken produceToken(String line) {
		init();
		for (IYasminConfigToken token : knownTokens) {
			IYasminConfigToken parsedToken = token.parse(line);
			if (parsedToken != null) {
				return parsedToken;
			}
		}

		return null;
	}
}
