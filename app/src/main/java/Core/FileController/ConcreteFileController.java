package Core.FileController;

import Core.Settings.Settings;

import java.io.File;
import java.nio.file.Paths;

public abstract class ConcreteFileController implements FileController {

    public File getFile(String fileName) {
        return Paths.get(this.getFilePath(fileName)).toFile();
    }

    public String getFilePath(String fileName) {
        String directoryPath = String.format("%s/%s", Settings.getInstance().getPath(), this.getFileType());
        String ret = String.format("%s/%s.%s", directoryPath, fileName, this.getFileType());
        new File(directoryPath).mkdirs();
        return ret;
    }
}
