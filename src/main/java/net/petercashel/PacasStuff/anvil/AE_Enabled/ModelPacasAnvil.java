package net.petercashel.PacasStuff.anvil.AE_Enabled;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPacasAnvil extends ModelBase
{
  //fields
    ModelRenderer Head;
    ModelRenderer Base;
    ModelRenderer BaseInner;
    ModelRenderer Spine;
    ModelRenderer SpineTop;
  
  public ModelPacasAnvil()
  {
    textureWidth = 64;
    textureHeight = 128;
    
      Head = new ModelRenderer(this, 0, 19);
      Head.addBox(0F, 0F, 0F, 16, 4, 16);
      Head.setRotationPoint(-8F, 8F, -8F);
      Head.setTextureSize(64, 128);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 16, 3, 16);
      Base.setRotationPoint(-8F, 21F, -8F);
      Base.setTextureSize(64, 128);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      BaseInner = new ModelRenderer(this, 0, 39);
      BaseInner.addBox(0F, 0F, 0F, 14, 2, 14);
      BaseInner.setRotationPoint(-7F, 19F, -7F);
      BaseInner.setTextureSize(64, 128);
      BaseInner.mirror = true;
      setRotation(BaseInner, 0F, 0F, 0F);
      Spine = new ModelRenderer(this, 0, 66);
      Spine.addBox(0F, 0F, 0F, 8, 6, 8);
      Spine.setRotationPoint(-4F, 13F, -4F);
      Spine.setTextureSize(64, 128);
      Spine.mirror = true;
      setRotation(Spine, 0F, 0F, 0F);
      SpineTop = new ModelRenderer(this, 0, 55);
      SpineTop.addBox(0F, 0F, 0F, 10, 1, 10);
      SpineTop.setRotationPoint(-5F, 12F, -5F);
      SpineTop.setTextureSize(64, 128);
      SpineTop.mirror = true;
      setRotation(SpineTop, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Head.render(f5);
    Base.render(f5);
    BaseInner.render(f5);
    Spine.render(f5);
    SpineTop.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
