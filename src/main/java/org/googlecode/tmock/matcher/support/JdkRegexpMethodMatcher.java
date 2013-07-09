
package org.googlecode.tmock.matcher.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author zhongfeng
 *
 */
@SuppressWarnings("serial")
public class JdkRegexpMethodMatcher extends AbstractRegexpMethodMatcher {

	/**
	 * Compiled form of the patterns.
	 */
	private Pattern[] compiledPatterns = new Pattern[0];

	/**
	 * Compiled form of the exclusion patterns.
	 */
	private Pattern[] compiledExclusionPatterns = new Pattern[0];


	/**
	 * Initialize {@link Pattern Patterns} from the supplied {@code String[]}.
	 */
	@Override
	protected void initPatternRepresentation(String[] patterns) throws PatternSyntaxException {
		this.compiledPatterns = compilePatterns(patterns);
	}

	/**
	 * Initialize exclusion {@link Pattern Patterns} from the supplied {@code String[]}.
	 */
	@Override
	protected void initExcludedPatternRepresentation(String[] excludedPatterns) throws PatternSyntaxException {
		this.compiledExclusionPatterns = compilePatterns(excludedPatterns);
	}

	/**
	 * Returns {@code true} if the {@link Pattern} at index {@code patternIndex}
	 * matches the supplied candidate {@code String}.
	 */
	@Override
	protected boolean matches(String pattern, int patternIndex) {
		Matcher matcher = this.compiledPatterns[patternIndex].matcher(pattern);
		return matcher.matches();
	}

	/**
	 * Returns {@code true} if the exclusion {@link Pattern} at index {@code patternIndex}
	 * matches the supplied candidate {@code String}.
	 */
	@Override
	protected boolean matchesExclusion(String candidate, int patternIndex) {
		Matcher matcher = this.compiledExclusionPatterns[patternIndex].matcher(candidate);
		return matcher.matches();
	}


	/**
	 * Compiles the supplied {@code String[]} into an array of
	 * {@link Pattern} objects and returns that array.
	 */
	private Pattern[] compilePatterns(String[] source) throws PatternSyntaxException {
		Pattern[] destination = new Pattern[source.length];
		for (int i = 0; i < source.length; i++) {
			destination[i] = Pattern.compile(source[i]);
		}
		return destination;
	}

}
