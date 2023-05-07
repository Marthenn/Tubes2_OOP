package Core.Item.Bill.Exception;

import lombok.Getter;

public class ItemOverOrderedException extends Exception {
    @Getter
    final private Integer itemId;

    public ItemOverOrderedException(Integer itemId) {
        this.itemId = itemId;
    }
}
