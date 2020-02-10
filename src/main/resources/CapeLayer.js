function initializeCoreMod() {
    return {
        'coremodone': {
            'target': {
                'type': 'CLASS',
                'name': 'net.minecraft.client.renderer.entity.layers.CapeLayer'
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

                    if (method.name === "render" || method.name === "func_225628_a_") {
                        print("Found method render ", method.toString());
                        var code = method.instructions;
                        var instr = code.toArray();

                        for (var i = 0; i < instr.length; i++) {
                            var instruction = instr[i];

                            if (instruction.getOpcode() == opcodes.IF_ACMPEQ) {
                                var jumpLabel = instruction.label;
                                var inst = instruction;
                                print("Found node ", inst.toString());
                                code.insert(inst, new JumpInsnNode(opcodes.IFEQ, jumpLabel))
                                code.insert(inst, new MethodInsnNode(opcodes.INVOKESTATIC, "top/theillusivec4/caelus/core/CaelusHooks", "hasRenderElytra", "(Lnet/minecraft/entity/LivingEntity;)Z", false))
                                code.insert(inst, new VarInsnNode(opcodes.ALOAD, 4))
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