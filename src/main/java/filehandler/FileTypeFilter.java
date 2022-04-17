
package filehandler;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter{
    private final String ext, desc;

    public FileTypeFilter(String ext, String desc) {
        this.ext = ext;
        this.desc = desc;
    }

    @Override
    public boolean accept(File f) {
        if(f.isDirectory()) return true;
        return f.getName().endsWith(ext);
    }

    @Override
    public String getDescription() {
        return desc + String.format(" (*%s)", ext);
    }
}
