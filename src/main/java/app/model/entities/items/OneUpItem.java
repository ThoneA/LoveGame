package app.model.entities.items;

import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

/**
 * Item entity that contains the key and effect for one up and gives player
 * extra health on add.
 */
public class OneUpItem extends Item {

    public OneUpItem() {
        timer /= 5;
    }

    @Override
    public char getKey() {
        return Constants.ONE_UP_ITEM_KEY;
    }

    @Override
    public char getEffectKey() {
        return Constants.ONE_UP_EFFECT_KEY;
    }

    @Override
    public ItemEffect getEffect() {
        return ItemEffect.ONE_UP;
    }

    @Override
    public void onAdd(EntityGameWorld gameWorld) {
        gameWorld.oneUp();
    }
}
