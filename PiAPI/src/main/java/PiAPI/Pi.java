package PiAPI;

import com.google.gson.JsonObject;
import PiAPI.Helpers.Pin;

/**
 * <h1>PiAPI</h1>
 * Allows for
 * <ul>
 * <li>GPIO pin control</li>
 * <li>Executing commands on Pi terminal</li>
 * <li>Configuring PiAPI settings</li>
 */
public class Pi {
    protected String urlOverride = "";
    protected String ipAddress = "";
    protected long port;
    private RuntimeException noUrl = new RuntimeException("API url not provided");

    /**
     * Initiates PiAPI URL
     * @param ipAddress The IP address of your raspberry pi running PiAPI
     * @param port The port that PiAPI is running on
     */
    public Pi(String ipAddress, long port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    /**
     * Initiates PiAPI URL
     * @param urlOverride The full url that PiAPI is running on
     */
    public Pi(String urlOverride) {
        this.urlOverride = urlOverride;
    }

    /**
     * Checks if URL is given
     * @return true if URL is given
     */
    private boolean checkUrl() {
        return (ipAddress != "" || urlOverride != "");
    }

    /**
     * Initiates a GPIO pin on the Pi
     * @param pin The pin number to initiate
     * @param direction Either "in" or "out"
     * @param edge edges should be configured for the pin
     * @param edgeTimeout How long the pin should timeout after edge
     * @return The default state of the new pin
     */
    public String initPin(int pin, String direction, String edge, long edgeTimeout) {
        if (checkUrl()) {
            String url = getRawUrl() + "/InitPin";
            JsonObject pinSettings = new JsonObject();
            pinSettings.addProperty("pin", pin);
            pinSettings.addProperty("direction", direction);

            if (edge != "") {
                pinSettings.addProperty("edge", edge);
            }

            if (edgeTimeout != -1) {
                pinSettings.addProperty("edgeTimeout", edgeTimeout);
            }

            return Utilities.post(url, pinSettings.toString());
        }
        else {
            throw noUrl;
        }
    }

    /**
     * Initiates a GPIO pin on the Pi
     * @param pin The pin number to initiate
     * @param direction Either "in" or "out"
     * @param edge edges should be configured for the pin
     * @return The default state of the new pin
     */
    public String initPin(int pin, String direction, String edge) {
        return initPin(pin, direction, edge, -1);
    }

    /**
     * Initiates a GPIO pin on the Pi
     * @param pin The pin number to initiate
     * @param direction Either "in" or "out"
     * @return The default state of the new pin
     */
    public String initPin(int pin, String direction) {
        return initPin(pin, direction, "", -1);
    }

    /**
     * Unexports a pin from the Pi
     * @param pin The pin to be unexported
     * @return API response
     */
    public String unexportPin(int pin) {
        if (checkUrl()) {
            String url = getRawUrl() + "/Unexport";

            return Utilities.post(url, String.valueOf(pin));
        }
        else {
            throw noUrl;
        }
    } 

    /**
     * Unexports all pins on the Pi
     * @return API response
     */
    public String cleanExit() {
        if (checkUrl()) {
            String url = getRawUrl() + "/CleanExit";

            return Utilities.get(url);
        }
        else {
            throw noUrl;
        }
    }

    /**
     * Sets the state of a given pin on the Pi
     * @param pin The pin on the Pi
     * @param state The state to set the pin (-1 is toggle)
     * @return The state of the pin (Or a JSON of all of the pins and their states)
     */
    public String setState(int pin, int state) {
        if (checkUrl()) {
            String url = getRawUrl() + "/SetState";
            JsonObject pinSettings = new JsonObject();

            pinSettings.addProperty("pin", pin);
            pinSettings.addProperty("state", state);

            return Utilities.post(url, pinSettings.toString());
        }
        else {
            throw noUrl;
        }
    }

    /**
     * Sets all pins on the Pi to a given state
     * @param state The state to set the pins to (-1 to toggle)
     * @return JSON of all the pins that succeeded and failed
     */
    public String setAllStates(int state) {
        if (checkUrl()) {
            String url = getRawUrl() + "/SetState";
            JsonObject pinSettings = new JsonObject();

            pinSettings.addProperty("pin", Pin.all());
            pinSettings.addProperty("state", state);

            return Utilities.post(url, pinSettings.toString());
        }
        else {
            throw noUrl;
        }
    }

    /**
     * Gets the state of a pin
     * @param pin The pin on the Pi
     * @return The state of the pin (Or a JSON of all the pin states)
     */
    public int getState(int pin) {
        if (checkUrl()) {
            String url = getRawUrl() + "/GetState";

            return Integer.valueOf(Utilities.post(url, String.valueOf(pin)));
        }
        else {
            throw noUrl;
        }
    }

    /**
     * Gets all current pin states from the Pi
     * @return JSON string of all the active pins and their states
     */
    public String getAllStates() {
        if (checkUrl()) {
            String url = getRawUrl() + "/GetState";

            return Utilities.post(url, Pin.all());
        }
        else {
            throw noUrl;
        }
    }

    /**
     * A JSON array of all active pins
     * @return A JSON array of all active pins
     */
    public String getActivePins() {
        if (checkUrl()) {
            String url = getRawUrl() + "/ActivePins";

            return Utilities.get(url);
        }
        else {
            throw noUrl;
        }
    }

    /**
     * Executes a Pi terminal command
     * @param command Terminal command
     * @return Command response
     */
    public String command(String command) {
        if (checkUrl()) {
            String url = getRawUrl() + "/Command";

            return Utilities.post(url, command);
        }
        else {
            throw noUrl;
        }
    }

    /**
     * Reboots the Pi
     */
    public void reboot() {
        if (checkUrl()) {
            String url = getRawUrl() + "/Command";

            Utilities.post(url, "sudo reboot");
        }
        else {
            throw noUrl;
        }
    }

    /**
     * Shuts down the Pi
     */
    public void shutdown() {
        if (checkUrl()) {
            String url = getRawUrl() + "/Command";

            Utilities.post(url, "sudo shutdown -h");
        }
        else {
            throw noUrl;
        }
    }

    /**
     * Gets a specefied PiAPI setting
     * @param settingName The name of the setting
     * @return The value of the setting
     */
    public String getAPISetting(String settingName) {
        if (checkUrl()) {
            String url = getRawUrl() + "/GetSetting";

            return Utilities.post(url, settingName);
        }
        else {
            throw noUrl;
        }
    }

    /**
     * Sets a specefied PiAPI setting
     * @param settingName The name of the setting
     * @param settingValue The value of the setting
     * @return API response
     */
    public String setAPISetting(String settingName, Object settingValue) {
        if (checkUrl()) {
            String url = getRawUrl() + "/SetSetting";

            JsonObject setting = new JsonObject();
            setting.addProperty("setting", settingName);
            setting.addProperty("val", Utilities.toJSON(settingValue));

            return Utilities.post(url, setting.toString());
        }
        else {
            throw noUrl;
        }
    }

    /**
     * The port that PiAPI runs on
     * @return The port that PiAPI runs on
     */
    public long getAPIPort() {
        return Long.parseLong(getAPISetting("port"));
    }

    /**
     * Sets the port that PiAPI runs on
     * @param port The new port value
     * @return
     */
    public String setAPIPort(long port) {
        return setAPISetting("port", port);
    }

    /**
     * Gets the raw URL that PiAPI is running on
     * @return PiAPI URL
     */
    public String getRawUrl() {
        String piUrl = "";
        if (urlOverride == "") {
            piUrl += String.format("http://%s", ipAddress);
            piUrl += (port != -1) ? ":" + port : "";
        }
        else {
            piUrl = urlOverride;
        }
        return piUrl;
    }
}