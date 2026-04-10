package com.example.map;

import java.util.Objects;

/**
 * A map marker holds only its extrinsic state (position + label) plus a
 * reference to a shared {@link MarkerStyle} obtained from {@link MarkerStyleFactory}.
 */
public class MapMarker {

    private final double lat;
    private final double lng;
    private final String label;
    private final MarkerStyle style;

    public MapMarker(double lat, double lng, String label, MarkerStyle style) {
        this.lat = lat;
        this.lng = lng;
        this.label = label;
        this.style = Objects.requireNonNull(style, "style");
    }

    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public String getLabel() { return label; }
    public MarkerStyle getStyle() { return style; }
}
