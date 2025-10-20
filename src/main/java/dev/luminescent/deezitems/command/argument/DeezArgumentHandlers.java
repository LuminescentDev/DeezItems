package dev.luminescent.deezitems.command.argument;

import dev.luminescent.deezitems.DeezItems;
import dev.luminescent.deezitems.utils.DeezItem;
import dev.luminescent.deezitems.utils.DeezMaterial;
import dev.rosewood.rosegarden.command.framework.ArgumentHandler;
public final class DeezArgumentHandlers {

  public static final ArgumentHandler<DeezItem> ITEM = new ItemArgumentHandler(DeezItems.getInstance());
  public static final ArgumentHandler<DeezMaterial> MATERIAL = new MaterialArgumentHandler(DeezItems.getInstance());
  
}
