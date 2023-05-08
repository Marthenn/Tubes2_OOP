package Core.FileController;

import Core.Settings.Settings;

import java.io.File;
import java.nio.file.Paths;

public abstract class AbstractFileController implements FileController {

    protected File getFile(String fileName) {
        return Paths.get(this.getFilePath(fileName)).toFile();
    }

    protected String getFilePath(String fileName) {
        String directoryPath = String.format("%s%s%s", Settings.getInstance().getPath(), File.separator, this.getFileType());
        String ret = String.format("%s%s%s.%s", directoryPath, File.separator, fileName, this.getFileType());
        new File(directoryPath).mkdirs();
        return ret;
    }
}
