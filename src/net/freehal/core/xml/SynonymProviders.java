package net.freehal.core.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.freehal.core.lang.ImplicitLanguageImplMap;
import net.freehal.core.lang.LanguageImplMap;
import net.freehal.core.util.Factory;

/**
 * An utility class for holding the currently used {@link SynonymProvider}s.
 * 
 * @author "Tobias Schulz"
 */
public class SynonymProviders {

	private static LanguageImplMap<CompositeSynonymProvider> provider = new ImplicitLanguageImplMap<CompositeSynonymProvider>(
			new Factory<CompositeSynonymProvider>() {
				@Override
				public CompositeSynonymProvider newInstance(String... params) {
					return new CompositeSynonymProvider();
				}
			});

	private SynonymProviders() {}

	/**
	 * Get the currently used synonym provider.
	 * 
	 * @return an instance of a class implementing the {@link SynonymProvider}
	 *         interface
	 */
	public static SynonymProvider getSynonymProvider() {
		return provider.getCurrent();
	}

	/**
	 * Set the synonym provider to use; run at the beginning of your code!
	 * 
	 * @param provider
	 *        the synonym provider to set
	 */
	public static void addSynonymProvider(SynonymProvider provider) {
		if (!SynonymProviders.provider.hasCurrent()) {
			SynonymProviders.provider.setCurrent(new CompositeSynonymProvider());
		}
		SynonymProviders.provider.getCurrent().add(provider);
	}

	public static class CompositeSynonymProvider implements SynonymProvider {

		List<SynonymProvider> providers = new ArrayList<SynonymProvider>();

		@Override
		public Collection<String> getSynonyms(String text) {
			List<String> words = new ArrayList<String>();
			for (SynonymProvider provider : providers) {
				Collection<String> result = provider.getSynonyms(text);
				if (result != null)
					words.addAll(result);
			}
			return words;
		}

		@Override
		public Collection<Word> getSynonyms(Word word) {
			List<Word> words = new ArrayList<Word>();
			for (SynonymProvider provider : providers) {
				Collection<Word> result = provider.getSynonyms(word);
				if (result != null)
					words.addAll(result);
			}
			return words;
		}

		public void add(SynonymProvider provider) {
			providers.add(provider);
		}
	}

	public static class NullSynonymProvider implements SynonymProvider {

		@Override
		public Collection<String> getSynonyms(String text) {
			List<String> words = new ArrayList<String>();
			return words;
		}

		@Override
		public Collection<Word> getSynonyms(Word word) {
			List<Word> words = new ArrayList<Word>();
			return words;
		}
	}
}
