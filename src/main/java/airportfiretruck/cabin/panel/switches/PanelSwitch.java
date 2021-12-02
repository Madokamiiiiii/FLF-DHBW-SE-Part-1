package airportfiretruck.cabin.panel.switches;

import airportfiretruck.cabin.panel.ControlPanel;

public class PanelSwitch extends Switch {
    private RelatedDevice device;
    private ControlPanel panel;

    public PanelSwitch(RelatedDevice device) {
        this.device = device;
    }

    public void connectToPanel(ControlPanel panel) {
        this.panel = panel;
    }

    @Override
    public void pressed() {
        panel.panelSwitch(device, isOn());
        super.pressed();
    }
}
