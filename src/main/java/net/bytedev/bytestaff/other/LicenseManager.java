package net.bytedev.bytestaff.other;


import dev.demeng.sentinel.wrapper.SentinelClient;
import dev.demeng.sentinel.wrapper.exception.*;
import org.bukkit.Bukkit;

import java.io.IOException;

public class LicenseManager {

    public static void authenticate() {

        SentinelClient client = new SentinelClient(
                "http://203.161.53.83:8080/api/v1",
                "ogssvq3g4rb5schpd8trms45v2",
                "z2RX59n4xfQLEFs8uBMitdnXJzsGyUyllB6oa3cUK3o=");

        boolean authenticated = false;

        try {
            client.getLicenseController().auth(
                    "PZI9T-9WBA5-Y9R81-5GZS4-EDT0C", "Example", null, null, "test", "47.204.52.216");
            authenticated = true;
        } catch (ApiException e) {
            System.out.println("Failed to verify license: " + e.getResponse().getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }

        if (authenticated) {
            System.out.println("Successfully authenticated.");
        }
    }

}