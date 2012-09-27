/*******************************************************************************
 * Copyright (c) 2006 - 2012 Tobias Schulz and Contributors.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl.html>.
 ******************************************************************************/
package net.freehal.core.typedefs;

import net.freehal.core.util.Mutable;

public class MutableInteger extends Mutable<Integer> {
	public MutableInteger() {
		super();
	}

	public MutableInteger(Integer i) {
		super(i);
	}

	public MutableInteger increment() {
		return add(1);
	}

	public MutableInteger decrement() {
		return add(-1);
	}

	public MutableInteger add(Integer i) {
		set(get() + i);
		return this;
	}
}