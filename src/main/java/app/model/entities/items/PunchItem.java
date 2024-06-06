package app.model.entities.items;

import app.utils.Constants;

/**
 * Item entity that contains the key and effect for punch.
 */
public class PunchItem extends Item {

    @Override
    public char getKey() {
        return Constants.PUNCH_ITEM_KEY;
    }

    @Override
    public char getEffectKey() {
        return Constants.PUNCH_EFFECT_KEY;
    }

    @Override
    public ItemEffect getEffect() {
        return ItemEffect.PUNCH;
    }
    
}
