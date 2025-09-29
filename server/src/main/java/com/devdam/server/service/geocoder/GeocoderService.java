package com.devdam.server.service.geocoder;

import com.opencagedata.jopencage.model.JOpenCageResponse;

public interface GeocoderService {
    JOpenCageResponse getLocationInfo(double latitude, double longitude);
    String getLocationName(double latitude, double longitude);
}
