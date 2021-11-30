package airportfiretruck.cabin.panel.switches;

import airportfiretruck.cabin.panel.ControlPanel;
import airportfiretruck.centralunit.IControlPanelCentralUnit;

public class PanelSwitch extends Switch{
    private RelatedDevice device;
    private ControlPanel panel;

    public PanelSwitch() {

    }

    @Override
    public void pressed() {
        panel.pswitch(device,isOn());
        super.pressed();
    }
}
