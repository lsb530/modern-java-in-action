package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

@Getter
public class GsonUtil {
    private final Gson gson;

    public GsonUtil() {
        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    }
}