function initializeCoreMod() {
    return {
        'coremodone': {
            'target': {
                'type': 'CLASS',
                'name': 'net.minecraft.client.entity.EntityPlayerSP'
            },
            'transformer': function(classNode) {
                print("Initializing transformation ", classNode.toString());
                var opcodes = Java.type('org.objectweb.asm.Opcodes')
                var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode')
                var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode')
                var JumpInsnNode = Java.type('org.objectweb.asm.tree.JumpInsnNode')
                var api = Java.type('net.minecraftforge.coremod.api.ASMAPI');
                var methods = classNode.methods;

                for (m in methods) {
                    var method = methods[m];

                    if (method.name.compareTo("livingTick") == 0) {
                        print("Found method ", method.toString());
                        var code = method.instructions;
                        var instr = code.toArray();
                        var count = 0;

                        for (var i = 0; i < instr.length; i++) {
                            var instruction = instr[i];

                            if (instruction.getOpcode() == opcodes.IF_ACMPNE) {
                                var jumpLabel = instruction.label;
                                var inst = instruction.getPrevious().getPrevious().getPrevious();
                                print("Found node ", inst.toString());
                                code.insertBefore(inst, new VarInsnNode(opcodes.ALOAD, 0))
                                code.insertBefore(inst, new MethodInsnNode(opcodes.INVOKESTATIC, "top/theillusivec4/caelus/core/CaelusHooks", "sendElytraPacket", "(Lnet/minecraft/client/entity/EntityPlayerSP;)V", false))
                                code.insertBefore(inst, new JumpInsnNode(opcodes.GOTO, jumpLabel))
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