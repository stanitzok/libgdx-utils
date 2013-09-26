/*******************************************************************************
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package de.blogspot.stanitzok.libgdx.assets.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

import de.blogspot.stanitzok.libgdx.assets.loaders.util.SizedTtfFileHandleResolver;

/**
 * This AssetLoader loads a TrueTypeFont file and converts a it into a
 * {@link BitmapFont}.
 * 
 * @author Thorsten Stanitzok
 */
public class TrueTypeFontAssetLoader extends SynchronousAssetLoader<BitmapFont, TrueTypeFontAssetLoaderParameters> {

    /**
     * The default font size if the font file is converted without a specified
     * parameter.
     */
    public static final int DEFAULT_FONT_SIZE = 12;

    /**
     * Creates a new instance using the specified {@link FileHandleResolver}.
     * 
     * @param resolver the FileHandleResolver to use
     * @see ExternalFileHandleResolver
     * @see InternalFileHandleResolver
     */
    public TrueTypeFontAssetLoader(final FileHandleResolver resolver) {
        super(new SizedTtfFileHandleResolver(resolver));

    }

    @Override
    public BitmapFont load(final AssetManager assetManager, final String fileName, final FileHandle file, final TrueTypeFontAssetLoaderParameters parameter) {
        final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(file);

        // use defaults if no parameter set.
        int fontSize = TrueTypeFontAssetLoader.DEFAULT_FONT_SIZE;
        String characters = FreeTypeFontGenerator.DEFAULT_CHARS;
        boolean flip = false;

        // overwrite defaults
        if (parameter != null) {
            fontSize = parameter.getFontSize();
            characters = parameter.getCharacters();
            flip = parameter.isFlip();
        }
        final BitmapFont font = generator.generateFont(fontSize, characters, flip);
        generator.dispose();
        return font;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(final String fileName, final FileHandle file, final TrueTypeFontAssetLoaderParameters parameter) {
        return null;
    }
}
