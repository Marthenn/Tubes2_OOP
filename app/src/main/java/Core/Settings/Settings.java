package Core.Settings;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
public class Settings {
    // pada class ini terdapat minim pengecekan terhadap inputan dari user
    // hal ini dilatarbelakangi oleh datanya yang berasal dari GUI
    private static Settings instance = null;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private FileType fileType = FileType.OBJECT;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String filePath = null;
    // should it be able to distinguish between file and directory?
    // Jika data file disimpan lebih dari 1 file maka basis penyimpanan adalah memilih folder

    private Settings(){}

    // Singleton pattern for Settings
    public static Settings getInstance(){
        if (Settings.instance == null){
            Settings.instance = new Settings();
        }
        return Settings.instance;
    }

    // TODO: Add settings for plugins
}
