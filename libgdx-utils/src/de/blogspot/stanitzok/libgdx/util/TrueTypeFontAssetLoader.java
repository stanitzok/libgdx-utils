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

package de.blogspot.stanitzok.libgdx.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;

/**
 * This AssetLoader loads a TrueTypeFont file and converts a it into a
 * {@link BitmapFont}.
 * 
 * @author Thorsten Stanitzok
 */
public class TrueTypeFontAssetLoader
		extends
		SynchronousAssetLoader<BitmapFont, TrueTypeFontAssetLoader.TrueTypeFontAssetLoaderParameters> {

	private BitmapFont font;

	/**
	 * The default font size if the font file is converted without a specified
	 * parameter.
	 */
	public static final int DEFAULT_FONT_SIZE = 12;

	/**
	 * Specific parameters used when converting the true type font. Following
	 * default parameters are used:
	 * <ul>
	 * <li>font size: <code>12</code></li>
	 * <li>characters: {@link FreeTypeFontGenerator#DEFAULT_CHARS}</li>
	 * <li>flip: <code>false</code></li>
	 * </ul>
	 * 
	 * @author Thorsten Stanitzok
	 */
	public class TrueTypeFontAssetLoaderParameters extends
			AssetLoaderParameters<BitmapFont> {
		private int fontSize = 12;
		private String characters = FreeTypeFontGenerator.DEFAULT_CHARS;
		private boolean flip = false;

		/**
		 * @param size the size in pixels
		 * @param characters the characters the font should contain
		 * @param flip whether to flip the font horizontally, see
		 *        {@link BitmapFont#BitmapFont(FileHandle, TextureRegion, boolean)}
		 */
		public TrueTypeFontAssetLoaderParameters(final int fontSize,
				final String characters, final boolean flip) {
			this.fontSize = fontSize;
			this.characters = characters;
			this.flip = flip;
		}

		public int getFontSize() {
			return this.fontSize;
		}

		public void setFontSize(final int fontSize) {
			this.fontSize = fontSize;
		}

		public String getCharacters() {
			return this.characters;
		}

		public void setCharacters(final String characters) {
			this.characters = characters;
		}

		public boolean isFlip() {
			return this.flip;
		}

		public void setFlip(final boolean flip) {
			this.flip = flip;
		}
	}

	/**
	 * Creates a new instance using the specified {@link FileHandleResolver}.
	 * 
	 * @param resolver the FileHandleResolver to use
	 * @see ExternalFileHandleResolver
	 * @see InternalFileHandleResolver
	 */
	public TrueTypeFontAssetLoader(final FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public BitmapFont load(final AssetManager assetManager,
			final String fileName, final FileHandle file,
			final TrueTypeFontAssetLoaderParameters parameter) {
		final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(file);
		int fontSize = TrueTypeFontAssetLoader.DEFAULT_FONT_SIZE;
		String characters = FreeTypeFontGenerator.DEFAULT_CHARS;
		boolean flip = false;
		if (parameter != null) {
			fontSize = parameter.getFontSize();
			characters = parameter.getCharacters();
			flip = parameter.isFlip();
		}
		this.font = generator.generateFont(fontSize, characters, flip);
		generator.dispose();
		return this.font;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(final String fileName,
			final FileHandle file, final TrueTypeFontAssetLoaderParameters parameter) {
		return null;
	}
}
