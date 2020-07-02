function initializeCoreMod() {
  return {
    'coremodone': {
      'target': {
        'type': 'CLASS',
        'name': 'net.minecraft.client.entity.player.ClientPlayerEntity'
      },
      'transformer': function (classNode) {
        print("Initializing transformation ", classNode.toString());
        var opcodes = Java.type('org.objectweb.asm.Opcodes');
        var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
        var JumpInsnNode = Java.type('org.objectweb.asm.tree.JumpInsnNode');
        var methods = classNode.methods;

        for (m in methods) {
          var method = methods[m];

          if (method.name === "livingTick" || method.name === "func_70636_d") {
            print("Found method livingTick ", method.toString());
            var code = method.instructions;
            var instr = code.toArray();

            for (var i = 0; i < instr.length; i++) {
              var instruction = instr[i];

              if (instruction.getOpcode() === opcodes.IF_ACMPNE) {
                var jumpLabel = instruction.label;
                var inst = instruction.getPrevious().getPrevious().getPrevious();
                print("Found node ", inst.toString());
                code.insertBefore(inst, new MethodInsnNode(opcodes.INVOKESTATIC,
                    "top/theillusivec4/caelus/common/CaelusHooks",
                    "sendElytraPacket", "()V", false));
                code.insertBefore(inst,
                    new JumpInsnNode(opcodes.GOTO, jumpLabel));
                break;
              }
            }
          }
        }
        return classNode;
      }
    }
  }
}