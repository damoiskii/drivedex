package com.devdam.server.service.geocoder;

import org.springframework.stereotype.Service;

import com.devdam.server.util.BaseUtils;
import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageResponse;
import com.opencagedata.jopencage.model.JOpenCageReverseRequest;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class GeocoderServiceImpl implements GeocoderService {
    private final JOpenCageGeocoder jOpenCageGeocoder;

    @Override
    public JOpenCageResponse getLocationInfo(double latitude, double longitude) {
        // Using this sleep method here because the free version of opencage is being used which can only take 1 request / sec
        // Ensuring if multiple requests being sent, the system will help the API out with slowing down if necessary
        BaseUtils.sleep(2);

        // In real live application the JOpenCageGeocoder should be a Singleton
        JOpenCageReverseRequest request = new JOpenCageReverseRequest(latitude, longitude);
        request.setNoAnnotations(true);

        JOpenCageResponse response = jOpenCageGeocoder.reverse(request);

        return response;
    }

    @Override
    public String getLocationName(double latitude, double longitude) {
        JOpenCageResponse response = getLocationInfo(latitude, longitude);
        
        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            return response.getResults().get(0).getFormatted();
        }

        return "Unknown location";
    }
}
