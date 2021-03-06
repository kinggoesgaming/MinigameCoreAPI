/*
 * This file is part of MinigameCoreAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2016 - 2016 MinigameCore <http://minigamecore.github.io>
 * Copyright (c) Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.minigamecore.api.minigame;

import io.github.minigamecore.api.data.DataManager;
import io.github.minigamecore.api.minigame.arena.Arena;

import java.util.SortedMap;

/**
 * Contains logic used to run {@link Arena}s. Each minigame can have multiple
 * {@link Arena}s using it.
 */
public interface Minigame extends DataManager {

    /**
     * Gets all possible states of the minigame. The states are ordered
     * ascending by their key.
     * 
     * @return All possible states of the minigame
     */
    SortedMap<Integer, MinigameState> getStates();

    /**
     * Gets the intial state of the minigame. May return null if no states are
     * present.
     * 
     * @return The initial state of the minigame.
     */
    default MinigameState getInitialState() {
        return getStates().get(getStates().firstKey());
    }

    /**
     * Gets the final state of the minigame. May return null if no states are
     * present.
     * 
     * @return The final state of the minigame.
     */
    default MinigameState getFinalState() {
        return getStates().get(getStates().lastKey());
    }

}
