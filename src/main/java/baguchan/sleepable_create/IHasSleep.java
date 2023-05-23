package baguchan.sleepable_create;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;

public interface IHasSleep {

    void setStaticSleep(AbstractContraptionEntity hasStaticSleep);

    AbstractContraptionEntity getStaticSleep();
}
