package com.ridelog.analyticsservice.analytics;

import com.ridelog.analyticsservice.analytics.bike.BikeAnalyticsResponse;
import com.ridelog.analyticsservice.analytics.user.UserAnalyticsResponse;

public interface AnalyticsService {

    BikeAnalyticsResponse getBikeAnalytics(
            Long bikeId
    );

    UserAnalyticsResponse getUserAnalytics(
            Long userId
    );
}