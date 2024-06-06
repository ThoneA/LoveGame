package app.model.entities.items;

import app.utils.Constants;

/**
 * Item entity that contains the key and effect for invincibility.
 */
public class InvincibleItem extends Item {

    @Override
    public char getKey() {
        return Constants.INVINCIBLE_ITEM_KEY ;
    }

    @Override
    public char getEffectKey() {
        return Constants.INVINCIBLE_EFFECT_KEY;
    }

    @Override
    public ItemEffect getEffect() {
        return ItemEffect.INVINCIBLE;
    }
    
}
