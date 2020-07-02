function initializeCoreMod() {
  return {
    'coremodone': {
      'target': {
        'type': 'CLASS',
        'name': 'net.minecraft.entity.player.PlayerEntity'
      },
      'transformer': function (classNode) {
        print("Initializing transformation ", classNode.toString());
        var opcodes = Java.type('org.objectweb.asm.Opcodes');
        var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
        var FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');
        var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
        var TypeInsnNode = Java.type('org.objectweb.asm.tree.TypeInsnNode');
        var methods = classNode.methods;

        for (m in methods) {
          var method = methods[m];

          if (method.name === "func_234570_el_") {
            print("Found method func_234570_el_ ", method.toString());
            var code = method.instructions;
            var instr = code.toArray();
            var count = 0;

            for (var i = 0; i < instr.length; i++) {
              var instruction = instr[i];

              if (instruction.getOpcode() === opcodes.INVOKEVIRTUAL) {
                count++;

                if (count > 5) {
                  print("Found node ", instruction.toString());
                  code.insert(instruction,
                      new MethodInsnNode(opcodes.INVOKEVIRTUAL,
                          "net/minecraft/entity/ai/attributes/AttributeModifierMap$MutableAttribute",
                          "func_233814_a_",
                          "(Lnet/minecraft/entity/ai/attributes/Attribute;)Lnet/minecraft/entity/ai/attributes/AttributeModifierMap$MutableAttribute;"));
                  code.insert(instruction, new TypeInsnNode(opcodes.CHECKCAST,
                      "net/minecraft/entity/ai/attributes/Attribute"));
                  code.insert(instruction,
                      new MethodInsnNode(opcodes.INVOKEVIRTUAL,
                          "net/minecraftforge/fml/RegistryObject", "get",
                          "()Lnet/minecraftforge/registries/IForgeRegistryEntry;"));
                  code.insert(instruction, new FieldInsnNode(opcodes.GETSTATIC,
                      "top/theillusivec4/caelus/api/CaelusApi", "ELYTRA_FLIGHT",
                      "Lnet/minecraftforge/fml/RegistryObject;"));
                  break;
                }
              }
            }
          }
        }
        return classNode;
      }
    }
  }
}