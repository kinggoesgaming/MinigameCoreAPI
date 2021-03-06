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

package io.github.minigamecore.api.util.config;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.nio.file.Files.createDirectories;
import static java.nio.file.Files.notExists;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.asset.Asset;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.annotation.Nullable;

/**
 * The Configuration class.
 *
 * <p>
 *     Contains useful methods for loading and saving
 *     {@link CommentedConfigurationNode}s.
 * </p>
 */
public final class Configuration {

    private final ConfigurationLoader<CommentedConfigurationNode> loader;
    private final Asset asset;

    private CommentedConfigurationNode node;

    /**
     * @param file The file.
     */
    public Configuration(File file) {
        this(file.toPath(), null);
    }

    /**
     * @param file The file.
     * @param asset The asset from which the new nodes are to merged from.
     * <p>
     *     The asset should be located at {@code assets/pluginid} as per
     *     Sponge Asset API specification.
     * </p>
     */
    public Configuration(File file, @Nullable Asset asset) {
        this(file.toPath(), asset);
    }

    /**
     * @param file The file.
     */
    public Configuration(Path file) {
        this(file, null);
    }

    /**
     * @param file The file.
     * @param asset The asset from which the new nodes are to merged from.
     * <p>
     *     The asset should be located at {@code assets/pluginid} as per
     *     Sponge Asset API specification.
     * </p>
     */
    public Configuration(Path file, @Nullable Asset asset) {
        checkNotNull(file, "file");

        if (notExists(file)) {
            if (notExists(file.getParent())) {
                try {
                    createDirectories(file.getParent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        loader = HoconConfigurationLoader.builder().setPath(file).build();
        this.asset = asset;
    }

    /**
     * Loads the configuration.
     *
     * @throws IOException Thrown if unable to load.
     */
    public void load() throws IOException {
        node = loader.load();
        if (asset != null) {
            try {
                node.mergeValuesFrom(HoconConfigurationLoader.builder().setURL(asset.getUrl()).build().load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets the {@link CommentedConfigurationNode}.
     *
     * @return The {@link CommentedConfigurationNode}.
     */
    public CommentedConfigurationNode get() {
        return node;
    }

    /**
     * Saves the {@link CommentedConfigurationNode} to disk.
     *
     * @throws IOException Thrown if unable to save.
     */
    public void save() throws IOException {
        loader.save(node);
    }

}
