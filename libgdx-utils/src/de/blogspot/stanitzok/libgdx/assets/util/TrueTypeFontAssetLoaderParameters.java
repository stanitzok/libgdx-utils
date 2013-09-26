package de.blogspot.stanitzok.libgdx.assets.util;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

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
public class TrueTypeFontAssetLoaderParameters extends AssetLoaderParameters<BitmapFont> {
    private int fontSize = 12;
    private String characters = FreeTypeFontGenerator.DEFAULT_CHARS;
    private boolean flip = false;

    /**
     * @param size the size in pixels
     * @param characters the characters the font should contain
     * @param flip whether to flip the font horizontally, see
     *        {@link BitmapFont#BitmapFont(FileHandle, TextureRegion, boolean)}
     */
    public TrueTypeFontAssetLoaderParameters(final int fontSize, final String characters, final boolean flip) {
        this.fontSize = fontSize;
        this.characters = characters;
        this.flip = flip;
    }

    /**
     * @param fontSize the size in pixels
     */
    public TrueTypeFontAssetLoaderParameters(final int fontSize) {
        this.fontSize = fontSize;
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