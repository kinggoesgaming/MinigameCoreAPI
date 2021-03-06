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

package io.github.minigamecore.api.minigame.arena;

import io.github.minigamecore.api.minigame.Minigame;
import io.github.minigamecore.api.minigame.MinigameState;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Collection;
import java.util.UUID;

/**
 * Represents in-game data for the minigame. There may or may not be multiple
 * arenas running at once on a server.
 */
public interface Arena {

    /**
     * Gets the minigame the arena is using.
     * 
     * @return The minigame the arena is using.
     */
    Minigame getMinigame();

    /**
     * Adds a player to the arena. Adding the player may fail for various
     * reasons.
     * 
     * @param player The player who will attempt to join the arena.
     */
    void addPlayer(Player player);

    /**
     * Removes a player from the arena.
     * 
     * @param player The player who will leave the arena.
     */
    void removePlayer(Player player);

    /**
     * Gets all players who are in the arena.
     * 
     * @return All players who are in the arena.
     */
    Collection<UUID> getPlayers();

    /**
     * Gets the current {@link MinigameState} of this arena.
     * 
     * @return The current {@link MinigameState} of this arena.
     */
    MinigameState getState();

    /**
     * Cycles the arena to the next {@link MinigameState}.
     */
    void nextState();

    /**
     * Resets the arena to the initial {@link MinigameState}.
     */
    void reset();

}
