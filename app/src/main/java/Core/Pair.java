package Core;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Pair<K, V> {
    @Getter
    @NonNull
    private K first;

    @Getter
    @NonNull
    private V second;


}
