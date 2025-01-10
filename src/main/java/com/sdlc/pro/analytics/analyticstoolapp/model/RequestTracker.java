package com.sdlc.pro.analytics.analyticstoolapp.model;

public record RequestTracker(String ip, String method, String path, String pageName, String time, String thread, Long consumeTime) {
}
