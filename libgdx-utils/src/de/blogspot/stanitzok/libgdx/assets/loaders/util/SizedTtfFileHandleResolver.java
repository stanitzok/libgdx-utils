package de.blogspot.stanitzok.libgdx.assets.loaders.util;

import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

/**
 * This class decorates a {@link FileHandleResolver} to extend the file parsing
 * mechanism to allow muiltple asset additions from the same file, but with
 * different configurations.
 * 
 * @see SizedTtfFileHandleResolver#SizedTtfFileHandleResolver(FileHandleResolver)
 *      SizedTtfFileHandleResolver(final FileHandleResolver toDecorate)
 * 
 * @author Thorsten Stanitzok
 * 
 */
public class SizedTtfFileHandleResolver implements FileHandleResolver {

    private static Logger logger = Logger.getLogger(SizedTtfFileHandleResolver.class.getName());

    private final FileHandleResolver decoratedResolver;

    private final Pattern pattern = Pattern.compile("^fonts/.*_s(\\d{1,3}).ttf$");

    /**
     * Creates a new instance of a {@link FileHandleResolver} witch parses the
     * filename for the following pattern:
     * <code>^fonts/.*_s(\\d{1,3}).ttf$</code> The file fonts/filename_s24.ttf
     * will be altered into fonts/filename.ttf
     * 
     * @param toDecorate the original used FileHandleResolver to be decorated.
     */
    public SizedTtfFileHandleResolver(final FileHandleResolver toDecorate) {
        this.decoratedResolver = toDecorate;
    }

    @Override
    public FileHandle resolve(final String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("filename must not be null");
        }
        final String parsedFilename = fileName.replaceFirst("(_s\\d{1,3})", "");
        return this.decoratedResolver.resolve(parsedFilename);
    }
}
