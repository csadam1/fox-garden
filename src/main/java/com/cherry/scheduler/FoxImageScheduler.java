package com.cherry.scheduler;

import com.cherry.dao.FoxDaoLocal;
import com.cherry.model.entity.Fox;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class FoxImageScheduler {

    private static final Logger logger = Logger.getLogger(FoxImageScheduler.class.getName());


    private static final String URL_ADDRESS = "https://randomfox.ca/floof/";
    private static final String GET_METHOD = "GET";
    private static final String IMAGE_PROPERTY = "image";
    private static final int DEFAULT_TIMEOUT = 5000;
    private static final String ERROR_MESSAGE_LOG = "Error fetching random fox image";
    @EJB
    private FoxDaoLocal foxDao;

    /**
     * Scheduled EJB Timer: runs at the top of every hour and updates one fox
     * that has no image by calling the Random Fox API and saving the image URL.
     */
    @Schedule(hour = "*", persistent = false)
    public void updateMissingFoxImage() {
        try {
            Optional<Fox> optionalFox = foxDao.findOneWithEmptyImage();
            if (optionalFox.isEmpty()) {
                return;
            }
            Fox fox = optionalFox.get();

            String imageUrl = fetchRandomFoxImageUrl();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                fox.setImage(imageUrl);
                foxDao.save(fox);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, ERROR_MESSAGE_LOG, e);
        }
    }

    private String fetchRandomFoxImageUrl() throws Exception {
        URL url = new URL(URL_ADDRESS);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(GET_METHOD);
        conn.setConnectTimeout(DEFAULT_TIMEOUT);
        conn.setReadTimeout(DEFAULT_TIMEOUT);

        int status = conn.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            conn.disconnect();
            return null;
        }

        try (InputStream is = conn.getInputStream();
             JsonReader reader = Json.createReader(is))
        {
            JsonObject obj = reader.readObject();
            return obj.getString(IMAGE_PROPERTY, null);
        } finally {
            conn.disconnect();
        }
    }
}
