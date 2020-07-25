package top.theillusivec4.caelus.api;

import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import top.theillusivec4.caelus.core.CaelusApiImpl;

public interface CaelusApi {

  static CaelusApi getInstance() {
    return CaelusApiImpl.INSTANCE;
  }

  String MODID = "caelus";

  /**
   * The elytra flight attribute, will provide elytra flight if the value is 1.0 or above. No flight
   * otherwise.
   */
  EntityAttribute ELYTRA_FLIGHT = new ClampedEntityAttribute("caelus.elytra_flight", 0.0D, 0.0D,
      1.0D).setTracked(true);

  /**
   * The attribute modifier used for the vanilla elytra item.
   */
  EntityAttributeModifier VANILLA_ELYTRA_MODIFIER = new EntityAttributeModifier(
      UUID.fromString("5b6c3728-9c24-42ae-83ac-70d61d8b8199"), "Elytra modifier", 1.0f,
      EntityAttributeModifier.Operation.ADDITION);

  /**
   * Checks whether or not an entity is able to elytra fly.
   *
   * @param livingEntity The entity to check for elytra flight capabilities
   * @return True if the entity can elytra fly, false otherwise.
   */
  boolean canFly(LivingEntity livingEntity);
}
