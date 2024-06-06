package app.model.entities.items;

import app.utils.Constants;

/**
 * Item entity that contains the key and effect for shooting.
 */
public class ShootingItem extends Item {
    
    @Override
    public char getKey() {
        return Constants.SHOOTING_ITEM_KEY;
    }

    @Override
    public char getEffectKey() {
        return Constants.GUN_EFFECT_KEY;
    }

    @Override
    public ItemEffect getEffect() {
        return ItemEffect.GUN;
    }
}
