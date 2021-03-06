package hudson.plugins.analysis.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

/**
 * Detects the package name of a Java file.
 *
 * @author Ulli Hafner
 */
public class JavaPackageDetector extends AbstractPackageDetector {
    /** Package pattern. */
    private final Pattern pattern;

    /**
     * Creates a new instance of {@link JavaPackageDetector}.
     */
    public JavaPackageDetector() {
        super();

        pattern = Pattern.compile("^\\s*package\\s*([a-z]+(\\.[a-zA-Z_][a-zA-Z0-9_]*)*)\\s*;.*");
    }

    /** {@inheritDoc}*/
    @Override
    public String detectPackageName(final InputStream stream) {
        try {
            LineIterator iterator = new LineIterator(new InputStreamReader(stream,
                    Charsets.toCharset(Charset.defaultCharset())));

            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    return matcher.group(1);
                }
            }
        }
        finally {
            IOUtils.closeQuietly(stream);
        }
        return UNKNOWN_PACKAGE;
    }

    @Override
    public boolean accepts(final String fileName) {
        return fileName.endsWith(".java");
    }
}

