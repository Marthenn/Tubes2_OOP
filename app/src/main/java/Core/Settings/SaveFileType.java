package Core.Settings;

import lombok.Getter;

public enum SaveFileType {
    JSON("json"),
    XML("xml"),
    OBJ("obj");

    @Getter
    String name;
    SaveFileType(String name){
        this.name = name;
    }
}
