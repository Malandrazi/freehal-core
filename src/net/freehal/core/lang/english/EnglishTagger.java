package net.freehal.core.lang.english;

import java.util.HashSet;
import java.util.Set;

import net.freehal.core.pos.AbstractTagger;
import net.freehal.core.pos.Tagger2012;
import net.freehal.core.pos.TaggerCache;
import net.freehal.core.util.RegexUtils;

public class EnglishTagger extends Tagger2012 implements AbstractTagger {

	Set<String> builtinEntityEnds = new HashSet<String>();
	Set<String> builtinMaleNames = new HashSet<String>();
	Set<String> builtinFemaleNames = new HashSet<String>();
	Set<String> customNames = new HashSet<String>();

	public EnglishTagger(TaggerCache container) {
		super(container);
	}

	@Override
	public boolean isName(String _name) {
		String name = _name.toLowerCase();

		if (builtinEntityEnds.contains(name))
			return false;

		if (builtinMaleNames.contains(name))
			return true;

		if (builtinFemaleNames.contains(name))
			return true;

		if (customNames.contains(name))
			return true;

		if (isJob(name))
			return true;

		return false;
	}

	private boolean isJob(String name) {
		return RegexUtils
				.ifind(name,
						"(soehne|shne|toechter|tchter|gebrueder|brueder)|(^bundes)|(minister)|(meister$)|(ger$)");
	}

}
