package io.bloogames.deckbuilder.text;

import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.manager.TextManager;

import java.text.DecimalFormat;
import java.util.function.Supplier;

public class ModelProperties {
    ObjectMap<String, Supplier<String>> propertyMap = new ObjectMap<>();

    public void registerString(String key, String value) {
        propertyMap.put(key, () -> value);
    }

    // Assume that zero should have a plus
    public void registerSignedInt(String key, int value) {
        Supplier<String> supplier = () -> {
            String sign;
            if (value >= 0) {
                sign = TextManager.INSTANCE.getCommonTextTemplate("positive_symbol");
            }
            else {
                sign = TextManager.INSTANCE.getCommonTextTemplate("negative_symbol");
            }
            return sign + Math.abs(value);
        };
        propertyMap.put(key, supplier);
    }

    public void registerInt(String key, int value) {
        propertyMap.put(key, () -> value + "");
    }

    public void registerFloat(String key, float value) {
        registerFloat(key, value, new DecimalFormat("#.##"));
    }

    public void registerFloat(String key, float value, DecimalFormat decimalFormat) {
        propertyMap.put(key, () -> decimalFormat.format(value));
    }

    public void registerDescribable(String key, Describable describable) {
        propertyMap.put(key, describable::description);
    }

    public void registerSupplier(String key, Supplier<String> supplier) {
        propertyMap.put(key, supplier);
    }

    public String parse(String original) {
        String result = original;
        for (ObjectMap.Entry<String, Supplier<String>> entry : propertyMap.entries()) {
            result = result.replace(entry.key, entry.value.get());
        }
        return result;
    }
}
