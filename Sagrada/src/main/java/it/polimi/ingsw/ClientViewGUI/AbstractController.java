package it.polimi.ingsw.ClientViewGUI;

import it.polimi.ingsw.model.PlayerPackage.State;
import javafx.scene.layout.AnchorPane;

public abstract class AbstractController {
    private AnchorPane Base;
    public void update(State state){};

    public AnchorPane getBase() {
        return this.Base;
    }

    public void setBase(AnchorPane base) {
        this.Base = base;
    }
}
