package com.mishchenkov.entity.product.lamp;

import com.mishchenkov.annotation.Item;

import java.io.Serializable;
import java.util.Objects;

/**
 * This type of lamp is used as interior decoration. The lamp can flicker,
 * and it can also have a different count of spiral turns.
 */
public class EdisonLamp extends Lamp implements Serializable {

    private static final long serialVersionUID = 1923913976272239423L;

    @Item(title = "Item.input.spiral", msg = "Item.select.spiral")
    private int spiralsCount;

    @Item(title = "Item.input.twinkle", msg = "Item.select.twinkle")
    private boolean twinkle;

    public EdisonLamp() {
        super();
    }

    public int getSpiralsCount() {
        return spiralsCount;
    }

    public void setSpiralsCount(int spiralsCount) {
        this.spiralsCount = spiralsCount;
    }

    public boolean isTwinkle() {
        return twinkle;
    }

    public void setTwinkle(boolean twinkle) {
        this.twinkle = twinkle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EdisonLamp that = (EdisonLamp) o;
        return spiralsCount == that.spiralsCount &&
                twinkle == that.twinkle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), spiralsCount, twinkle);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", spiralsCount=" + getSpiralsCount() +
                ", twinkle=" + isTwinkle() +
                '}';
    }
}
