package top.theillusivec4.caelus.api.capability;

public interface IRenderElytra {

  State getRenderState();

  void setRenderState(State renderState);

  enum State {
    NONE,
    ELYTRA,
    ENCHANTED
  }
}
