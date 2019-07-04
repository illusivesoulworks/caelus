function initializeCoreMod() {
    return {
        'coremodone': {
            'target': {
                'type': 'CLASS',
                'name': 'net.minecraft.entity.LivingEntity'
            },
            'transformer': function(classNode) {
                print("Initializing transformation ", classNode.toString());
                var opcodes = Java.type('org.objectweb.asm.Opcodes')
                var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode')
                var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode')
                var api = Java.type('net.minecraftforge.coremod.api.ASMAPI');
                var methods = classNode.methods;

                for (m in methods) {
                    var method = methods[m];

                    if (method.name === "updateElytra" || method.name === "func_184616_r") {
                        print("Found method ", method.toString());
                        var code = method.instructions;
                        var instr = code.toArray();
                        var count = 0;

                        for (var i = 0; i < instr.length; i++) {
                            var instruction = instr[i];

                            if (instruction.getOpcode() == opcodes.GOTO) {
                                count++;

                                if (count > 1) {
                                    instruction = instruction.getPrevious().getPrevious();
                                    print("Found node ", instruction.toString());
                                    code.insert(instruction, new VarInsnNode(opcodes.ISTORE, 1))
                                    code.insert(instruction, new MethodInsnNode(opcodes.INVOKESTATIC, "top/theillusivec4/caelus/api/CaelusAPI", "canElytraFly", "(Lnet/minecraft/entity/LivingEntity;)Z", false))
                                    code.insert(instruction, new VarInsnNode(opcodes.ALOAD, 0))
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